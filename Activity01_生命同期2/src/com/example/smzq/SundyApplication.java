/**
 * 
 * 
 * 我们自的Application类。
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
	 * 当配置改变时，屏幕初次出现以及屏幕翻转时调用。
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		Log.i("MyLog", "App ConfigrationChanged");
		
	}

	/**
	 * 启动时调用.
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.i("MyLog", "App Create");
	}

	/**
	 * 内存过低时调用。
	 */
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		
		Log.i("MyLog", "App LowMemory");
	}

	/**
	 * 当被系统关闭时调用。
	 */
	@Override
	public void onTerminate() {
		super.onTerminate();
		
		Log.i("MyLog", "App onTerminate");
	}

	
}
