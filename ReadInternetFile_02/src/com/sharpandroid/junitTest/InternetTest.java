package com.sharpandroid.junitTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class InternetTest {
	
	@Test
	public void test() {
		try {
			this.getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ�����ϵ�ͼƬ
	public void getImage() throws Exception{
		
		//��������Ҫ��������·����
		String urlpath = "http://www.qq.com";
		
		URL url = new URL(urlpath);  //ͨ��·������URL�����
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();//ͨ��URL������HTTP���ӡ�
		
		conn.setRequestMethod("GET"); //�������ӵ�����ʽ��
		conn.setConnectTimeout(6000); //��������ʱ��ʱ����
		
		if(conn.getResponseCode()==200){ //�������˵Ļظ�CodeΪ200��˵�����ӳɹ�.
			InputStream is = conn.getInputStream(); //�õ��������˴���������.���ڿͻ�����˵������������
			
			byte[] data = readInputStream(is);//����������Ϊ�ֽ����ݡ�
			
			System.out.println(new String(data,"UTF-8")); //ֱ������ֽ�����
			
//			File file = new File("myImage03.jpg"); //�����ļ���
//			FileOutputStream fos = new FileOutputStream(file); //�����ļ�������������ļ��������.
//			fos.write(data);
//			fos.close();
		}
	
	}

	//���������ж����ֽ���.
	private byte[] readInputStream(InputStream is) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		
		try {
			int length = -1;
			while((length = is.read(buffer))!=-1){ //ͨ����������ȡ�����ݷ���Buffer�С����ж��Ƿ�Ϊ��.
				bos.write(buffer, 0, length);  //��buffer�е�����д�뵽ByteArrayOutputStream�Ļ������С�
			}
			bos.close();
			is.close();
			return bos.toByteArray();//����һ�������˻����������ݵ�byte[].
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}










