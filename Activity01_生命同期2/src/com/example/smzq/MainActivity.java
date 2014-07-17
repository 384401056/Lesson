package com.example.smzq;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	
	private Button btn01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		btn01 = (Button)findViewById(R.id.button1);
		btn01.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				//以下两种属于粗暴的关闭方式，以至于Application的onTerminate过程都没被调用。
				//所以Activity的onDestroy也不可能会被调用。
				
				//System.exit(0);
				Process.killProcess(Process.myPid()); //属于粗暴的关闭方式.
			}
		});
		
	}

	/**
	 * 
	 * Activity关闭时被调用。
	 * 
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Log.i("MyLog", "Activity onDestroy");
	}
	
	
	
	
	
}
