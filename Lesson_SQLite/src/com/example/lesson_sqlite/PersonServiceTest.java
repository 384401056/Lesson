/*
 * ����PersonService��.
 */
package com.example.lesson_sqlite;

import java.util.List;

import android.test.AndroidTestCase;

public class PersonServiceTest extends AndroidTestCase {
	
	//�洢���ԡ�
	public void testSave() throws Throwable{
		PersonService ps = new PersonService(this.getContext());
		Person person = new Person("kkkoioeeew", 4578);
		ps.save(person);
	}
	
	//���²��ԡ�
	public void testUpdate() throws Throwable{
		PersonService ps = new PersonService(this.getContext());
		Person person = new Person();
		person.setID(1);
		person.setName("kkkkkkkkkkkk");
		person.setAge(999);
		
		ps.update(person);
	}
	
	//���Ҳ���
	public void testFind() throws Throwable{
		PersonService ps = new PersonService(this.getContext());
		Person person = ps.find(2);
		System.out.println("PersonService------------->"+person.toString());
	}
	
	//�������ԡ�
	public void testCount() throws Throwable{
		PersonService ps = new PersonService(this.getContext());
		System.out.println("PersonService------------>"+ ps.getCount());
	}
	
	//��ҳ���ԡ�
	public void testGetScrollData(){
		PersonService ps = new PersonService(this.getContext());
		List<Person> persons = ps.getScrollData(0, 3);
		
		for(Person person:persons){
			System.out.println("PersonService--------------->"+person.toString());
		}
	}
	
	//ɾ������
	public void testDelete(){
		PersonService ps = new PersonService(this.getContext());
		ps.delete(11);
	}
}




















