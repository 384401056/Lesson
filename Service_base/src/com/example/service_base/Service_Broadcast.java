package com.example.service_base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
 * 广播接收类，处理收到的广播.
 */
public class Service_Broadcast extends BroadcastReceiver {
	
	private static final String MUSIC_COMPLETED = "com.sharpandroid.Service_Player.completed";

	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(action.equals(MUSIC_COMPLETED)){
			Toast.makeText(context, "播放结束.", Toast.LENGTH_LONG).show();
			context.stopService(new Intent("com.sharpandroid.Music"));  //停止服务.
		}

	}

}
