package com.example.multhreaddownloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

//线程类.
public class DownloadThread extends Thread{
	private static final String TAG = "DownloadThread";
	
	private RandomAccessFile saveFile;
	private URL downUrl;
	private int block;
	
	//下载开始位置。
	private int threadId = -1;
	private int startPos;
	private int downLength;
	private boolean finish = false;
	private FileDownloader downloader;

	/**
	 * 线程类构造函数.
	 * @param downloader
	 * @param downUrl
	 * @param saveFile
	 * @param block
	 * @param startPos
	 * @param threadId
	 */
	public DownloadThread(FileDownloader downloader,URL downUrl,RandomAccessFile saveFile,int block,int startPos,int threadId){
		this.downUrl = downUrl;
		this.saveFile = saveFile;
		this.block = block;
		this.startPos = startPos;
		this.downloader = downloader;
		this.threadId = threadId;
		this.downLength = startPos - (block * (threadId-1)); //已经下载的数据大小.
	}
	
	public void run() {
		try {
			HttpURLConnection http = (HttpURLConnection)downUrl.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Accept-Language", "zh-CN");
			http.setRequestProperty("Referer", downUrl.toString());
			http.setRequestProperty("Charset", "UTF-8");
			http.setRequestProperty("Range", "bytes="+this.startPos+"-");
			http.setRequestProperty("Connection","Keep-Alive");
			
			InputStream is = http.getInputStream();
			int max = block>1024?1024:(block>10?10:1);  //根据每条线程要下载的大小来决定buffer的大小.
			byte[] buffer = new byte[max];
			int offset = 0;
			System.out.println("线程"+this.threadId+"从位置"+this.startPos+"开始下载.");
			
			while(downLength<block && (offset=is.read(buffer,0,max))!=-1){
				saveFile.write(buffer, 0, offset); //写入文件.
				downLength += offset;
				downloader.update(this.threadId, block * (threadId-1) + downLength); //数据更新到data缓存中,threadid和当前已下载量，也就是新位置。
				downloader.saveLogFile(); //将这些位置保存在数据库中。因为多个线程都可能调用此方法，所以此方法要加同步关键字。
				downloader.append(offset); //累计已下载大小。同步方法.
				
				int spare = block - downLength;
				if(spare<max) max = (int)spare;
			}
			
			saveFile.close();
			is.close();
			System.out.println("线程 "+ this.threadId + "完成下载");
			this.finish = true;
			this.interrupt();
		} catch (Exception e) {
			this.downLength = -1;
			System.out.print("线程"+this.threadId+":"+e);
		}
	}
	public boolean isFinish(){
		return finish;
	}
	
	public long getDownLength(){
		return downLength;
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	