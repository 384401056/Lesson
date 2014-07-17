package com.example.activity05_intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn01;
	private Button btn02;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	/**
	 * 用按钮事件调用自己别一个App中的Activty.
	 */
	btn01 = (Button)findViewById(R.id.button1);
	btn01.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent _Intent = new Intent();
			
			//这里com.example.activity05.MainActivity这个Activity中的 <intent-filter>  下的 action  name 属性必须为"".
			//当然这个App必须是已经安装过了的。
			_Intent.setClassName("com.example.activity05", "com.example.activity05.MainActivity");
			
			startActivity(_Intent);
		}
	});
	
	
	btn02 = (Button)findViewById(R.id.button2);
	btn02.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent _Intent = new Intent();
			_Intent.setAction(Intent.ACTION_DIAL);
			startActivity(_Intent);
		}
	});
		
		
	}
	
}
