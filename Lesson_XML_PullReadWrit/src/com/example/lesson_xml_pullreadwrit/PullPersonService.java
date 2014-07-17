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
	
	//��ȡXML�ĵ���
	public static List<Person> readXML(InputStream is) throws Throwable{
		//��ȡһ��Pull��������ʵ����
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		
		List<Person> persons = null;
		Person person = null;
		
		/*
		 * ��ȡ��ǰ�����¼������ִ���.�˴������� Pull�ĵ�һ���¼�.Ҳ���ǿ�ʼ�����ĵ�.
		 * Pull���������¼�����һ�����ķ��������Ƕ�Ӧһ���������֡�Pullһ��ֻ��5��������
		 */
		int eventCode = parser.getEventType();

		//ֻҪû�н������ĵ��Ľ������־�һֱѭ����
		while(eventCode != XmlPullParser.END_DOCUMENT){
			switch (eventCode) {
			//�ļ���ʼ�¼�
			case XmlPullParser.START_DOCUMENT: 
				persons = new ArrayList<Person>();
				break;
			//��ʼ����Ԫ���¼���
			case XmlPullParser.START_TAG:  
				if("person".equals(parser.getName())){
					person = new Person();
					person.setId(Integer.valueOf(parser.getAttributeValue(0))); //��ȡperson�ĵ�1������ֵ ��
				}
				else if(person!=null){ //���person����Ԫ�ز�Ϊ��.
					if("name".equals(parser.getName())){
						person.setName(parser.nextText()); //�����ǰ�¼���START_TAG��ô��һ���¼������ı�,Ҳ��������.ֱ�ӻ�ȡnameԪ�ص�ֵ.
					}
					else if("age".equals(parser.getName())){
						person.setAge(new Short(parser.nextText()));
					}
				}
				break;
			//����Ԫ�ؽ����¼���	
			case XmlPullParser.END_TAG:
				//���������person�Ľ���������person����Ϊ�գ��ͽ�person���뵽persons.�����person.
				if("person".equals(parser.getName()) && person !=null){
					persons.add(person);
					person = null;
				}
				break;
			default:
				break;
			}
			eventCode = parser.next(); //����һ�¸��¼����������¼����ִ��롣
		}
		return persons;
	}
	
	//����XML�ĵ���
	public static void writeXML(List<Person> persons, Writer writer) throws Throwable{

		//�ж�SD���Ƿ����,���ҿɽ��ж�д���ʡ�
		if(Environment.getExternalStorageState().equals("mounted")){
			
			//��SD���ϴ���XML�ļ�
			File file = new File(Environment.getExternalStorageDirectory(), "androiddata.xml"); //��ͬ��File file = new File("/sdcard/ SDCard.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write("".getBytes());
	
			//����XML���л�����ʵ����
			XmlSerializer serializer = Xml.newSerializer();
			
			serializer.setOutput(fos,"UTF-8"); //���õ�ͬ��:<?xml version="1.0" encoding="utf-8"?>
	 
			//��ʼд�ĵ�ͷ
			serializer.startDocument("UTF-8", true);
			
			//��ʼдpersonsԪ��
			serializer.startTag(null, "persons");
			
			for(Person person:persons){
				//дpersonԪ��
				serializer.startTag(null, "person");
				serializer.attribute(null, "id", String.valueOf(person.getId()));
				
					//дnameԪ��
					serializer.startTag(null, "name");
					serializer.text(person.getName());
					serializer.endTag(null, "name");
					
					//дageԪ��
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
			System.out.println("SDCard �����ڻ�д����.");
		}
	}
}
