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
	
	//获取网络上的图片
	public void getImage() throws Exception{
		
		//首先我们要获得请求的路径。
		String urlpath = "http://www.qq.com";
		
		URL url = new URL(urlpath);  //通过路径建立URL类对象。
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();//通过URL对象建立HTTP连接。
		
		conn.setRequestMethod("GET"); //设置连接的请求方式。
		conn.setConnectTimeout(6000); //设置请求超时的时长。
		
		if(conn.getResponseCode()==200){ //如果服务端的回复Code为200，说明连接成功.
			InputStream is = conn.getInputStream(); //得到服务器端传来的数据.对于客户端来说就是输入流。
			
			byte[] data = readInputStream(is);//将输入流变为字节数据。
			
			System.out.println(new String(data,"UTF-8")); //直接输出字节流。
			
//			File file = new File("myImage03.jpg"); //创建文件。
//			FileOutputStream fos = new FileOutputStream(file); //创建文件输出流。并对文件输出数据.
//			fos.write(data);
//			fos.close();
		}
	
	}

	//从输入流中读出字节流.
	private byte[] readInputStream(InputStream is) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		
		try {
			int length = -1;
			while((length = is.read(buffer))!=-1){ //通过输入流读取出数据放入Buffer中。并判断是否为空.
				bos.write(buffer, 0, length);  //将buffer中的数据写入到ByteArrayOutputStream的缓冲区中。
			}
			bos.close();
			is.close();
			return bos.toByteArray();//创建一个复制了缓冲区中内容的byte[].
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}










