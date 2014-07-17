/*
 * 获取网络图片，并通过ImageView显示。
 */
package com.example.getinternetdata;

import com.example.net.util.NetTool;//导入工具包

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/*
 * 获取网络上的图片.并在ImageView中显示。
 */
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	//网络访问只能在子线程中，的以要用Handler来传递子线程得到的数据。
	private MyHandler myhandler = new MyHandler();
	
	private Button btn01 = null;
	private ImageView iv01 = null;
	private EditText et01 = null;
	private ProgressBar pb01 = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn01 = (Button)findViewById(R.id.button1);
		iv01 = (ImageView)findViewById(R.id.imageView1);
		et01 = (EditText)findViewById(R.id.editText1);
		pb01 = (ProgressBar)findViewById(R.id.progressBar1);
		
		btn01.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				pb01.setVisibility(View.VISIBLE); //显示进度条.
				Thread thread = new Thread(new GetData());
				thread.start();
			}
		});
		
	}
	
	//通过信息队列更新数据
	class MyHandler extends Handler{
		public void handleMessage(Message msg) {
			byte[] data = (byte[])msg.obj;
			Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
			iv01.setImageBitmap(bm); //显示图片.
			pb01.setVisibility(View.GONE); //关闭进度条。
		}
		
	}
	
	//获取网络数据。
	class  GetData implements Runnable{
		public void run() {
			try{
				Message msg = new Message();
				
				//调用工具包中的方法返回网络数据。URL来自EditView中的文本.
				msg.obj = NetTool.getImage(et01.getText().toString().trim()); 
				myhandler.sendMessage(msg);
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
