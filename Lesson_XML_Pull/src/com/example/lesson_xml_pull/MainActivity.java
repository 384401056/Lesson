package com.example.lesson_xml_pull;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;


public class MainActivity extends Activity {

	private List<Person> list = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		readXML();
		
		writeXML();

	}
	
	//��ȡsrcĿ¼�µ�data.xml�ĵ���
	public void readXML(){
		InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("data.xml");
		try {
			list = PullService.readXML(is);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	//����ȡ����List����д��XML�ĵ���������SDCard
	public void writeXML(){
		//д�����ӿڡ�
		Writer writer = new Writer() {
			public void write(char[] buf, int offset, int count) throws IOException {
			}
			public void flush() throws IOException {
			}
			public void close() throws IOException {
			}
		};
		
		try {
			PullService.writeXML(list, writer);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
