package com.example.activity04;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityA extends Activity {

	private Button btn01,btn02;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_a);
		
		Log.i("MyLog", "ActivityA TaskId="+getTaskId());
		
		
		btn01 = (Button)findViewById(R.id.Abutton1);
        btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(ActivityA.this,MainActivity.class);
				startActivity(_intent);		
			}
		});
        
        btn02 = (Button)findViewById(R.id.Abutton2);
        btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(ActivityA.this,ActivityB.class);
				startActivity(_intent);
			}
		});
	}

}
