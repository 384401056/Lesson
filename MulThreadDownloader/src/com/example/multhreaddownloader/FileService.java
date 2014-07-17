/*
 * 将下文件的数据信息保存到SQLite中.
 */

package com.example.multhreaddownloader;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.DBhelper.DBOpenHelper;

public class FileService {
	
	private DBOpenHelper dbhelper;
	
	//构造函数.
	public FileService(Context context){
		dbhelper = new DBOpenHelper(context);
	}
	
	/**
	 * 返回数据库数据.
	 * @param path
	 * @return
	 */
	public Map<Integer,Integer> getData(String path){
		SQLiteDatabase db = dbhelper.getReadableDatabase();//创建数据库对象.
		Cursor cursor = db.rawQuery("select threadid,position from filedown where downpath = ?",new String[]{path});
		
		Map<Integer,Integer> data = new HashMap<Integer, Integer>();
		
		while(cursor.moveToNext()){
			data.put(cursor.getInt(0), cursor.getInt(1)); //用key来存threadid,用值来存position。
		}
		cursor.close();
		db.close();
		return data;
	}
	
	
	/**
	 * 保存下载线程初始位置.
	 * @param path
	 * @param map
	 */
	public void save(String path,Map<Integer,Integer> map){
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		db.beginTransaction(); //开始事务，此处后面的代码，如果有一条出错，则前面的也不会被执行.这些语句只能一起成功，或者一起失败。
		try{
			
			for(Map.Entry<Integer, Integer> entry:map.entrySet()){
				db.execSQL("insert into filedown(downpath,threadid,position) values(?,?,?)",new Object[] {path,entry.getKey(),entry.getValue()});
			}
			db.setTransactionSuccessful();//标记当前事务是成功的
			
		}finally{
			db.endTransaction(); //结束事务
		}
		db.close();
	}
	
	/**
	 * 实时更新线程的下载位置。
	 * @param path
	 * @param map
	 */
	public void update(String path, Map<Integer,Integer> map){
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		db.beginTransaction();
		
		try{
			for(Map.Entry<Integer, Integer> entry:map.entrySet()){
				db.execSQL("update filedown set position=? where downpath=? and threadid=?",new Object[] {entry.getValue(),path,entry.getKey()});
			}
			db.setTransactionSuccessful();//标记当前事务是成功的
			
		}finally{
			db.endTransaction(); //结束事务
		}
		db.close();
	}
	
	/**
	 * 下载完成后删除该文件对应的下载记录。
	 * @param path
	 */
	public void delete(String path){
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		db.execSQL("delete from filedown where downpath=?",new Object[]{path});
		db.close();
	}
	
}



























