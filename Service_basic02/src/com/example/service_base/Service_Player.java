/*
 * 用Service播放后台音乐.
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

	private static final String MUSIC_COMPLETED = "com.sharpandroid.Service_Player.completed";
	
	private final IBinder mBinder = new LocalBinder();
	
	MediaPlayer MPlayer;
	
	//当音乐播放完毕后，触发事件.
	MediaPlayer.OnCompletionListener completeListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mp) {
			//发送广播.
			Intent i = new Intent(MUSIC_COMPLETED);
			sendBroadcast(i);
		}
	};
	
	
	public void onCreate() {
		MPlayer = MediaPlayer.create(this, R.raw.mayday);
		MPlayer.setOnCompletionListener(completeListener);
		super.onCreate();
	}
	

	public void onDestroy() {
		super.onDestroy();
		MPlayer.stop();
	}


	public void onStart(Intent intent, int startId) {
		MPlayer.start();
	}


	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	
	public class LocalBinder extends Binder{
		public Service_Player getService(){
			return Service_Player.this;
		}
	}
	
}








