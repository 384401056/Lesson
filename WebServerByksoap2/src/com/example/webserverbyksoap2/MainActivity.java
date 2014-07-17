package com.example.webserverbyksoap2;

import java.util.List;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class MainActivity extends Activity {

	MyHandler myHeadler = new MyHandler();
	
	Button btn01 = null;
	Button btn02 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn01 = (Button)findViewById(R.id.button1);
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                Thread mythread = new Thread(new getListRunnable());
                mythread.start();
			}
		});
		
		btn02 = (Button)findViewById(R.id.button2);
		btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Thread mythread = new Thread(new getDatasetRunnable());
				mythread.start();
			}
		});
		
		
	}

	//线程类
	class getListRunnable implements Runnable{
		@Override
		public void run() {
			System.out.println("=======>Runnable");
			
			List<String> cityList = WebServiceUtil.getProvinceList();
			for(int i=0;i<cityList.size();i++){
				System.out.println(i+"=======>"+ cityList.get(i).toString());
			}
			
//			Message message = new Message();
//			message.obj = list;
//			myHeadler.sendMessage(message);
		}
		
	}
	
	//线程类
	class getDatasetRunnable implements Runnable{
		@Override
		public void run() {
			System.out.println("=======>Runnable");
			WebServiceUtil.getSupportCityDataset("31126");
		}
		
	}
	
	
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			
		}
		
	}

}
