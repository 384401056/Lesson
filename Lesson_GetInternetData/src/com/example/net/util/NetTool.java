package com.example.net.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetTool {

	public static byte[] getImage(String urlpath) throws Exception{
		try{
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
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		return null;
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
