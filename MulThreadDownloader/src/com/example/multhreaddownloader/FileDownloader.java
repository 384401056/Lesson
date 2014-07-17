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
	private int downloadSize = 0;  //�������ļ���С��
	private int fileSize = 0;  //ԭʼ�ļ���С��
	private DownloadThread[] threads;  //�߳�����
	private URL url; //���ص�ַ��
	private File saveFile;  //���ر����ļ�.
	private File logFile;   //���ؼ�¼�ļ�.
	private Map<Integer,Integer> data = new ConcurrentHashMap<Integer, Integer>(); //������߳�id��������ص�λ�á�
	
	private int block;  //ÿ���߳����صĴ�С ��
	private String downloadUrl;  //����·��.
	
	
	
	/**
	 * �����ļ�������.
	 * @param context
	 * @param downloadUrl ����·��
	 * @param fileSaveDir �ļ�����Ŀ¼
	 * @param threadNum �����߳���
	 */
	public FileDownloader(Context context,String downloadUrl,File fileSaveDir,int threadNum){
		try{
			
			this.contex = context;
			this.downloadUrl = downloadUrl; //����·��.
			fileService = new FileService(context);  //�������ݿ��ļ������ࡣ
			this.url = new URL(downloadUrl);
			if(!fileSaveDir.exists()) fileSaveDir.mkdirs(); //�жϴ洢Ŀ¼�Ƿ���ڣ��������͵���File���mkdirs()����ȥ����.
			
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
				this.fileSize = conn.getContentLength(); //��ȡ�ļ���С��
				
				if(this.fileSize<=0) throw new RuntimeException("�޷���֪�ļ���С��");
				String fileName = getFileName(conn); //��ȡ�ļ���
				this.saveFile = new File(fileSaveDir,fileName); //�����ļ�. 
				
				Map<Integer,Integer> logdata = fileService.getData(downloadUrl); //ͨ�����ص�ַ����ѯ���ݿ��ȡ���߳�id,����������λ�á�
				
				//����õ����ݣ�˵����ǰ���ع����Ͱ����ݷ���data�У�dataȫ�ֱ����������߳�������ص�λ�á�
				if(logdata.size()>0){
					data.putAll(logdata);
				}
				
				this.block = this.fileSize / this.threads.length + 1; //�õ�ÿ���߳�Ҫ���صĴ�С��
				
				if(this.data.size()==this.threads.length){
					for(int i=0;i<this.threads.length;i++){
						//this.data.get(i+1) �õ�һ���߳����һ�����ص����
						//this.block*i �õ�ÿ���̵߳���ʼλ�á�blockÿ���߳�Ҫ���صĴ�С������i��Ϊ��ʼλ���ˡ�����Ҫע�⡣
						//���ԣ�������������͵õ���ÿ���߳�Ŀǰ�����˶������ݡ����ѭ���ۼӾ͵õ������̹߳������˶��١�
						this.downloadSize += this.data.get(i+1)-(this.block*i);
					}
					System.out.println("�Ѿ����صĳ���."+this.downloadSize);
				}
			}
			else{
				throw new RuntimeException("��������Ӧ����");
			}
		}catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException("���Ӳ�������·��!");
		}
	}


	/**
	 * ��ȡҪ���ص��ļ���
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
			filename = UUID.randomUUID()+".tmp"; //Ĭ��ȡһ���ļ���.
		}
		return filename;
	}


	/**
	 * ��ʼ�����ļ�.
	 * @param listener �������������ı仯���������Ҫ�˽�ʵʱ�������ݣ���������Ϊnull;
	 * @return  �������ļ���С��
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
				
				//�ж��Ѿ����ص����ݺ�Ҫ���ص������Ƿ���ȣ��Լ���������ص�λ���Ƿ�С���ļ���С��
				if(downLength < this.block && this.data.get(i+1) < this.fileSize){
					RandomAccessFile randOut = new RandomAccessFile(this.saveFile, "rw"); //������������ļ�.
					if(this.fileSize>0) randOut.setLength(this.fileSize); //�����ļ���С.
					randOut.seek(this.data.get(i+1));  //��ÿ���߳���������λ����Ϊ��ʼ������λ�á�
					
					//����һ���̶߳������๹�캯���г�ʼ��һЩ������ע���ʱ��û�п�ʼ���ء�
					this.threads[i] = new DownloadThread(this, this.url, randOut, this.block, this.data.get(i+1),i+1);
					this.threads[i].setPriority(7);  //���ø�Ȩ��.
					this.threads[i].start(); //��ʼִ���߳����е�run()������
					
				}else{
					this.threads[i] = null;
				}
			}
			
			/**
			 * ���²����ǵ�δ������صĲ��ȷ�����
			 */
			this.fileService.save(this.downloadUrl,this.data); //�����̵߳ĵ�ǰ����λ�õ����ݿ��С�
			boolean notFinish = true;  //����δ��ɵı�־��
			
			/**
			 * ѭ���ж��Ƿ�������ɡ�
			 */
			while(notFinish){
				Thread.sleep(900);
				notFinish = false;//����������ɡ�
				
				for(int i=0;i< this.threads.length;i++){
					//����߳�Ϊ�գ������߳�û���������.
					if(this.threads[i] != null && !this.threads[i].isFinish()){
						notFinish = true; 
						//�������ʧ�ܣ����������ء�
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
				 * ���ýӿڷ���������ProgressBar��
				 */
				if(listener!=null) listener.onDownloadSize(this.downloadSize); 
			}
			fileService.delete(this.downloadUrl); //�����ݿ���ɾ�����е�ַΪdownloadUrl������.
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new Exception("����ʧ��");
		}
		return this.downloadSize;
	}


	/**
	 * ��ȡHTTP��Ӧͷ�ֶ�.
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
	 * ��ӡHTTPͷ�ֶ�.
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
	 * ��ȡ�߳�����
	 * @return
	 */
	public int getThreadSize(){
		return threads.length;
	}
	
	/**
	 * ��ȡ�ļ���С.
	 * @return
	 */
	public int getFileSize(){
		return fileSize;
	}
	
	/**
	 * �ۼ������ش�С.
	 * @param size
	 */
	protected synchronized void append(int size){
		downloadSize += size;
	}
	
	/**
	 * ����ָ���߳��������λ�á�
	 * @param threadId �ߺ�ID.
	 * @param pos ��������λ��.
	 */
	protected void update(int threadId,int pos){
		this.data.put(threadId, pos);
	}
	
	/**
	 * �����¼�ļ�.
	 */
	protected synchronized void saveLogFile(){
		this.fileService.update(this.downloadUrl, this.data);
	}

}







































