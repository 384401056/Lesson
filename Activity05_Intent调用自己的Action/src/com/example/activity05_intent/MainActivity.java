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
	 * �ð�ť�¼������Լ���һ��App�е�Activty.
	 */
	btn01 = (Button)findViewById(R.id.button1);
	btn01.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent _Intent = new Intent();
			
			//����com.example.activity05.MainActivity���Activity�е� <intent-filter>  �µ� action  name ���Ա���Ϊ"".
			//��Ȼ���App�������Ѿ���װ���˵ġ�
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
