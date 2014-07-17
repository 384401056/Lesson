package com.example.lesson_rss_pull;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetTool {
	public static InputStream getStream(String urlPath,String encoding) throws Exception{
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6*1000);
		if(conn.getResponseCode()==200){
			InputStream is = conn.getInputStream();
			return is;
		}else
			return null;
	}
}
