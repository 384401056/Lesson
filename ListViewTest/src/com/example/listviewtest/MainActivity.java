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
			 map.put("lvw_custom_name", "item����");
			 map.put("lvw_custom_description", "item����");
			 
			 items.add(map);
		 }
		 
		 SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.listview, 
	                new String[] {"lvw_custom_img","lvw_custom_name","lvw_custom_description"}, 
	                new int[] {R.id.lvw_custom_img, R.id.lvw_custom_name, R.id.lvw_custom_description});
		
		 lv.setAdapter(adapter);
		 
		/*
		 * ����ListView������.
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
//			//ͼƬ��keyֵ�������ȡ��ӳ���ϵ����ʵ����Adapterʱ���壬����ϲ����key�벼���ļ��ж����idȡͬ����ֵ            
//			//valueֵΪͼƬ����Դid���������͵�ֵ��
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("ivState", R.drawable.sensor_2);
//			map.put("tvSerialId",i+"��" );
//			map.put("tv01", "ʵʱ���ݣ�");
//			map.put("tv02", "�������ݣ�");
//			map.put("tv03", "����λ�ã�");
//			map.put("tempStr", "��"+100);
//			map.put("humStr", 100+"%");
//			map.put("location", "һ������"+i+"��ƽ̨");
//			
//			items.add(map);
//		}
//		
//		
//		//************************************************************************        
//		//* newһ��SimpleAdapter     
//		//* itemsΪ���ݼ���       
//		//* R.layout.lvw_customΪ�Զ����ListView�����ļ�   
//		//* ���ĸ�����Ϊmap�е�key����  
//		//* ���������Ϊ�Զ��岼���ļ��пռ����Դid���ϣ�����ĸ�����Ҫһһ��Ӧ *        
//		//************************************************************************
//		SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.listview, element1, element2);
//		
//		
//		lv.setAdapter(adapter);
//		
	}


}
