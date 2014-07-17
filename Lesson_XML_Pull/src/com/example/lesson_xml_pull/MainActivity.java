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
	
	//读取src目录下的data.xml文档。
	public void readXML(){
		InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("data.xml");
		try {
			list = PullService.readXML(is);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	//将读取到的List数据写成XML文档，并存入SDCard
	public void writeXML(){
		//写入器接口。
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
