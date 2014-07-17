package com.example.lesson_xml_pullreadwrit;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import android.test.AndroidTestCase;

public class PullPersonServiceTest extends AndroidTestCase {
		//ʹ��Pull����������XML
		public void pullXML() throws Throwable{
			InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("data.xml");
			List<Person> list = PullPersonService.readXML(is);

			for(Person person:list){
				System.out.println("PullPersonService---->"+"ID:"+person.getId()+" Name:"+person.getName()+" Age:"+person.getAge());
			}

		}
		
		//ʹ��Pull������xml�ĵ�������SDCard
		public void writeXML() throws Throwable{

			//��ȡdata.xml�ļ����ݡ�
			InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("data.xml");
			List<Person> list = PullPersonService.readXML(is);
			
			for(Person person:list){
				System.out.println("PullPersonService---->"+"ID:"+person.getId()+" Name:"+person.getName()+" Age:"+person.getAge());
			}
			
			//д�����ӿڡ�
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
			
			//��������XML�ĵ���д�뵽SDCard��.
			PullPersonService.writeXML(list, writer);

		}
}
