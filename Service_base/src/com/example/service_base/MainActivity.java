/*
 * 启动Service来播放音乐，当音乐播放完毕，通过Intent发送广播,由广播接收类来处理。
 */
package com.example.service_base;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn01 = null;
	private Button btn02 = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		btn01 = (Button)findViewById(R.id.button1);
		btn01.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				startService(new Intent("com.sharpandroid.Music"));
			}
		});
		
		btn02 = (Button)findViewById(R.id.button2);
		btn02.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				stopService(new Intent("com.sharpandroid.Music"));
			}
		});
	}
}
