package com.example.service_base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
 * �㲥�����࣬�����յ��Ĺ㲥.
 */
public class Service_Broadcast extends BroadcastReceiver {
	
	private static final String MUSIC_COMPLETED = "com.sharpandroid.Service_Player.completed";

	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(action.equals(MUSIC_COMPLETED)){
			Toast.makeText(context, "���Ž���.", Toast.LENGTH_LONG).show();
			context.stopService(new Intent("com.sharpandroid.Music"));  //ֹͣ����.
		}

	}

}
