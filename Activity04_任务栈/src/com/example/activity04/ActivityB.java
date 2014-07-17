package com.example.activity04;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityB extends Activity {

	private Button btn01,btn02;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_b);
		
		Log.i("MyLog","ActivityB TaskId="+getTaskId());
		
		
		btn01 = (Button)findViewById(R.id.Bbutton1);
        btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(ActivityB.this,MainActivity.class);
				startActivity(_intent);		
			}
		});
        
        btn02 = (Button)findViewById(R.id.Bbutton2);
        btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(ActivityB.this,ActivityA.class);
				startActivity(_intent);
			}
		});
		
	}
}
