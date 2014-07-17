package com.example.lesson_activitylift;

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
				Intent intent = new Intent(MainActivity.this, OtherActivity.class);
				//¿ªÆôÐÂActivity
				startActivity(intent); 
			}
		});
	
		
		btn02 = (Button)findViewById(R.id.button2);
		btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	protected void onPause() {

		super.onPause();
	}

	@Override
	protected void onRestart() {

		super.onRestart();
	}

	@Override
	protected void onResume() {

		super.onResume();
	}

	@Override
	protected void onStart() {

		super.onStart();
	}

	@Override
	protected void onStop() {

		super.onStop();
	}
	
	
	
	
}
