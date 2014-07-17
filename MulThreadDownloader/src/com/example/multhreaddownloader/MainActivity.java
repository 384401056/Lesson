package com.example.multhreaddownloader;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView resultView;
	private EditText edPath;
	private Button btnDownload;
	private ProgressBar pb01;
	private MyHandler myhandler = new MyHandler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		resultView=(TextView)findViewById(R.id.textView1);
		edPath=(EditText)findViewById(R.id.editText1);
		btnDownload=(Button)findViewById(R.id.button1);
		pb01=(ProgressBar)findViewById(R.id.progressBar1);
		
		btnDownload.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					try {
						//download(edPath.getText().toString(),Environment.getExternalStorageDirectory());
						download("http://dldir1.qq.com/music/clntupate/QQMusic_Setup_2014.exe",Environment.getExternalStorageDirectory());
						
					} catch (Exception e) {
						Toast.makeText(MainActivity.this, R.string.fail, 1).show();
					}
				}
				else{
					Toast.makeText(MainActivity.this, R.string.sdError, 1);
				}
			}
		});

	}
	
	
	/**
	 * ��ʼ�����ļ���
	 * @param path �����ļ���·����
	 * @param saveDir �����ļ���·����
	 * @throws Exception
	 */
	private void download(final String path, final File saveDir) throws Exception{
		new Thread(new Runnable() {
			public void run() {
				try{
					FileDownloader downloader = new FileDownloader(MainActivity.this, path, saveDir, 3);
					pb01.setMax(downloader.getFileSize()); //��progressBar�����ֵ��ΪҪ�����ļ��Ĵ�С.

					//��ʼ�����ļ�.
					downloader.download(new DownloadProgressListener() {
						//�ļ����ع�����ͨ��Message�����Ѿ������ļ���С�����ݸ�handler.
						public void onDownloadSize(int size) {
							Message msg = new Message();
							msg.what = 1;
							msg.getData().putInt("size", size);
							myhandler.sendMessage(msg);
						}
					});
				}catch(Exception e){
					Message msg = new Message();
					msg.what = -1;
					msg.getData().putString("error", "����ʧ��");
					myhandler.sendMessage(msg);
				}
			}
		}).start();
	}
	

	class MyHandler extends Handler{
		public void handleMessage(Message msg) {
			if(!Thread.currentThread().isInterrupted()){
				switch (msg.what) {
				case 1:
					int size = msg.getData().getInt("size");
					pb01.setProgress(size);
					int result = (int)(((float)pb01.getProgress()/(float)pb01.getMax())*100);
					resultView.setText(result +"%");
					
					//�������������,����ʾ���سɹ���
					if(pb01.getMax()==pb01.getProgress()){
						Toast.makeText(MainActivity.this, R.string.success, 1).show();
					}
					break;
				case -1:
					String error = msg.getData().getString("error");
					Toast.makeText(MainActivity.this, error, 1).show();
					break;
				default:
					break;
				}
			}
		}
		
	}

}
