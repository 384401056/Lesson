package com.example.lesson_filerw;

import java.io.FileInputStream;

import android.os.Environment;
import android.test.AndroidTestCase;

public class FileServiceTest extends AndroidTestCase {
	
	public void testSave() throws Throwable{
		FileService fileService = new FileService(getContext());
		fileService.saveData("�������ļ��Ĵ�ģʽΪMODE_APPENDʱ��д������ݽ���׷�ӵ��ļ�β��..");
	}
	
	public void testRead() throws Throwable{
		FileService fileService = new FileService(getContext());
		System.out.println(fileService.readData());
	}
	
	public void testSaveToSDCard() throws Throwable{
		 //�ж�SD���Ƿ����,���ҿɽ��ж�д���ʡ�
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			FileService fileService = new FileService(getContext());
			fileService.saveToSDCard("����SDCard�ϣ����ã�����");
		}else{
			System.out.println("SDCard �����ڻ�д����.");
		}
	}
}
