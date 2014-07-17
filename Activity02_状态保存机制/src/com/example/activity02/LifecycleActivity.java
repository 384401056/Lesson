package com.example.activity02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 理解Activity的生命期
 * 理解Activity的状态保存机制，以及自己进行状态保存的方法。
 * @author Administrator
 *
 */
public class LifecycleActivity extends Activity {

	private Button btn01;
	private Button btn02;
	private AlertDialog.Builder builder;
	private EditText et01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//如果不设置ContentView，则window会有一个默认的。
		setContentView(R.layout.activity_lifecycle);
		Log.i("MyLog","OnCreate");

		et01 = (EditText)findViewById(R.id.editText1);
		
		//自己进行状态的恢复。也可以在onRestoreInstanceState()方法中去恢复。
		//但是官方推荐在onCreate()方法中恢复。因为onRestoreInstanceState()方法有时可能不会被调用到。
		if(savedInstanceState!=null){
			et01.setText(savedInstanceState.getString("myState"));
		}
		
		
		
		/**
		 * 启动新Activity.
		 */
		btn01 = (Button)findViewById(R.id.button1);
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(LifecycleActivity.this,SecondActivity.class);
				startActivity(_intent);
			}
		});
		

		/**
		 * 设置Dialog。
		 */
		builder = new AlertDialog.Builder(LifecycleActivity.this);
		builder.setMessage("你真的想要退出吗？");
		builder.setCancelable(false);
		
		builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {                
//				LifecycleActivity.this.finish();
				dialog.cancel();
			}
		});      
			
		
		builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {           
			@Override
			public void onClick(DialogInterface dialog, int id) {                
				dialog.cancel();
			}
		});
		
		
		/**
		 * 启动Dialog.
		 */
		btn02=(Button)findViewById(R.id.button2);
		btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("MyLog", "onStart");
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("MyLog", "onResume");
	}
	

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("MyLog", "onPause");
	}


	@Override
	protected void onStop() {
		super.onStop();
		Log.i("MyLog", "OnStop");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		Log.i("MyLog", "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("MyLog", "OnDestroy");
	}

	/**
	 * 程序在直接按Home键退回到Home页面时，在暂停onPause()方法之前会对当前Activity的状态进行自动保存。
	 * 如果是点击“返回键”退出程序，不会调用此方法。也就是说程序被Distroy掉后，是不会保存状态的。
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//当要自己进行状态保存时，系统自身的保存方法要去掉,这样才不会让系统自动的恢复方法覆盖掉我们的。
//		super.onSaveInstanceState(outState);
		
		//修改要保存的状态。
		outState.putString("myState", "GaoYanBin:"+et01.getText());
		Log.i("MyLog", "onSaveInstanceState");
	}

	/**
	 * 只有在执行onSaveInstanceState（）方法后，进程直接被Kill，再次运行程序此方法才会被执行。
	 * 并且是在onResume()方法之前执行。
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("MyLog", "onRestoreInstanceState");
	}
	
	
	
	
	
}
