package com.example.activity04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn01,btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        Log.i("MyLog", "MainActivity TaskId = "+getTaskId());
        
        btn01 = (Button)findViewById(R.id.button1);
        btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(MainActivity.this,ActivityA.class);
				startActivity(_intent);		
			}
		});
        
        btn02 = (Button)findViewById(R.id.button2);
        btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(MainActivity.this,ActivityB.class);
				startActivity(_intent);
			}
		});
          
    }

}



























