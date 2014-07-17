package com.example.lesson_sqlite_accessprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

public class AccessProviderTest extends AndroidTestCase {
	private static final String TAG = "AccessProviderTest------------------->";
	
	/*
	 * 查询测试.
	 */
	public void testQuery() throws Throwable{
		/*
		 * 获取ContentResolver对象.
		 * 此对象提供了与ContentProvider对象相对应的四个方法.insert(),delete(),update()和query().
		 */
		ContentResolver contentResolver = this.getContext().getContentResolver();
		
		//定义Uri。
		Uri uri = Uri.parse("content://com.hotmail.hunterofheart.personprovider/person");
		
		//获取数据游标.
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		
		while(cursor.moveToNext()){
			int idIndex = cursor.getColumnIndex("personid");
			int nameIndex = cursor.getColumnIndex("name");
			int ageIndex = cursor.getColumnIndex("age");
			
			System.out.println(TAG + "PersonID = "+ cursor.getInt(idIndex)
					+", name = "+cursor.getString(nameIndex)
					+", age="+cursor.getInt(ageIndex));
		}
		cursor.close();
	}
	
	/*
	 * 添加测试
	 */
	public void testInsert() throws Throwable{
		ContentResolver contentResolver = this.getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.hotmail.hunterofheart.personprovider/person");
		
		ContentValues values = new ContentValues();
		values.put("name", "Gstar");
		values.put("age", 10000);
		
		contentResolver.insert(uri, values);
	}
	
	/*
	 * 删除测试
	 */
	public void testDelete() throws Throwable{
		ContentResolver contentResolver = this.getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.hotmail.hunterofheart.personprovider/person/1");
		
		contentResolver.delete(uri, null, null);
	}
	
	
	/*
	 * 更新测试
	 */
	public void testUpdate() throws Throwable{
		ContentResolver contentResolver = this.getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.hotmail.hunterofheart.personprovider/person/9");
		
		ContentValues values = new ContentValues();
		values.put("name", "gaoyanbin");
		values.put("age", 99999);
		
		contentResolver.update(uri, values, null, null);
		
	}
	
}






























