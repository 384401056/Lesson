package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ToDoListActivity extends Activity {

	private EditText myEditText = null;
	private ListView myListView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myEditText = (EditText) findViewById(R.id.myEditText);
		myListView = (ListView) findViewById(R.id.myListView);

		

		final List<String> todoItem = new ArrayList<String>();

		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, todoItem);

		myListView.setAdapter(aa);
		
		
		myEditText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
							todoItem.add(0, myEditText.getText().toString());
							
							aa.notifyDataSetChanged();//通知ListView绑定数据已更新。
							myEditText.setText("");
							return true;
					}
				}
				return false;
			}
		});

	}
}
