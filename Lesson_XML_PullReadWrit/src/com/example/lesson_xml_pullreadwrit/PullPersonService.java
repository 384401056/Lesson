package com.example.lesson_xml_pullreadwrit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.os.Environment;
import android.util.Xml;

public class PullPersonService {
	
	//读取XML文档。
	public static List<Person> readXML(InputStream is) throws Throwable{
		//获取一个Pull解析对象实例。
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		
		List<Person> persons = null;
		Person person = null;
		
		/*
		 * 获取当前解析事件的数字代码.此处触发了 Pull的第一个事件.也就是开始解析文档.
		 * Pull解析器的事件不是一个个的方法，而是对应一个个的数字。Pull一共只有5个方法。
		 */
		int eventCode = parser.getEventType();

		//只要没有解析到文档的结束部分就一直循环。
		while(eventCode != XmlPullParser.END_DOCUMENT){
			switch (eventCode) {
			//文件开始事件
			case XmlPullParser.START_DOCUMENT: 
				persons = new ArrayList<Person>();
				break;
			//开始解析元素事件。
			case XmlPullParser.START_TAG:  
				if("person".equals(parser.getName())){
					person = new Person();
					person.setId(Integer.valueOf(parser.getAttributeValue(0))); //获取person的第1个属性值 。
				}
				else if(person!=null){ //如果person的子元素不为空.
					if("name".equals(parser.getName())){
						person.setName(parser.nextText()); //如果当前事件是START_TAG那么下一个事件就是文本,也就是内容.直接获取name元素的值.
					}
					else if("age".equals(parser.getName())){
						person.setAge(new Short(parser.nextText()));
					}
				}
				break;
			//解析元素结束事件。	
			case XmlPullParser.END_TAG:
				//如果解析到person的结束，而且person对象不为空，就将person加入到persons.并清空person.
				if("person".equals(parser.getName()) && person !=null){
					persons.add(person);
					person = null;
				}
				break;
			default:
				break;
			}
			eventCode = parser.next(); //触发一下个事件，并返回事件数字代码。
		}
		return persons;
	}
	
	//生成XML文档。
	public static void writeXML(List<Person> persons, Writer writer) throws Throwable{

		//判断SD卡是否可用,并且可进行读写访问。
		if(Environment.getExternalStorageState().equals("mounted")){
			
			//在SD卡上创建XML文件
			File file = new File(Environment.getExternalStorageDirectory(), "androiddata.xml"); //等同于File file = new File("/sdcard/ SDCard.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write("".getBytes());
	
			//创建XML串行化对象实例。
			XmlSerializer serializer = Xml.newSerializer();
			
			serializer.setOutput(fos,"UTF-8"); //作用等同于:<?xml version="1.0" encoding="utf-8"?>
	 
			//开始写文档头
			serializer.startDocument("UTF-8", true);
			
			//开始写persons元素
			serializer.startTag(null, "persons");
			
			for(Person person:persons){
				//写person元素
				serializer.startTag(null, "person");
				serializer.attribute(null, "id", String.valueOf(person.getId()));
				
					//写name元素
					serializer.startTag(null, "name");
					serializer.text(person.getName());
					serializer.endTag(null, "name");
					
					//写age元素
					serializer.startTag(null, "age");
					serializer.text(String.valueOf(person.getAge()));
					serializer.endTag(null, "age");
				
				serializer.endTag(null, "person");
			}
			
			serializer.endTag(null, "persons");
			serializer.endDocument();
			
			writer.flush();
			writer.close();
		}
		else{
			System.out.println("SDCard 不存在或写保护.");
		}
	}
}
