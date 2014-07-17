/*
 * 多线程下载测试
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
		
	String fileName = "C:\\Users\\ServerAdmin\\Desktop\\QQBrowser.exe"; //文件名和路径。中间的\要用\\来转义.
	String path = "http://dldir1.qq.com/invc/tt/QQBrowserSetup.exe";  //下载地址.
	URL url = new URL(path);
	HttpURLConnection conn = (HttpURLConnection)url.openConnection();//通过URL对象建立HTTP连接。
	
	conn.setRequestMethod("GET"); //设置连接的请求方式。
	conn.setConnectTimeout(5*1000); //设置请求超时的时长。
	
	int fileLength = conn.getContentLength();//获取将要下载的文件长度。
	System.out.println("下载文件长度："+ fileLength);
	
	//随机访问文件.
	RandomAccessFile file = new RandomAccessFile(fileName, "rw"); 
	file.setLength(fileLength); //设置本地文件的长度.
	file.close();
	conn.disconnect();
	int threadSize = 3;  //要开启下载的线程数。
	int threadLength = fileLength % 3 ==0?fileLength/3:fileLength/3+1; //每新线程要下载的长度。如果能整除则平均分3份，如果不能则每份上加1.
	
	//循环创建随访问文件，并启动线程.
	for(int i=0;i<threadSize;i++){
		int startPosition = i * threadLength;
		RandomAccessFile threadFile = new RandomAccessFile(fileName, "rw"); //创建随访问文件.
		threadFile.seek(startPosition);//从指定的字节数处写入数据
		
		new DownlaodThread(i, path, startPosition, threadFile, threadLength).start(); //创建线程.
	}
	
	//读取用户输入。如果输入q则退出.否一直等待。
	int quit = System.in.read();
	while('q'!=quit){
		Thread.sleep(2*1000);
	}
}
	
	//线程类.
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
				
				//利HTTP的Range头字段,从指定的字节大小处下载文件。
				conn.setRequestProperty("Range", "bytes="+startposition+"-");
				InputStream is = conn.getInputStream(); //获取输入流。
				
				byte[] buffer = new byte[1024];
				
 				int len = -1;
 				int length = 0;
 				
 				//循环读取输入流并写入文件。
 				while(length<threadlength && (len = is.read(buffer))!=-1){
 					threadFile.write(buffer, 0, len); //将输入流读取到的数据写入文件.
 					length += len;
 				}
 				threadFile.close();
				is.close();
				System.out.println("线程"+(threadid+1)+"已下载完成！"+length);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("线程"+threadid+"下载出错！");
			}
		}

	}

}
















