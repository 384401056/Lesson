/*
 * ��ȡ����ͼƬ����ͨ��ImageView��ʾ��
 */
package com.example.getinternetdata;

import com.example.net.util.NetTool;//���빤�߰�

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
 * ��ȡ�����ϵ�ͼƬ.����ImageView����ʾ��
 */
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	//�������ֻ�������߳��У�����Ҫ��Handler���������̵߳õ������ݡ�
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
				pb01.setVisibility(View.VISIBLE); //��ʾ������.
				Thread thread = new Thread(new GetData());
				thread.start();
			}
		});
		
	}
	
	//ͨ����Ϣ���и�������
	class MyHandler extends Handler{
		public void handleMessage(Message msg) {
			byte[] data = (byte[])msg.obj;
			Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
			iv01.setImageBitmap(bm); //��ʾͼƬ.
			pb01.setVisibility(View.GONE); //�رս�������
		}
		
	}
	
	//��ȡ�������ݡ�
	class  GetData implements Runnable{
		public void run() {
			try{
				Message msg = new Message();
				
				//���ù��߰��еķ��������������ݡ�URL����EditView�е��ı�.
				msg.obj = NetTool.getImage(et01.getText().toString().trim()); 
				myhandler.sendMessage(msg);
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
