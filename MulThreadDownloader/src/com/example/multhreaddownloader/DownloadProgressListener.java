package com.example.multhreaddownloader;

/**
 * ʱʵ��ʾ��ǰ�߳����������Ķ��١�
 * @author ServerAdmin
 *
 */
public interface DownloadProgressListener {	
	/**
	 * �ļ����ع�����ͨ��Message�����Ѿ������ļ���С�����ݸ�handler.
	 * @param size
	 */
	public void onDownloadSize(int size); 
}
