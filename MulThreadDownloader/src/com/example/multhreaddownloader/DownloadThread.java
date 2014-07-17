package com.example.multhreaddownloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

//�߳���.
public class DownloadThread extends Thread{
	private static final String TAG = "DownloadThread";
	
	private RandomAccessFile saveFile;
	private URL downUrl;
	private int block;
	
	//���ؿ�ʼλ�á�
	private int threadId = -1;
	private int startPos;
	private int downLength;
	private boolean finish = false;
	private FileDownloader downloader;

	/**
	 * �߳��๹�캯��.
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
		this.downLength = startPos - (block * (threadId-1)); //�Ѿ����ص����ݴ�С.
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
			int max = block>1024?1024:(block>10?10:1);  //����ÿ���߳�Ҫ���صĴ�С������buffer�Ĵ�С.
			byte[] buffer = new byte[max];
			int offset = 0;
			System.out.println("�߳�"+this.threadId+"��λ��"+this.startPos+"��ʼ����.");
			
			while(downLength<block && (offset=is.read(buffer,0,max))!=-1){
				saveFile.write(buffer, 0, offset); //д���ļ�.
				downLength += offset;
				downloader.update(this.threadId, block * (threadId-1) + downLength); //���ݸ��µ�data������,threadid�͵�ǰ����������Ҳ������λ�á�
				downloader.saveLogFile(); //����Щλ�ñ��������ݿ��С���Ϊ����̶߳����ܵ��ô˷��������Դ˷���Ҫ��ͬ���ؼ��֡�
				downloader.append(offset); //�ۼ������ش�С��ͬ������.
				
				int spare = block - downLength;
				if(spare<max) max = (int)spare;
			}
			
			saveFile.close();
			is.close();
			System.out.println("�߳� "+ this.threadId + "�������");
			this.finish = true;
			this.interrupt();
		} catch (Exception e) {
			this.downLength = -1;
			System.out.print("�߳�"+this.threadId+":"+e);
		}
	}
	public boolean isFinish(){
		return finish;
	}
	
	public long getDownLength(){
		return downLength;
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	