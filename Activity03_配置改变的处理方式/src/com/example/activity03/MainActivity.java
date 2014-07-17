package com.example.activity03;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	/**
	 * �����øı�ʱ���ô˷�����
	 * ��Щ���øı�? ��Manifest�ļ��е�Activty������configChanges����:orientation������Ļ���л���
	 * �˷����Ĳ���Ҫ������Ͻ��У�ģ��������Bug.
	 * ������configChange���л���Ļʱ������ִ��onCreate(),onStart(),onDestory()��Щ�����ˣ�����ִֻ��onConfigurationChanged()������
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		
		Log.i("MyLog","onConfigurationChanged");
		
		if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
			Log.i("MyLog","��ʱ�Ǻ������羰ģʽ��");
		}
		else{
			Log.i("MyLog","��ʱ������������ģʽ��");
		}
		
	}
	
	
	
}
