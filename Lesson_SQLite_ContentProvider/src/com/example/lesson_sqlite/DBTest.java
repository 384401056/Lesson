/*
 * ����DatabaseHelper��.
 */
package com.example.lesson_sqlite;

import android.test.AndroidTestCase;

public class DBTest extends AndroidTestCase {
	
	public void testCreateDB() throws Throwable{
		DatabaseHelper dbHelper = new DatabaseHelper(this.getContext());
		dbHelper.getWritableDatabase();//�Զ�д�ķ�ʽ�����ݿ⣬���ô˷����ᵼ�����ݵ�����.���᷵��һ��SQLiteDatabase����.
	}
}
