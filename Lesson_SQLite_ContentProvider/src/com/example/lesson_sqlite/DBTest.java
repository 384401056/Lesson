/*
 * 测试DatabaseHelper类.
 */
package com.example.lesson_sqlite;

import android.test.AndroidTestCase;

public class DBTest extends AndroidTestCase {
	
	public void testCreateDB() throws Throwable{
		DatabaseHelper dbHelper = new DatabaseHelper(this.getContext());
		dbHelper.getWritableDatabase();//以读写的方式打开数据库，调用此方法会导致数据的生成.并会返回一个SQLiteDatabase对象.
	}
}
