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
	 * 在暂停onPause()方法之前会对当前Activity的状态进行保存。
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i("MyLog", "SecondActivity onSaveInstanceState");
	}

	
	/**
	 * 只有在执行onSaveInstanceState（）方法后，进程直接被Kill，再次运行程序此方法才会被执行。
	 * 并且是在onResume()方法之前执行。
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("MyLog", "SecondActivity onRestoreInstanceState");
	}

}
