/*
 * SharedPreferences存储类的读写.一般使用它来保存软件配置参数。
 */
package com.example.lesson_sharedpreferences;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etName = null;
	EditText etAge = null;
	Button btn01 = null;
	Button btn02 = null;
	TextView tvRead = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etName = (EditText)findViewById(R.id.etName);
		etAge = (EditText)findViewById(R.id.etAge);
		tvRead = (TextView)findViewById(R.id.tvRead);
		
		btn01 = (Button)findViewById(R.id.button1);
		btn02 = (Button)findViewById(R.id.button2);
		
		btn01.setOnClickListener(new MyOnClickListener());
		btn02.setOnClickListener(new MyOnClickListener());
		
	}
	
	
	class MyOnClickListener implements OnClickListener{
		public void onClick(View v) {
			SharedPreferences sp = getSharedPreferences("sharpandroid",Context.MODE_PRIVATE);
			Button myButton = (Button)v;
			
			//根据不同的按钮执行不同的case过程.
			switch (myButton.getId()) {
			case R.id.button1:
				//获取SharedPreferences的编辑器.
				Editor editor = sp.edit();
				editor.putString("name", etName.getText().toString());
				editor.putInt("age", Integer.parseInt(etAge.getText().toString()));
				editor.commit();//提交数据.
				Toast.makeText(MainActivity.this, "数据保存成功！", Toast.LENGTH_LONG).show();
				break;
			case R.id.button2:
				sp.getString("name", null);
				sp.getInt("age", 0);
				tvRead.setText(sp.getString("name", null)+sp.getInt("age", 0));
				break;
			default:
				break;
			}
		}
		
	}
	
	
	
}
