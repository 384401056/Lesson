package com.example.activity01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RoseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rose);
		
		Intent intent = getIntent();
		
		String strName ;
		
		if(intent!=null){
			strName = intent.getExtras().getString("name");
			Toast.makeText(this, strName, Toast.LENGTH_LONG).show();
		}
		
		
		
		//ִ�з���ֵ��ҵ���߼���
		Button btnRose = (Button)findViewById(R.id.btnRose);
		btnRose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//�½�����װ�ط���ֵ��Intent.
				Intent returnIntent = new Intent();
				returnIntent.putExtra("name", "Google");
				
				//����Activity�ķ���ֵ.
				setResult(321, returnIntent);
				
				//�ر�Activity.
				finish();
			}
		});
		
		
	}
}




























