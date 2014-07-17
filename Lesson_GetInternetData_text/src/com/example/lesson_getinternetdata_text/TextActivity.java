package com.example.lesson_getinternetdata_text;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class TextActivity extends Activity {

	private TextView tv01 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
		tv01 = (TextView)findViewById(R.id.textView1);
		
		Intent intent = getIntent();
		String str = intent.getStringExtra("data");
		tv01.setText(str);
	}
	
	
	
	
	
	
	
	
}
