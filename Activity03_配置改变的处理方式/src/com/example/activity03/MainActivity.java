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
	 * 当配置改变时调用此方法。
	 * 哪些配置改变? 在Manifest文件中的Activty中设置configChanges。如:orientation就是屏幕的切换。
	 * 此方法的测试要在真机上进行，模拟器上有Bug.
	 * 配置了configChange后，切换屏幕时不会再执行onCreate(),onStart(),onDestory()这些方法了，而是只执行onConfigurationChanged()方法。
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		
		Log.i("MyLog","onConfigurationChanged");
		
		if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
			Log.i("MyLog","此时是横屏（风景模式）");
		}
		else{
			Log.i("MyLog","此时是竖屏（人像模式）");
		}
		
	}
	
	
	
}
