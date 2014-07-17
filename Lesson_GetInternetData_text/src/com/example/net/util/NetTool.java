package com.example.net.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Environment;

public class NetTool {

	//��ȡhtml�ĵ��ı����ĵ���С��Ӧ����100k,����TextView�ؼ��޷���ʾ��������ݹ���ͱ���Ϊ�ļ���
	public static String getHtml(String urlpath, String enconding) throws Exception{
		//��������Ҫ��������·������Ҫ�����ļ���·��	
		URL url = new URL(urlpath);  //ͨ��·������URL�����
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();//ͨ��URL������HTTP���ӡ�
		
		conn.setRequestMethod("GET"); //�������ӵ�����ʽ��
		conn.setConnectTimeout(6000); //��������ʱ��ʱ����
		
		if(conn.getResponseCode()==200){ //�������˵Ļظ�CodeΪ200��˵�����ӳɹ�.
			InputStream is = conn.getInputStream(); //�õ��������˴���������.���ڿͻ�����˵������������	
			byte[] data = NetTool.readInputStream(is);//����������Ϊ�ֽ����ݡ�
			return new String(data,enconding);
		}
		return null;
	}

	
	//��ȡͼƬ����
	public static byte[] getImage(String urlpath) throws Exception{
		//��������Ҫ��������·������Ҫ�����ļ���·��
		URL url = new URL(urlpath);  //ͨ��·������URL�����
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();//ͨ��URL������HTTP���ӡ�
		
		conn.setRequestMethod("GET"); //�������ӵ�����ʽ��
		conn.setConnectTimeout(6000); //��������ʱ��ʱ����
		
		if(conn.getResponseCode()==200){ //�������˵Ļظ�CodeΪ200��˵�����ӳɹ�.
			InputStream is = conn.getInputStream(); //�õ��������˴���������.���ڿͻ�����˵������������
			
			byte[] data = NetTool.readInputStream(is);//����������Ϊ�ֽ����ݡ�
			return data;
		}

		return null;
	}

	//��ȡ�������ݲ�����Ϊ�����ļ���
	public static void getFile(String urlpath, String fileName) throws Exception{
		//��������Ҫ��������·������Ҫ�����ļ���·��	
		URL url = new URL(urlpath);  //ͨ��·������URL�����
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();//ͨ��URL������HTTP���ӡ�
		
		conn.setRequestMethod("GET"); //�������ӵ�����ʽ��
		conn.setConnectTimeout(6000); //��������ʱ��ʱ����
		
		//�������˵Ļظ�CodeΪ200��˵�����ӳɹ�.�����ж�SD���Ƿ����,�ɽ��ж�д���ʡ�
		if(conn.getResponseCode()==200 && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){ 
			InputStream is = conn.getInputStream(); //�õ��������˴���������.���ڿͻ�����˵������������	
			byte[] data = NetTool.readInputStream(is);//����������Ϊ�ֽ����ݡ�
	
			File file = new File(Environment.getExternalStorageDirectory(), fileName); //��ͬ��File file = new File("/sdcard/ SDCard.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
		}
		else{
			return;
		}
	}

	//����������Ϊ�ֽ����ݡ�
	public static byte[] readInputStream(InputStream is) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		
		try {
			int length = -1;
			while((length = is.read(buffer))!=-1){ //ͨ��������,��ȡ�����ݷ���Buffer�С����ж��Ƿ�Ϊ��.
				baos.write(buffer, 0, length);  //��buffer�е�����д�뵽ByteArrayOutputStream�Ļ������С�
			}
			baos.close();
			is.close();
			return baos.toByteArray();//����һ�������˻����������ݵ�byte[].
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
