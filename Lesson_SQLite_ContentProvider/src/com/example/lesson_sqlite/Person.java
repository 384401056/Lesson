package com.example.lesson_sqlite;

public class Person {
	private Integer id;
	private String name;
	private Integer age;
	
	public Person(){
		
	}
	
	public Person(String name,Integer age){
		this.name = name;
		this.age = age;
	}
	
	
	public Integer getID(){
		return id;
	}
	
	public void setID(Integer id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Integer getAge(){
		return this.age;
	}
	
	public void setAge(Integer age){
		this.age = age;
	}
	
	
	public String toString(){
		return "Person [ID:" +this.id+ "[Name:"+this.name+"]"+"[Age:"+this.age+"]";
	}
	
}



















