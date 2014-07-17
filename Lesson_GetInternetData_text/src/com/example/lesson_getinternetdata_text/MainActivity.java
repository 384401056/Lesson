package com.example.lesson_getinternetdata_text;

import com.example.net.util.NetTool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et01 = null;
	private Button btn01 = null;
	private ProgressBar pb01 =null;
	
	private MyHandler myhandler = new MyHandler();
	
	private String urlpath = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et01 = (EditText)findViewById(R.id.editText1);
		pb01 = (ProgressBar)findViewById(R.id.progressBar1);
		btn01 = (Button)findViewById(R.id.button1);
		btn01.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0){
				//�ж������Ƿ������ӡ���Ҫ�û�Ȩ�ޡ�
				if(isNetworkConnected(MainActivity.this)){
					urlpath =  et01.getText().toString().trim(); //�˴���url ������http://��ͷ����ַ��
					pb01.setVisibility(View.VISIBLE); //��ʾ��������
					Thread thread = new Thread(new GetData()); //�������̴߳������ȡ����.
					thread.start();
				}
				else{
					Toast.makeText(MainActivity.this, "û�м�⵽�������ӣ�", Toast.LENGTH_LONG).show();
					return;
				}
			}
		});
		
	}
	
	//ͨ����Ϣ���д������ݵ����̡߳�
	class MyHandler extends Handler{
		public void handleMessage(Message msg) {
			pb01.setVisibility(View.INVISIBLE);
			String data = (String)msg.obj;
			Intent intent = new Intent(MainActivity.this, TextActivity.class);
			intent.putExtra("data", data);
			startActivity(intent);
		}
		
	}
	
	//���߳�.
	class GetData implements Runnable{
		public void run() {
			try{
				Message msg = new Message();
				msg.obj = NetTool.getHtml(urlpath, "UTF-8");
				myhandler.sendMessage(msg);
			}catch(Exception ex){
				ex.printStackTrace();
			}
//			try {
//				NetTool.getFile(urlpath, "01.txt");
//			} catch (Exception e) {
//
//			}
		}
		
	}
	
	//�ж��Ƿ����������� 
	public boolean isNetworkConnected(Context context) { 
		if (context != null) { 
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
			if (mNetworkInfo != null) { 
				return mNetworkInfo.isAvailable(); 
			}
		}
		return false;
	}
}
