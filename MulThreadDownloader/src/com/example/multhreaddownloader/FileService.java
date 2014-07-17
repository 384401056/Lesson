/*
 * �����ļ���������Ϣ���浽SQLite��.
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
	
	//���캯��.
	public FileService(Context context){
		dbhelper = new DBOpenHelper(context);
	}
	
	/**
	 * �������ݿ�����.
	 * @param path
	 * @return
	 */
	public Map<Integer,Integer> getData(String path){
		SQLiteDatabase db = dbhelper.getReadableDatabase();//�������ݿ����.
		Cursor cursor = db.rawQuery("select threadid,position from filedown where downpath = ?",new String[]{path});
		
		Map<Integer,Integer> data = new HashMap<Integer, Integer>();
		
		while(cursor.moveToNext()){
			data.put(cursor.getInt(0), cursor.getInt(1)); //��key����threadid,��ֵ����position��
		}
		cursor.close();
		db.close();
		return data;
	}
	
	
	/**
	 * ���������̳߳�ʼλ��.
	 * @param path
	 * @param map
	 */
	public void save(String path,Map<Integer,Integer> map){
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		db.beginTransaction(); //��ʼ���񣬴˴�����Ĵ��룬�����һ��������ǰ���Ҳ���ᱻִ��.��Щ���ֻ��һ��ɹ�������һ��ʧ�ܡ�
		try{
			
			for(Map.Entry<Integer, Integer> entry:map.entrySet()){
				db.execSQL("insert into filedown(downpath,threadid,position) values(?,?,?)",new Object[] {path,entry.getKey(),entry.getValue()});
			}
			db.setTransactionSuccessful();//��ǵ�ǰ�����ǳɹ���
			
		}finally{
			db.endTransaction(); //��������
		}
		db.close();
	}
	
	/**
	 * ʵʱ�����̵߳�����λ�á�
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
			db.setTransactionSuccessful();//��ǵ�ǰ�����ǳɹ���
			
		}finally{
			db.endTransaction(); //��������
		}
		db.close();
	}
	
	/**
	 * ������ɺ�ɾ�����ļ���Ӧ�����ؼ�¼��
	 * @param path
	 */
	public void delete(String path){
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		db.execSQL("delete from filedown where downpath=?",new Object[]{path});
		db.close();
	}
	
}



























