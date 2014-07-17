package com.example.lesson_xml_pullreadwrit;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import android.test.AndroidTestCase;

public class PullPersonServiceTest extends AndroidTestCase {
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
			
			for(Person person:list){
				System.out.println("PullPersonService---->"+"ID:"+person.getId()+" Name:"+person.getName()+" Age:"+person.getAge());
			}
			
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
			
			//将读出的XML文档再写入到SDCard中.
			PullPersonService.writeXML(list, writer);

		}
}
