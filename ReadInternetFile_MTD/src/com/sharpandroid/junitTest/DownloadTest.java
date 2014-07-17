/*
 * ���߳����ز���
 */
package com.sharpandroid.junitTest;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;


public class DownloadTest {

	@Test
	public void test() {
		try {
			this.download();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	
public void download() throws Throwable{
		
	String fileName = "C:\\Users\\ServerAdmin\\Desktop\\QQBrowser.exe"; //�ļ�����·�����м��\Ҫ��\\��ת��.
	String path = "http://dldir1.qq.com/invc/tt/QQBrowserSetup.exe";  //���ص�ַ.
	URL url = new URL(path);
	HttpURLConnection conn = (HttpURLConnection)url.openConnection();//ͨ��URL������HTTP���ӡ�
	
	conn.setRequestMethod("GET"); //�������ӵ�����ʽ��
	conn.setConnectTimeout(5*1000); //��������ʱ��ʱ����
	
	int fileLength = conn.getContentLength();//��ȡ��Ҫ���ص��ļ����ȡ�
	System.out.println("�����ļ����ȣ�"+ fileLength);
	
	//��������ļ�.
	RandomAccessFile file = new RandomAccessFile(fileName, "rw"); 
	file.setLength(fileLength); //���ñ����ļ��ĳ���.
	file.close();
	conn.disconnect();
	int threadSize = 3;  //Ҫ�������ص��߳�����
	int threadLength = fileLength % 3 ==0?fileLength/3:fileLength/3+1; //ÿ���߳�Ҫ���صĳ��ȡ������������ƽ����3�ݣ����������ÿ���ϼ�1.
	
	//ѭ������������ļ����������߳�.
	for(int i=0;i<threadSize;i++){
		int startPosition = i * threadLength;
		RandomAccessFile threadFile = new RandomAccessFile(fileName, "rw"); //����������ļ�.
		threadFile.seek(startPosition);//��ָ�����ֽ�����д������
		
		new DownlaodThread(i, path, startPosition, threadFile, threadLength).start(); //�����߳�.
	}
	
	//��ȡ�û����롣�������q���˳�.��һֱ�ȴ���
	int quit = System.in.read();
	while('q'!=quit){
		Thread.sleep(2*1000);
	}
}
	
	//�߳���.
	private class DownlaodThread extends Thread{
		private int threadid;
		private int startposition;
		private RandomAccessFile threadFile;
		private int threadlength;
		private String path;

		
		public DownlaodThread(int threadid,String path,int startposition,RandomAccessFile threadFile,int threadlength){
			this.threadid = threadid;
			this.path = path;
			this.startposition = startposition;
			this.threadFile = threadFile;
			this.threadlength = threadlength;
		}
		
		public void run() {
			try {
				
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setReadTimeout(5*1000);
				conn.setRequestMethod("GET");
				
				//��HTTP��Rangeͷ�ֶ�,��ָ�����ֽڴ�С�������ļ���
				conn.setRequestProperty("Range", "bytes="+startposition+"-");
				InputStream is = conn.getInputStream(); //��ȡ��������
				
				byte[] buffer = new byte[1024];
				
 				int len = -1;
 				int length = 0;
 				
 				//ѭ����ȡ��������д���ļ���
 				while(length<threadlength && (len = is.read(buffer))!=-1){
 					threadFile.write(buffer, 0, len); //����������ȡ��������д���ļ�.
 					length += len;
 				}
 				threadFile.close();
				is.close();
				System.out.println("�߳�"+(threadid+1)+"��������ɣ�"+length);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("�߳�"+threadid+"���س���");
			}
		}

	}

}
















