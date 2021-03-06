package com.example.DBhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static final String Name = "donwloadData.db";//数据库文件名.
	private static final int version = 1;
	
	//自定义构造函数.
	public DBOpenHelper(Context context) {
		super(context, Name, null, version);
	}

	//此方法在数据库初次被创建时调用.
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("Create Table if not exists filedown (id integer primary key autoincrement,downpath varchar(100),threadid integer,position integer)");
	}

	//当数据库version改变时，此方法会被调用.
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//此处当更新数据时，会直接删除原来的数据库，并重建。在实际应用中是不能这么做的，要先备份用户数据，再导入新表。
		db.execSQL("Drop Table if exists filedown");
		onCreate(db);
	}

}
