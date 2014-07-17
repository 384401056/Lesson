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
		 * openFileOutput()第二个参数是打开文件的模式.
		 * MODE_PRIVATE 为私有模式，只有创建文件的应用可以访问，并且每次写入都会覆盖原来的内容。
		 * MODE_APPEND 为追加模式。
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
			bos.write(buffer, 0, len); //捕获缓冲区buffer中的数据
		}
		bos.close();
		
		byte[] data = bos.toByteArray();//将捕获的数据，转换成字节数组。
		return new String(data);
	}
	
	/*
	 * 保存数据到SD卡中.要先添加访问SD卡的权限.
	 */
	public void saveToSDCard(String content) throws Exception{
		File file = new File(Environment.getExternalStorageDirectory(), "SDCard.txt"); //等同于File file = new File("/sdcard/ SDCard.txt");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(content.getBytes());
		fos.close();
	}
	
}





















