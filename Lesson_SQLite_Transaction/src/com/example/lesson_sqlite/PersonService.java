package com.example.lesson_sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonService {
	
	private DatabaseHelper dbHelper;
	private Context context;
	
	public PersonService(Context context){
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	/*
	 * �������ݡ�
	 */
	public void save(Person person){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		//db.execSQL("insert into person(name,age) values(?,?)",new Object[] {person.getName(),person.getAge()});
		//db.execSQL("insert into person(name,age) values('"+ person.getName() +"',"+ person.getAge() +")"); 
		
		db.beginTransaction(); //��ʼ���񣬴˴�����Ĵ��룬�����һ��������ǰ���Ҳ���ᱻִ��.��Щ���ֻ��һ��ɹ�������һ��ʧ�ܡ�
		try{
			
			db.execSQL("insert into person(name,age) values(?,?)",new Object[] {"����",123});
			db.execSQL("insert into personddddfff(name,age) values(?,?)",new Object[] {"����",567});
			
		}catch(Exception e){
			
		}
		db.endTransaction();  //��������.
	}
	
	/*
	 * ��������.
	 */
	public void update(Person person){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("update person set name=?,age=? where personid=?",new Object[]{person.getName(),person.getAge(),person.getID()});
	}
	
	/*
	 * ��������.
	 */
	public Person find(Integer id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		//�����α�.
		Cursor cursor = db.rawQuery("select personid,name,age from person where personid=?", new String[]{String.valueOf(id)});
		
		if(cursor.moveToNext()){
			Person person = new Person();
			
			//ͨ���α�õ����ֶε�ֵ.
			person.setID(cursor.getInt(cursor.getColumnIndex("personid")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			
			return person;
		}
		cursor.close();//�ر��α�.
		return null;
	}
	
	/*
	 * ɾ������.
	 */
	public void delete(Integer id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		db.execSQL("delete from person where personid=?",new Object[]{id});
	}
	
	
	/*
	 * ��ҳ.
	 * ����1�����������ݿ�ʼ��
	 * ����2��һ����ʾ����������.
	 */
	public List<Person> getScrollData(int firstResult,int maxResult){
		
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select personid,name,age from person limit ?,?",new String[]{String.valueOf(firstResult),
				String.valueOf(maxResult)});
		
		while (cursor.moveToNext()) {
			Person person = new Person();

			//ͨ���α�õ����ֶε�ֵ.
			person.setID(cursor.getInt(cursor.getColumnIndex("personid")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			persons.add(person);
		}
		cursor.close();
		return persons;
	}
	
	/*
	 * ��ȡ��¼����.
	 */
	public long getCount(){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		cursor.close();
		return count;
	}
	
}




































