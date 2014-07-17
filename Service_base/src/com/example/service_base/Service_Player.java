/*
 * ��Service���ź�̨����.
 */
package com.example.service_base;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class Service_Player extends Service {

	MediaPlayer MPlayer;
	
	//�����ֲ�����Ϻ󣬴����¼�.
	MediaPlayer.OnCompletionListener completeListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mp) {
			//���͹㲥.
			Intent i = new Intent("com.sharpandroid.Service_Player.completed");
			sendBroadcast(i);
		}
	};
		
	
	public void onCreate() {
		MPlayer = MediaPlayer.create(this, R.raw.mayday);
		super.onCreate();
	}
	
	public class LocalBinder extends Binder{
		public Service_Player getService(){
			return Service_Player.this;
		}
	}
	
	public void onStart(Intent intent, int startId) {
		MPlayer.start();
	}

	public void onDestroy() {
		super.onDestroy();
		MPlayer.stop();
	}

	public IBinder onBind(Intent arg0) {
		return null;
	}
	
}








