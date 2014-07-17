package com.example.lesson_intentactivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn01 = null;
	private Button btn02 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn01 = (Button)findViewById(R.id.button1);
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,OtherActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		btn02 = (Button)findViewById(R.id.Button02);
		btn02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//通过隐式意图调用Activity
				Intent intent = new Intent("intent.IntentAcitivy");
				startActivity(intent);
				
			}
		});
		
	}

}
