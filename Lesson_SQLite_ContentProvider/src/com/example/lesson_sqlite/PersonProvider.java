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
	 * 静态代码块.
	 * 如果有些代码必须在项目启动的时候就执行的时候,需要使用静态代码块,这种代码是主动执行的;需要在项目启动的时候就初始化,
	 */
	
	static{
		matcher.addURI("com.hotmail.hunterofheart.personprovider", "person", PERSONS);
		matcher.addURI("com.hotmail.hunterofheart.personprovider", "person/#", PERSON);
	}
	
	
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(this.getContext());//实例化数据库操作类.
		return true;
	}
	

	//用于供外部应用向ContentProvider中添加数据.
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase(); //以读写方式打开数据库.
		long num;
		switch (matcher.match(uri)) {
		case PERSONS:
			num = db.insert("person", "personid", values);
			return ContentUris.withAppendedId(uri, num);
			
		case PERSON:
			num = db.insert("person", "personid", values);
			String struri = uri.toString();
			
			//截取Uri最后一个"/"前面的字符串.并用withAppendedId给Uri路径加上ID.
			return ContentUris.withAppendedId(Uri.parse(struri.substring(0,struri.lastIndexOf('/'))), num);
			
		default:
			throw new IllegalArgumentException("Unknown Uri"+uri); //告诉用户传入了一个非法的Uri.
		}
	}
	
	//用于供外部应用更新ContentProvider中的数据
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase(); //以读写方式打开数据库.
		switch (matcher.match(uri)) {
		case PERSONS:

			return db.update("person", values, selection, selectionArgs);

		case PERSON:
			long personid = ContentUris.parseId(uri);
			String whereClause = "personid=" + personid; //从Uri中提取id.
			
			if(selection!=null && !"".equals(selection.trim())){
				whereClause = whereClause + "and" + selection;
			}
			return db.update("person", values, whereClause, selectionArgs);
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
	}
	
	
	//删除数据。
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
	 * 获取数据
	 * 参数列表说明：
	 * 1.Uri.
	 * 2.游标中各列的名称.
	 * 3.where的选择条件.
	 * 3.选择条件的参数.
	 * 4.如何排序.
	 */
	public Cursor query(Uri uri, String[] projection , String selection , String[] selectionArgs,String sortOrder) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		switch (matcher.match(uri)) {
		case PERSONS:
			return db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
		case PERSON:
			long personid= ContentUris.parseId(uri);
			String whereClause = "personid="+personid;
			
			//如果选择条件不为空，就在选择条件上加 personid=?，起到只选择一条记录的目的。 
			if(selection!=null && !"".equals(selection.trim())){
				whereClause = whereClause + "and" + selection;
			}
			return db.query("person", projection, whereClause, selectionArgs, null, null, sortOrder);
		default:
			throw new IllegalArgumentException("Unknown Uri:"+uri);
		}
	}

	//用于返回当前Uri所代表的数据类型.返回数据如果为集合类型,则以vnd.andorid.cursor.dir/开头，如果为非集合则以vnd.android.cursor.item/开头。
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
