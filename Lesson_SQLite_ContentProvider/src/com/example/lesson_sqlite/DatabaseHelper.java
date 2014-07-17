package com.example.lesson_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String Name = "sharp.db";//���ݿ��ļ���.
	private static final int version = 1;
	
	//�Զ��幹�캯��.
	public DatabaseHelper(Context context) {
		super(context, Name, null, version);
	}

	//�˷��������ݿ���α�����ʱ����.
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("Create Table person (personid integer primary key autoincrement,name varchar(20),age integer)");
	}

	//�����ݿ�version�ı�ʱ���˷����ᱻ����.
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//�˴�����������ʱ����ֱ��ɾ��ԭ�������ݿ⣬���ؽ�����ʵ��Ӧ�����ǲ�����ô���ģ�Ҫ�ȱ����û����ݣ��ٵ����±�
		db.execSQL("Drop Table if exists person");
		onCreate(db);
	}
}
