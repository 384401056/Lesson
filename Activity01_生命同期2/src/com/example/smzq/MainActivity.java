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
				
				//�����������ڴֱ��Ĺرշ�ʽ��������Application��onTerminate���̶�û�����á�
				//����Activity��onDestroyҲ�����ܻᱻ���á�
				
				//System.exit(0);
				Process.killProcess(Process.myPid()); //���ڴֱ��Ĺرշ�ʽ.
			}
		});
		
	}

	/**
	 * 
	 * Activity�ر�ʱ�����á�
	 * 
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Log.i("MyLog", "Activity onDestroy");
	}
	
	
	
	
	
}
