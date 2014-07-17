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
	 * 插入数据。
	 */
	public void save(Person person){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		//db.execSQL("insert into person(name,age) values(?,?)",new Object[] {person.getName(),person.getAge()});
		//db.execSQL("insert into person(name,age) values('"+ person.getName() +"',"+ person.getAge() +")"); 
		
		db.beginTransaction(); //开始事务，此处后面的代码，如果有一条出错，则前面的也不会被执行.这些语句只能一起成功，或者一起失败。
		try{
			
			db.execSQL("insert into person(name,age) values(?,?)",new Object[] {"张三",123});
			db.execSQL("insert into personddddfff(name,age) values(?,?)",new Object[] {"李四",567});
			
		}catch(Exception e){
			
		}
		db.endTransaction();  //结束事务.
	}
	
	/*
	 * 更新数据.
	 */
	public void update(Person person){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("update person set name=?,age=? where personid=?",new Object[]{person.getName(),person.getAge(),person.getID()});
	}
	
	/*
	 * 查找数据.
	 */
	public Person find(Integer id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		//创建游标.
		Cursor cursor = db.rawQuery("select personid,name,age from person where personid=?", new String[]{String.valueOf(id)});
		
		if(cursor.moveToNext()){
			Person person = new Person();
			
			//通过游标得到各字段的值.
			person.setID(cursor.getInt(cursor.getColumnIndex("personid")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			
			return person;
		}
		cursor.close();//关闭游标.
		return null;
	}
	
	/*
	 * 删除数据.
	 */
	public void delete(Integer id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		db.execSQL("delete from person where personid=?",new Object[]{id});
	}
	
	
	/*
	 * 分页.
	 * 参数1：从哪条数据开始。
	 * 参数2：一次显示多少条数据.
	 */
	public List<Person> getScrollData(int firstResult,int maxResult){
		
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select personid,name,age from person limit ?,?",new String[]{String.valueOf(firstResult),
				String.valueOf(maxResult)});
		
		while (cursor.moveToNext()) {
			Person person = new Person();

			//通过游标得到各字段的值.
			person.setID(cursor.getInt(cursor.getColumnIndex("personid")));
			person.setName(cursor.getString(cursor.getColumnIndex("name")));
			person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			persons.add(person);
		}
		cursor.close();
		return persons;
	}
	
	/*
	 * 获取记录总数.
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




































