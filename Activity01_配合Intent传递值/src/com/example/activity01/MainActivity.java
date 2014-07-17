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
 * 熟练使用Activity与Intent配合传递值的方式。
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
		 * 既然Activity实际上是一个window来呈现视图，那我们就用window。
		 * 既然layout中的XML是用inflater压入成视图的，那我们就用inflater.
		 * 
		 * 下面的代码与 setContentView(R.layout.activity_main);的功能相同，
		 * 说明了Activity和window之间的关系。
		 */
		this.getWindow().setContentView(
				this.getLayoutInflater().inflate(R.layout.activity_main, null));

		Button btn01 = (Button) findViewById(R.id.button1);
		btn01.setText("TextChanged");

		/*
		 * 动态添加界面组件。
		 */
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		
		
		Button btn02 = new Button(this);
		btn02.setText("Phone ACTION_DIAL");
		ll.addView(btn02, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		
		Button btn03 = new Button(this);
		btn03.setText("Phone ACTION_CALL");
		ll.addView(btn03, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	
		

		/*
		 * 添加事件： 1.事件主题，发生对象。 2.事件的监听函数设置。 3.事件的逻辑代码。
		 */
		btn01.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				/*
				 * 启动新Activity，并传入值。
				 */
				Intent intent = new Intent(MainActivity.this,RoseActivity.class);
				intent.putExtra("name", "BinBin");
				
//				startActivity(intent);
				
				/*
				 * 启动一个有返回值的Activity.
				 * 其实在Android源码中，startActivity也是调用的startActivityForResult
				 * 只不过requestCode为-1.
				 */
				startActivityForResult(intent, 123);
			}
		});

		
		/**
		 * 实现打电话功能的按钮1事件1。
		 * 使用的Intent Action是 ACTION_DIAL
		 * 只是打开打电话的界面，并输入了电话号码。并没有拨打电话。
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
		 * 实现打电话功能的按钮2事件。
		 * 使用的Intent Action是 ACTION_CALL
		 * 直接拨打电话号码，所以使用ACTION_CALL需要在Manifest配置文件中设置用户权限。
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
	 * 前一个Activity有返回值时就会被调用。
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		//判断返回的Code值，并取出Intent传过来的值。
		if(resultCode==321){
			String strResult = data.getExtras().getString("name");
			Toast.makeText(this, strResult, Toast.LENGTH_LONG).show();
		}
		
	}
		
}

























