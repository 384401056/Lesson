package com.example.activity01;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * ����ʹ��Activity��Intent��ϴ���ֵ�ķ�ʽ��
 * 
 * @author Administrator
 * @since 2014-7-12
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		/*
		 * ��ȻActivityʵ������һ��window��������ͼ�������Ǿ���window��
		 * ��Ȼlayout�е�XML����inflaterѹ�����ͼ�ģ������Ǿ���inflater.
		 * 
		 * ����Ĵ����� setContentView(R.layout.activity_main);�Ĺ�����ͬ��
		 * ˵����Activity��window֮��Ĺ�ϵ��
		 */
		this.getWindow().setContentView(
				this.getLayoutInflater().inflate(R.layout.activity_main, null));

		Button btn01 = (Button) findViewById(R.id.button1);
		btn01.setText("TextChanged");

		/*
		 * ��̬��ӽ��������
		 */
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		
		
		Button btn02 = new Button(this);
		btn02.setText("Phone ACTION_DIAL");
		ll.addView(btn02, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		
		Button btn03 = new Button(this);
		btn03.setText("Phone ACTION_CALL");
		ll.addView(btn03, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	
		

		/*
		 * ����¼��� 1.�¼����⣬�������� 2.�¼��ļ����������á� 3.�¼����߼����롣
		 */
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				/*
				 * ������Activity��������ֵ��
				 */
				Intent intent = new Intent(MainActivity.this,RoseActivity.class);
				intent.putExtra("name", "BinBin");
				
//				startActivity(intent);
				
				/*
				 * ����һ���з���ֵ��Activity.
				 * ��ʵ��AndroidԴ���У�startActivityҲ�ǵ��õ�startActivityForResult
				 * ֻ����requestCodeΪ-1.
				 */
				startActivityForResult(intent, 123);
			}
		});

		
		/**
		 * ʵ�ִ�绰���ܵİ�ť1�¼�1��
		 * ʹ�õ�Intent Action�� ACTION_DIAL
		 * ֻ�Ǵ򿪴�绰�Ľ��棬�������˵绰���롣��û�в���绰��
		 */
		btn02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_dial =new Intent();
				intent_dial.setAction(Intent.ACTION_DIAL);
				intent_dial.setData(Uri.parse("tel:13658860150"));
				startActivity(intent_dial);
			}

		});
		
		/**
		 * ʵ�ִ�绰���ܵİ�ť2�¼���
		 * ʹ�õ�Intent Action�� ACTION_CALL
		 * ֱ�Ӳ���绰���룬����ʹ��ACTION_CALL��Ҫ��Manifest�����ļ��������û�Ȩ�ޡ�
		 */
		btn03.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_call = new Intent();
				intent_call.setAction(Intent.ACTION_CALL);
				intent_call.setData(Uri.parse("tel:13658860150"));
				startActivity(intent_call);
			}
		});
	}

	/**
	 * ǰһ��Activity�з���ֵʱ�ͻᱻ���á�
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		//�жϷ��ص�Codeֵ����ȡ��Intent��������ֵ��
		if(resultCode==321){
			String strResult = data.getExtras().getString("name");
			Toast.makeText(this, strResult, Toast.LENGTH_LONG).show();
		}
		
	}
		
}

























