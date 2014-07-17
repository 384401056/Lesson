package com.example.listviewtest;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;

public class MainActivity extends Activity {

	private ListView lv  = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView)findViewById(R.id.listView1);
		
		ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
		 for(int i = 0; i < 5; i++) {
			 HashMap<String, Object> map = new HashMap<String, Object>();
			 map.put("lvw_custom_img", R.drawable.sensor_2);
			 map.put("lvw_custom_name", "item名称");
			 map.put("lvw_custom_description", "item描述");
			 
			 items.add(map);
		 }
		 
		 SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.listview, 
	                new String[] {"lvw_custom_img","lvw_custom_name","lvw_custom_description"}, 
	                new int[] {R.id.lvw_custom_img, R.id.lvw_custom_name, R.id.lvw_custom_description});
		
		 lv.setAdapter(adapter);
		 
		/*
		 * 生成ListView的数据.
		 */
//		ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
//		
//		String[] element1 = new String[]{
//				"ivState",
//				"tvSerialId",
//				"tv01",
//				"tv02",
//				"tv03",
//				"tempStr",
//				"humStr",
//				"location"
//		};
//		
//		int[] element2 = new int[]{
//				R.id.ivState, 
//				R.id.tvSerialId, 
//				R.id.tv01,
//				R.id.tv02,
//				R.id.tv03,
//				R.id.tempStr,
//				R.id.tempStr,
//				R.id.humStr,
//				R.id.location	
//		};
//		
//		for(int i=0;i<10;i++){
//			
//			//图片，key值可以随便取，映射关系会在实例化Adapter时定义，但我喜欢将key与布局文件中定义的id取同样的值            
//			//value值为图片的资源id或其它类型的值。
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("ivState", R.drawable.sensor_2);
//			map.put("tvSerialId",i+"号" );
//			map.put("tv01", "实时数据：");
//			map.put("tv02", "报警数据：");
//			map.put("tv03", "所在位置：");
//			map.put("tempStr", "℃"+100);
//			map.put("humStr", 100+"%");
//			map.put("location", "一号区域"+i+"号平台");
//			
//			items.add(map);
//		}
//		
//		
//		//************************************************************************        
//		//* new一个SimpleAdapter     
//		//* items为数据集合       
//		//* R.layout.lvw_custom为自定义的ListView布局文件   
//		//* 第四个参数为map中德key集合  
//		//* 第五个参数为自定义布局文件中空间的资源id集合，与第四个参数要一一对应 *        
//		//************************************************************************
//		SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.listview, element1, element2);
//		
//		
//		lv.setAdapter(adapter);
//		
	}


}
