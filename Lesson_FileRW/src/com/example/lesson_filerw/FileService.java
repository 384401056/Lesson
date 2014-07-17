package com.example.lesson_filerw;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;

public class FileService {
	private Context context;
	
	public FileService(Context context){
		this.context = context;
	}
	
	public void saveData(String content) throws Exception{
		
		/*
		 * openFileOutput()�ڶ��������Ǵ��ļ���ģʽ.
		 * MODE_PRIVATE Ϊ˽��ģʽ��ֻ�д����ļ���Ӧ�ÿ��Է��ʣ�����ÿ��д�붼�Ḳ��ԭ�������ݡ�
		 * MODE_APPEND Ϊ׷��ģʽ��
		 * 
		 */
		FileOutputStream fos = context.openFileOutput("sharpandroid.txt", Context.MODE_PRIVATE);
		fos.write(content.getBytes());
		fos.close();
	}
	
	public String readData() throws Throwable{
		int len = 0;
		byte[] buffer = new byte[1024];
		
		FileInputStream fis = context.openFileInput("sharpandroid.txt");
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		while((len=fis.read(buffer))!=-1){
			bos.write(buffer, 0, len); //���񻺳���buffer�е�����
		}
		bos.close();
		
		byte[] data = bos.toByteArray();//����������ݣ�ת�����ֽ����顣
		return new String(data);
	}
	
	/*
	 * �������ݵ�SD����.Ҫ����ӷ���SD����Ȩ��.
	 */
	public void saveToSDCard(String content) throws Exception{
		File file = new File(Environment.getExternalStorageDirectory(), "SDCard.txt"); //��ͬ��File file = new File("/sdcard/ SDCard.txt");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(content.getBytes());
		fos.close();
	}
	
}





















