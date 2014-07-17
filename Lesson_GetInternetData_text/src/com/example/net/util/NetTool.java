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

	//获取html文档文本。文档大小不应超过100k,否则TextView控件无法显示。如果数据过大就保存为文件。
	public static String getHtml(String urlpath, String enconding) throws Exception{
		//首先我们要获得请求的路径。即要下载文件的路径	
		URL url = new URL(urlpath);  //通过路径建立URL类对象。
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();//通过URL对象建立HTTP连接。
		
		conn.setRequestMethod("GET"); //设置连接的请求方式。
		conn.setConnectTimeout(6000); //设置请求超时的时长。
		
		if(conn.getResponseCode()==200){ //如果服务端的回复Code为200，说明连接成功.
			InputStream is = conn.getInputStream(); //得到服务器端传来的数据.对于客户端来说就是输入流。	
			byte[] data = NetTool.readInputStream(is);//将输入流变为字节数据。
			return new String(data,enconding);
		}
		return null;
	}

	
	//获取图片数据
	public static byte[] getImage(String urlpath) throws Exception{
		//首先我们要获得请求的路径。即要下载文件的路径
		URL url = new URL(urlpath);  //通过路径建立URL类对象。
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();//通过URL对象建立HTTP连接。
		
		conn.setRequestMethod("GET"); //设置连接的请求方式。
		conn.setConnectTimeout(6000); //设置请求超时的时长。
		
		if(conn.getResponseCode()==200){ //如果服务端的回复Code为200，说明连接成功.
			InputStream is = conn.getInputStream(); //得到服务器端传来的数据.对于客户端来说就是输入流。
			
			byte[] data = NetTool.readInputStream(is);//将输入流变为字节数据。
			return data;
		}

		return null;
	}

	//获取网络数据并保存为本地文件。
	public static void getFile(String urlpath, String fileName) throws Exception{
		//首先我们要获得请求的路径。即要下载文件的路径	
		URL url = new URL(urlpath);  //通过路径建立URL类对象。
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();//通过URL对象建立HTTP连接。
		
		conn.setRequestMethod("GET"); //设置连接的请求方式。
		conn.setConnectTimeout(6000); //设置请求超时的时长。
		
		//如果服务端的回复Code为200，说明连接成功.并且判断SD卡是否可用,可进行读写访问。
		if(conn.getResponseCode()==200 && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){ 
			InputStream is = conn.getInputStream(); //得到服务器端传来的数据.对于客户端来说就是输入流。	
			byte[] data = NetTool.readInputStream(is);//将输入流变为字节数据。
	
			File file = new File(Environment.getExternalStorageDirectory(), fileName); //等同于File file = new File("/sdcard/ SDCard.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
		}
		else{
			return;
		}
	}

	//将输入流变为字节数据。
	public static byte[] readInputStream(InputStream is) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		
		try {
			int length = -1;
			while((length = is.read(buffer))!=-1){ //通过输入流,读取出数据放入Buffer中。并判断是否为空.
				baos.write(buffer, 0, length);  //将buffer中的数据写入到ByteArrayOutputStream的缓冲区中。
			}
			baos.close();
			is.close();
			return baos.toByteArray();//创建一个复制了缓冲区中内容的byte[].
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
