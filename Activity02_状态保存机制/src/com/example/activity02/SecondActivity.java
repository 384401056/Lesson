package com.example.activity02;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Log.i("MyLog","SecondActivity onCreate");
	}


	@Override
	protected void onStart() {
		super.onStart();
		
		Log.i("MyLog","SecondActivity onStart");
	}


	@Override
	protected void onResume() {
		super.onResume();
		
		Log.i("MyLog","SecondActivity onResume");
	}


	@Override
	protected void onPause() {
		super.onPause();
		
		Log.i("MyLog","SecondActivity onPause");
	}


	@Override
	protected void onStop() {
		super.onStop();
		
		Log.i("MyLog","SecondActivity onStop");
	}

	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		Log.i("MyLog","SecondActivity onRestart");
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		Log.i("MyLog","SecondActivity onDestroy");
	}
	
	/**
	 * ����ͣonPause()����֮ǰ��Ե�ǰActivity��״̬���б��档
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i("MyLog", "SecondActivity onSaveInstanceState");
	}

	
	/**
	 * ֻ����ִ��onSaveInstanceState���������󣬽���ֱ�ӱ�Kill���ٴ����г���˷����Żᱻִ�С�
	 * ��������onResume()����֮ǰִ�С�
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("MyLog", "SecondActivity onRestoreInstanceState");
	}

}
