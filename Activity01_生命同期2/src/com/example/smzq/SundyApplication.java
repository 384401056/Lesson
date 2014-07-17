/**
 * 
 * 
 * �����Ե�Application�ࡣ
 * 
 * 
 * 
 */
package com.example.smzq;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class SundyApplication extends Application
{

	/**
	 * �����øı�ʱ����Ļ���γ����Լ���Ļ��תʱ���á�
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		Log.i("MyLog", "App ConfigrationChanged");
		
	}

	/**
	 * ����ʱ����.
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.i("MyLog", "App Create");
	}

	/**
	 * �ڴ����ʱ���á�
	 */
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		
		Log.i("MyLog", "App LowMemory");
	}

	/**
	 * ����ϵͳ�ر�ʱ���á�
	 */
	@Override
	public void onTerminate() {
		super.onTerminate();
		
		Log.i("MyLog", "App onTerminate");
	}

	
}
