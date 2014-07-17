/*
 * ≤‚ ‘PersonService¿‡.
 */
package com.example.lesson_sqlite;

import java.util.List;

import android.test.AndroidTestCase;

public class PersonServiceTest extends AndroidTestCase {
	
	//¥Ê¥¢≤‚ ‘°£
	public void testSave() throws Throwable{
		PersonService ps = new PersonService(this.getContext());
		Person person = new Person("kkkoioeeew", 4578);
		ps.save(person);
	}
	
	//∏¸–¬≤‚ ‘°£
	public void testUpdate() throws Throwable{
		PersonService ps = new PersonService(this.getContext());
		Person person = new Person();
		person.setID(1);
		person.setName("kkkkkkkkkkkk");
		person.setAge(999);
		
		ps.update(person);
	}
	
	//≤È’“≤‚ ‘
	public void testFind() throws Throwable{
		PersonService ps = new PersonService(this.getContext());
		Person person = ps.find(2);
		System.out.println("PersonService------------->"+person.toString());
	}
	
	//◊‹ ˝≤‚ ‘°£
	public void testCount() throws Throwable{
		PersonService ps = new PersonService(this.getContext());
		System.out.println("PersonService------------>"+ ps.getCount());
	}
	
	//∑÷“≥≤‚ ‘°£
	public void testGetScrollData(){
		PersonService ps = new PersonService(this.getContext());
		List<Person> persons = ps.getScrollData(0, 3);
		
		for(Person person:persons){
			System.out.println("PersonService--------------->"+person.toString());
		}
	}
	
	//…æ≥˝≤‚ ‘
	public void testDelete(){
		PersonService ps = new PersonService(this.getContext());
		ps.delete(11);
	}
}




















