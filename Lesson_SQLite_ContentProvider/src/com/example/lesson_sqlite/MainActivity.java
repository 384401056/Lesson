/*
 * 增加了内容提供者的SQLite_ListView 使用还是Lesson_SQLite中的数据库。
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
		List<Person> persons = ps.getScrollData(0, 100); //前100条数据.

		List<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
		
//		HashMap<String,String> title = new HashMap<String, String>();
//		title.put("personid", "编号");
//		title.put("name", "姓名");
//		title.put("age", "年龄");
//		data.add(title); //列表标题.
		
		for(Person person:persons){
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("personid", String.valueOf(person.getID()));
			map.put("name", person.getName());
			map.put("age", String.valueOf(person.getAge()));
			
			data.add(map); //各个数据.
		}
		
		
		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, data, R.layout.personitem, new String[]{"personid","name","age"},
				new int[]{R.id.personid,R.id.name,R.id.age});
		
		lv01.setAdapter(adapter);
		
	}
	
	/*
	 * ListView点击事件监听类.
	 */
	class MyListViewListener implements OnItemClickListener{
		
		/*
		 * 参数说明： 
		 * parent就是ListView; 
		 * view表示每一项最顶级的那个元素，如RelativeLayout;
		 * position表示每一项数据在adapter中的位置编号;
		 * id表示每一项在ListView中的位置编号。
		 */
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			ListView listview = (ListView)parent;
			
			//获取与清单中的指定位置相关联的数据。
			HashMap<String, String> itemData = (HashMap<String, String>)lv01.getItemAtPosition(position);
			String personid = itemData.get("personid");
			String name = itemData.get("name");
			String age = itemData.get("age");
			
			System.out.println("personid:"+personid + " name:"+name+" age:"+age);
		}
		
	}
	
}




















