package com.example.multhreaddownloader;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;

public class FileDownloader {

	private Context contex;
	private FileService fileService;
	
	private static final String TAG = "FileDownloaker";
	private int downloadSize = 0;  //已下载文件大小。
	private int fileSize = 0;  //原始文件大小。
	private DownloadThread[] threads;  //线程数。
	private URL url; //下载地址。
	private File saveFile;  //本地保存文件.
	private File logFile;   //下载记录文件.
	private Map<Integer,Integer> data = new ConcurrentHashMap<Integer, Integer>(); //缓存各线程id和最后下载的位置。
	
	private int block;  //每条线程下载的大小 。
	private String downloadUrl;  //下载路径.
	
	
	
	/**
	 * 构建文件下载器.
	 * @param context
	 * @param downloadUrl 下载路径
	 * @param fileSaveDir 文件保存目录
	 * @param threadNum 下载线程数
	 */
	public FileDownloader(Context context,String downloadUrl,File fileSaveDir,int threadNum){
		try{
			
			this.contex = context;
			this.downloadUrl = downloadUrl; //下载路径.
			fileService = new FileService(context);  //创建数据库文件操作类。
			this.url = new URL(downloadUrl);
			if(!fileSaveDir.exists()) fileSaveDir.mkdirs(); //判断存储目录是否存在，如果不存就调用File类的mkdirs()方法去创建.
			
			this.threads = new DownloadThread[threadNum];
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(6*1000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Accept-Language", "zh-CN");
			conn.setRequestProperty("Referer", downloadUrl);
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.connect();
			
			if (conn.getResponseCode()==200){
				this.fileSize = conn.getContentLength(); //获取文件大小。
				
				if(this.fileSize<=0) throw new RuntimeException("无法获知文件大小。");
				String fileName = getFileName(conn); //获取文件名
				this.saveFile = new File(fileSaveDir,fileName); //保存文件. 
				
				Map<Integer,Integer> logdata = fileService.getData(downloadUrl); //通下下载地址，查询数据库获取其线程id,和最后的下载位置。
				
				//如果得到数据，说明以前下载过。就把数据放入data中，data全局变量保存名线程最后下载的位置。
				if(logdata.size()>0){
					data.putAll(logdata);
				}
				
				this.block = this.fileSize / this.threads.length + 1; //得到每个线程要下载的大小。
				
				if(this.data.size()==this.threads.length){
					for(int i=0;i<this.threads.length;i++){
						//this.data.get(i+1) 得到一条线程最后一次下载到哪里。
						//this.block*i 得到每条线程的起始位置。block每条线程要下载的大小，乘以i则为开始位置了。这里要注意。
						//所以，以上两项相减就得到了每条线程目前下载了多少数据。最后循环累加就得到所有线程共下载了多少。
						this.downloadSize += this.data.get(i+1)-(this.block*i);
					}
					System.out.println("已经下载的长度."+this.downloadSize);
				}
			}
			else{
				throw new RuntimeException("服务器响应错误");
			}
		}catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException("连接不到下载路径!");
		}
	}


	/**
	 * 获取要下载的文件名
	 * @param conn
	 * @return
	 */
	private String getFileName(HttpURLConnection conn) {
		
		String filename = this.url.toString().substring(this.url.toString().lastIndexOf('/') + 1);
		
		if(filename==null || "".equals(filename.trim())){
			for(int i=0;;i++){
				String mine = conn.getHeaderField(i);
				if(mine==null) break;
				if("content-disposition".equals(conn.getHeaderFieldKey(i).toLowerCase())){
					Matcher m = Pattern.compile(".*filename=(.*)").matcher(mine.toLowerCase());
					if(m.find()) return m.group(1);
				}
			}
			filename = UUID.randomUUID()+".tmp"; //默认取一个文件名.
		}
		return filename;
	}


	/**
	 * 开始下载文件.
	 * @param listener 监听下载数量的变化，如果不需要了解实时下载数据，可以设置为null;
	 * @return  已下载文件大小。
	 * @throws Exception
	 */
	public int download(DownloadProgressListener listener) throws Exception{
		try {
			
			if(this.data.size()!=this.threads.length){
				this.data.clear();
				for(int i=0;i<this.threads.length;i++){
					this.data.put(i+1,this.block * i);
				}
			}
			
			
			for(int i=0;i<this.threads.length;i++){
				int downLength = this.data.get(i+1) - (this.block * i);
				
				//判断已经下载的数据和要下载的数据是否相等，以及，最后下载的位置是否小于文件大小。
				if(downLength < this.block && this.data.get(i+1) < this.fileSize){
					RandomAccessFile randOut = new RandomAccessFile(this.saveFile, "rw"); //创建随机访问文件.
					if(this.fileSize>0) randOut.setLength(this.fileSize); //设置文件大小.
					randOut.seek(this.data.get(i+1));  //将每个线程最后的下载位置做为开始的下载位置。
					
					//创建一个线程对象，在类构造函数中初始化一些变量，注意此时还没有开始下载。
					this.threads[i] = new DownloadThread(this, this.url, randOut, this.block, this.data.get(i+1),i+1);
					this.threads[i].setPriority(7);  //设置高权限.
					this.threads[i].start(); //开始执行线程类中的run()方法。
					
				}else{
					this.threads[i] = null;
				}
			}
			
			/**
			 * 以下部份是当未完成下载的补救方案。
			 */
			this.fileService.save(this.downloadUrl,this.data); //保存线程的当前下载位置到数据库中。
			boolean notFinish = true;  //下载未完成的标志。
			
			/**
			 * 循环判断是否下载完成。
			 */
			while(notFinish){
				Thread.sleep(900);
				notFinish = false;//假设下载完成。
				
				for(int i=0;i< this.threads.length;i++){
					//如果线程为空，并且线程没有下载完成.
					if(this.threads[i] != null && !this.threads[i].isFinish()){
						notFinish = true; 
						//如果下载失败，再重新下载。
						if(this.threads[i].getDownLength()==-1){
							RandomAccessFile randOut = new RandomAccessFile(this.saveFile, "rw");
							randOut.seek(this.data.get(i+1));
							this.threads[i] = new DownloadThread(this, this.url, randOut, this.block, this.data.get(i+1), i+1);
							this.threads[i].setPriority(7);
							this.threads[i].start();
						}
					}
				}
				
				/**
				 * 调用接口方法来更新ProgressBar。
				 */
				if(listener!=null) listener.onDownloadSize(this.downloadSize); 
			}
			fileService.delete(this.downloadUrl); //在数据库中删除所有地址为downloadUrl的数据.
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new Exception("下载失败");
		}
		return this.downloadSize;
	}


	/**
	 * 获取HTTP响应头字段.
	 * @param http
	 * @return
	 */
	public static Map<String,String> getHttpResponseHeader(HttpURLConnection http){
		
		Map<String,String> header = new LinkedHashMap<String, String>();
		
		for(int i=0;;i++){
			String mine = http.getHeaderField(i);
			if(mine == null) break;
			header.put(http.getHeaderFieldKey(i), mine);
		}
		return header;
	}
	
	
	/**
	 * 打印HTTP头字段.
	 * @param http
	 */
	public static void printRsponseHeader(HttpURLConnection http){
		Map<String,String> header = getHttpResponseHeader(http);
		for(Map.Entry<String, String> entry:header.entrySet()){
			String key = entry.getKey()!=null?entry.getKey()+":" :"";
			System.out.println(key+entry.getValue());
		}
	}
	
	
	

	/**
	 * 获取线程数。
	 * @return
	 */
	public int getThreadSize(){
		return threads.length;
	}
	
	/**
	 * 获取文件大小.
	 * @return
	 */
	public int getFileSize(){
		return fileSize;
	}
	
	/**
	 * 累计已下载大小.
	 * @param size
	 */
	protected synchronized void append(int size){
		downloadSize += size;
	}
	
	/**
	 * 更新指定线程最后下载位置。
	 * @param threadId 线和ID.
	 * @param pos 最后的下载位置.
	 */
	protected void update(int threadId,int pos){
		this.data.put(threadId, pos);
	}
	
	/**
	 * 保存记录文件.
	 */
	protected synchronized void saveLogFile(){
		this.fileService.update(this.downloadUrl, this.data);
	}

}







































