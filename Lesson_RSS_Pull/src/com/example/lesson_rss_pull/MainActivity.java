package com.example.lesson_rss_pull;


import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView contentView = null;
	private List<News> list = null;
	
	private String[] content;
	private int count = 0;
	
	//网络访问只能在子线程中，要用Handler来传递子线程得到的数据。
	private MyHandler myhandler = new MyHandler();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//启动新线程从网络获取数据。
		Thread thread = new Thread(new getListRunnble());
		thread.start();
		
		contentView = (ListView)findViewById(R.id.listView1);
		
		//定义ListView的栏目点击监听器.
		contentView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pesition,long id) {
				//根据点击的位置得到单个的News对象.
				News temp = list.get(pesition);
				
				Intent intent = new Intent(MainActivity.this,ShowNewsActivity.class);//转入另一个窗口中。
				//用intent传递数据.
				intent.putExtra("title", temp.getTitle());
				intent.putExtra("pubDate", temp.getPubDate());
				intent.putExtra("description", temp.getDescription());
				intent.putExtra("link", temp.getLink());
				
				startActivity(intent);
			}
		});
		
	}
	
	//通过主线的Handler的消息队列返回数据并更新UI.
	class MyHandler extends Handler{
		public void handleMessage(Message msg) {
			
			list = (List<News>)msg.obj;
			
			content = new String[list.size()];

			for(News ne:list){
				content[count] = ne.getTitle().trim();
				count++;
			}
			
			//设置ListView的显示。
			contentView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,content));

		}
		
	}
	
	//从网络上获取数据的子线程。
	class getListRunnble implements Runnable{
		private String path = "http://rss.sina.com.cn/sports/global/focus.xml";
		public void run() {
			try {
				
				List<News> list = PullService.readXML(NetTool.getStream(path, "UTF-8"));
				
				Message msg = new Message();
				msg.obj = list;
				myhandler.sendMessage(msg);
				
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
	}
}






























