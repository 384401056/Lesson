package com.example.lesson_sqlite_accessprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

public class AccessProviderTest extends AndroidTestCase {
	private static final String TAG = "AccessProviderTest------------------->";
	
	/*
	 * ��ѯ����.
	 */
	public void testQuery() throws Throwable{
		/*
		 * ��ȡContentResolver����.
		 * �˶����ṩ����ContentProvider�������Ӧ���ĸ�����.insert(),delete(),update()��query().
		 */
		ContentResolver contentResolver = this.getContext().getContentResolver();
		
		//����Uri��
		Uri uri = Uri.parse("content://com.hotmail.hunterofheart.personprovider/person");
		
		//��ȡ�����α�.
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
	 * ��Ӳ���
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
	 * ɾ������
	 */
	public void testDelete() throws Throwable{
		ContentResolver contentResolver = this.getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.hotmail.hunterofheart.personprovider/person/1");
		
		contentResolver.delete(uri, null, null);
	}
	
	
	/*
	 * ���²���
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






























