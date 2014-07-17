/*
 * 通过绑定的方式来启动服务。
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
	
	private Service_Player serverPlayer = null;
	
	private ServiceConnection connection = new ServiceConnection() {
		public void onServiceDisconnected(ComponentName name) {
			serverPlayer = null;
		}
		public void onServiceConnected(ComponentName name, IBinder service) {
			//先将IBinder转成LocalBinder,再调用getService();
			serverPlayer = ((Service_Player.LocalBinder)service).getService();
		}
	};
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bindService(new Intent(this,Service_Player.class), connection, Context.BIND_AUTO_CREATE);
		
		
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
