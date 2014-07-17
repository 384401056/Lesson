package com.example.multhreaddownloader;

/**
 * 时实表示当前线程下载数量的多少。
 * @author ServerAdmin
 *
 */
public interface DownloadProgressListener {	
	/**
	 * 文件下载过程中通过Message传递已经下载文件大小的数据给handler.
	 * @param size
	 */
	public void onDownloadSize(int size); 
}
