package com.example.lesson_sqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {

	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int PERSONS = 1;
 	private static final int PERSON = 2;
	private DatabaseHelper dbHelper;
	
	/*
	 * ��̬�����.
	 * �����Щ�����������Ŀ������ʱ���ִ�е�ʱ��,��Ҫʹ�þ�̬�����,���ִ���������ִ�е�;��Ҫ����Ŀ������ʱ��ͳ�ʼ��,
	 */
	
	static{
		matcher.addURI("com.hotmail.hunterofheart.personprovider", "person", PERSONS);
		matcher.addURI("com.hotmail.hunterofheart.personprovider", "person/#", PERSON);
	}
	
	
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(this.getContext());//ʵ�������ݿ������.
		return true;
	}
	

	//���ڹ��ⲿӦ����ContentProvider���������.
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase(); //�Զ�д��ʽ�����ݿ�.
		long num;
		switch (matcher.match(uri)) {
		case PERSONS:
			num = db.insert("person", "personid", values);
			return ContentUris.withAppendedId(uri, num);
			
		case PERSON:
			num = db.insert("person", "personid", values);
			String struri = uri.toString();
			
			//��ȡUri���һ��"/"ǰ����ַ���.����withAppendedId��Uri·������ID.
			return ContentUris.withAppendedId(Uri.parse(struri.substring(0,struri.lastIndexOf('/'))), num);
			
		default:
			throw new IllegalArgumentException("Unknown Uri"+uri); //�����û�������һ���Ƿ���Uri.
		}
	}
	
	//���ڹ��ⲿӦ�ø���ContentProvider�е�����
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase(); //�Զ�д��ʽ�����ݿ�.
		switch (matcher.match(uri)) {
		case PERSONS:

			return db.update("person", values, selection, selectionArgs);

		case PERSON:
			long personid = ContentUris.parseId(uri);
			String whereClause = "personid=" + personid; //��Uri����ȡid.
			
			if(selection!=null && !"".equals(selection.trim())){
				whereClause = whereClause + "and" + selection;
			}
			return db.update("person", values, whereClause, selectionArgs);
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
	}
	
	
	//ɾ�����ݡ�
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		switch (matcher.match(uri)) {
		case PERSONS:
			return db.delete("person", selection, selectionArgs);
		case PERSON:
			long personid = ContentUris.parseId(uri);
			String whereClause = "personid=" + personid;
			
			if(selection!=null && !"".equals(selection.trim())){
				whereClause = whereClause + "and" + selection;
			}
			return db.delete("person", whereClause, selectionArgs);
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
	}
	

	/*
	 * ��ȡ����
	 * �����б�˵����
	 * 1.Uri.
	 * 2.�α��и��е�����.
	 * 3.where��ѡ������.
	 * 3.ѡ�������Ĳ���.
	 * 4.�������.
	 */
	public Cursor query(Uri uri, String[] projection , String selection , String[] selectionArgs,String sortOrder) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		switch (matcher.match(uri)) {
		case PERSONS:
			return db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
		case PERSON:
			long personid= ContentUris.parseId(uri);
			String whereClause = "personid="+personid;
			
			//���ѡ��������Ϊ�գ�����ѡ�������ϼ� personid=?����ֻѡ��һ����¼��Ŀ�ġ� 
			if(selection!=null && !"".equals(selection.trim())){
				whereClause = whereClause + "and" + selection;
			}
			return db.query("person", projection, whereClause, selectionArgs, null, null, sortOrder);
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
	}

	//���ڷ��ص�ǰUri���������������.�����������Ϊ��������,����vnd.andorid.cursor.dir/��ͷ�����Ϊ�Ǽ�������vnd.android.cursor.item/��ͷ��
	public String getType(Uri uri) {
		switch (matcher.match(uri)) {
		case PERSONS:
			return "vnd.android.cursor.dir/personprovider.persons";
		case PERSON:
			return "vnd.android.cursor.item/personprovider.person";
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
	}

}
