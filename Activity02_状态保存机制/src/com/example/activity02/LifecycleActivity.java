package com.example.activity02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * ���Activity��������
 * ���Activity��״̬������ƣ��Լ��Լ�����״̬����ķ�����
 * @author Administrator
 *
 */
public class LifecycleActivity extends Activity {

	private Button btn01;
	private Button btn02;
	private AlertDialog.Builder builder;
	private EditText et01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//���������ContentView����window����һ��Ĭ�ϵġ�
		setContentView(R.layout.activity_lifecycle);
		Log.i("MyLog","OnCreate");

		et01 = (EditText)findViewById(R.id.editText1);
		
		//�Լ�����״̬�Ļָ���Ҳ������onRestoreInstanceState()������ȥ�ָ���
		//���ǹٷ��Ƽ���onCreate()�����лָ�����ΪonRestoreInstanceState()������ʱ���ܲ��ᱻ���õ���
		if(savedInstanceState!=null){
			et01.setText(savedInstanceState.getString("myState"));
		}
		
		
		
		/**
		 * ������Activity.
		 */
		btn01 = (Button)findViewById(R.id.button1);
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent _intent = new Intent(LifecycleActivity.this,SecondActivity.class);
				startActivity(_intent);
			}
		});
		

		/**
		 * ����Dialog��
		 */
		builder = new AlertDialog.Builder(LifecycleActivity.this);
		builder.setMessage("�������Ҫ�˳���");
		builder.setCancelable(false);
		
		builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {                
//				LifecycleActivity.this.finish();
				dialog.cancel();
			}
		});      
			
		
		builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {           
			@Override
			public void onClick(DialogInterface dialog, int id) {                
				dialog.cancel();
			}
		});
		
		
		/**
		 * ����Dialog.
		 */
		btn02=(Button)findViewById(R.id.button2);
		btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("MyLog", "onStart");
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("MyLog", "onResume");
	}
	

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("MyLog", "onPause");
	}


	@Override
	protected void onStop() {
		super.onStop();
		Log.i("MyLog", "OnStop");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		Log.i("MyLog", "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("MyLog", "OnDestroy");
	}

	/**
	 * ������ֱ�Ӱ�Home���˻ص�Homeҳ��ʱ������ͣonPause()����֮ǰ��Ե�ǰActivity��״̬�����Զ����档
	 * ����ǵ�������ؼ����˳����򣬲�����ô˷�����Ҳ����˵����Distroy�����ǲ��ᱣ��״̬�ġ�
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//��Ҫ�Լ�����״̬����ʱ��ϵͳ����ı��淽��Ҫȥ��,�����Ų�����ϵͳ�Զ��Ļָ��������ǵ����ǵġ�
//		super.onSaveInstanceState(outState);
		
		//�޸�Ҫ�����״̬��
		outState.putString("myState", "GaoYanBin:"+et01.getText());
		Log.i("MyLog", "onSaveInstanceState");
	}

	/**
	 * ֻ����ִ��onSaveInstanceState���������󣬽���ֱ�ӱ�Kill���ٴ����г���˷����Żᱻִ�С�
	 * ��������onResume()����֮ǰִ�С�
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("MyLog", "onRestoreInstanceState");
	}
	
	
	
	
	
}
