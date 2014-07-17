package com.example.lesson_xml_parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.test.AndroidTestCase;

public class ReadXmlTest extends AndroidTestCase {

	//使用SAX解析器来解析 XML
	public void SAXformXML() throws Throwable{
		InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("data.xml");
		SAXforHandler handler = new SAXforHandler();//创建解析类对象
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		saxParser.parse(is, handler);
		
		List<Person> list = handler.getPersons();
		
		
		for(Person person:list){
			System.out.println("SAXforHandler---->"+"ID:"+person.getId()+" Name:"+person.getName()+" Age:"+person.getAge());
		}
		
//		Iterator<Person> itr = list.iterator();
//		
//		while (itr.hasNext()) {
//			Person person = (Person) itr.next();
//			System.out.println("Persons---->"+"ID:"+person.getId()+" Name:"+person.getName()+" Age:"+person.getAge());
//		}
			
	}
	
	
	
	// 使用JAVA DOM对象来解析XML
	public void domXML() throws Throwable{
		
		InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("data.xml");
		List<Person> list = DomPersonService.readXML(is);

		for(Person person:list){
			System.out.println("DomPersonService---->"+"ID:"+person.getId()+" Name:"+person.getName()+" Age:"+person.getAge());
		}

	}
	
	
	
	//使用Pull对象来解析XML
	public void pullXML() throws Throwable{
		
		InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("data.xml");
		List<Person> list = PullPersonService.readXML(is);

		for(Person person:list){
			System.out.println("PullPersonService---->"+"ID:"+person.getId()+" Name:"+person.getName()+" Age:"+person.getAge());
		}

	}
	
	//使用Pull来生成xml文档，放入SDCard
	public void writeXML() throws Throwable{

			//读取data.xml文件内容。
			InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("data.xml");
			List<Person> list = PullPersonService.readXML(is);
			
			//写入器接口。
			Writer writer = new Writer() {
				@Override
				public void write(char[] buf, int offset, int count) throws IOException {
					
				}
				
				@Override
				public void flush() throws IOException {
					
				}
				
				@Override
				public void close() throws IOException {
					
				}
			};
			
			PullPersonService.writeXML(list, null);

	}
}
