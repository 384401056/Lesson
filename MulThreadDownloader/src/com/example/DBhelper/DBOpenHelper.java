package com.example.DBhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static final String Name = "donwloadData.db";//���ݿ��ļ���.
	private static final int version = 1;
	
	//�Զ��幹�캯��.
	public DBOpenHelper(Context context) {
		super(context, Name, null, version);
	}

	//�˷��������ݿ���α�����ʱ����.
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("Create Table if not exists filedown (id integer primary key autoincrement,downpath varchar(100),threadid integer,position integer)");
	}

	//�����ݿ�version�ı�ʱ���˷����ᱻ����.
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//�˴�����������ʱ����ֱ��ɾ��ԭ�������ݿ⣬���ؽ�����ʵ��Ӧ�����ǲ�����ô���ģ�Ҫ�ȱ����û����ݣ��ٵ����±�
		db.execSQL("Drop Table if exists filedown");
		onCreate(db);
	}

}
