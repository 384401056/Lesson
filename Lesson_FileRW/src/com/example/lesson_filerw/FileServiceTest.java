package com.example.lesson_filerw;

import java.io.FileInputStream;

import android.os.Environment;
import android.test.AndroidTestCase;

public class FileServiceTest extends AndroidTestCase {
	
	public void testSave() throws Throwable{
		FileService fileService = new FileService(getContext());
		fileService.saveData("当设置文件的打开模式为MODE_APPEND时，写入的内容将被追加到文件尾部..");
	}
	
	public void testRead() throws Throwable{
		FileService fileService = new FileService(getContext());
		System.out.println(fileService.readData());
	}
	
	public void testSaveToSDCard() throws Throwable{
		 //判断SD卡是否可用,并且可进行读写访问。
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			FileService fileService = new FileService(getContext());
			fileService.saveToSDCard("存在SDCard上，更好！！！");
		}else{
			System.out.println("SDCard 不存在或写保护.");
		}
	}
}
