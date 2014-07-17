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
	
	//�������ֻ�������߳��У�Ҫ��Handler���������̵߳õ������ݡ�
	private MyHandler myhandler = new MyHandler();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//�������̴߳������ȡ���ݡ�
		Thread thread = new Thread(new getListRunnble());
		thread.start();
		
		contentView = (ListView)findViewById(R.id.listView1);
		
		//����ListView����Ŀ���������.
		contentView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pesition,long id) {
				//���ݵ����λ�õõ�������News����.
				News temp = list.get(pesition);
				
				Intent intent = new Intent(MainActivity.this,ShowNewsActivity.class);//ת����һ�������С�
				//��intent��������.
				intent.putExtra("title", temp.getTitle());
				intent.putExtra("pubDate", temp.getPubDate());
				intent.putExtra("description", temp.getDescription());
				intent.putExtra("link", temp.getLink());
				
				startActivity(intent);
			}
		});
		
	}
	
	//ͨ�����ߵ�Handler����Ϣ���з������ݲ�����UI.
	class MyHandler extends Handler{
		public void handleMessage(Message msg) {
			
			list = (List<News>)msg.obj;
			
			content = new String[list.size()];

			for(News ne:list){
				content[count] = ne.getTitle().trim();
				count++;
			}
			
			//����ListView����ʾ��
			contentView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,content));

		}
		
	}
	
	//�������ϻ�ȡ���ݵ����̡߳�
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






























