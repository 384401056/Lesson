/*
 * �����������ṩ�ߵ�SQLite_ListView ʹ�û���Lesson_SQLite�е����ݿ⡣
 */
package com.example.lesson_sqlite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private ListView lv01 = null;
	private PersonService ps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv01 = (ListView)findViewById(R.id.listView1);
		lv01.setOnItemClickListener(new MyListViewListener());
		
		ps = new PersonService(this);
		List<Person> persons = ps.getScrollData(0, 100); //ǰ100������.

		List<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
		
//		HashMap<String,String> title = new HashMap<String, String>();
//		title.put("personid", "���");
//		title.put("name", "����");
//		title.put("age", "����");
//		data.add(title); //�б����.
		
		for(Person person:persons){
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("personid", String.valueOf(person.getID()));
			map.put("name", person.getName());
			map.put("age", String.valueOf(person.getAge()));
			
			data.add(map); //��������.
		}
		
		
		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, data, R.layout.personitem, new String[]{"personid","name","age"},
				new int[]{R.id.personid,R.id.name,R.id.age});
		
		lv01.setAdapter(adapter);
		
	}
	
	/*
	 * ListView����¼�������.
	 */
	class MyListViewListener implements OnItemClickListener{
		
		/*
		 * ����˵���� 
		 * parent����ListView; 
		 * view��ʾÿһ��������Ǹ�Ԫ�أ���RelativeLayout;
		 * position��ʾÿһ��������adapter�е�λ�ñ��;
		 * id��ʾÿһ����ListView�е�λ�ñ�š�
		 */
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			ListView listview = (ListView)parent;
			
			//��ȡ���嵥�е�ָ��λ������������ݡ�
			HashMap<String, String> itemData = (HashMap<String, String>)lv01.getItemAtPosition(position);
			String personid = itemData.get("personid");
			String name = itemData.get("name");
			String age = itemData.get("age");
			
			System.out.println("personid:"+personid + " name:"+name+" age:"+age);
		}
		
	}
	
}




















