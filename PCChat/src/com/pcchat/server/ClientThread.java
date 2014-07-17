package com.pcchat.server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 维持服务器与单个客户端的连接线程,负责接收客户端发来的信息。
 *
 */
public class ClientThread extends Thread {

	Socket clientSocket;
	
	//声明服务器端中存储的Socket对象的数据输入/输出流。
	DataInputStream dis = null;
	DataOutputStream dos = null;
	
	ServerThread serverThread;
	
	/**
	 * 构造函数.
	 * @param socket 服务器收到的socket
	 * @param serverThread 服务端的线程.
	 */
	public ClientThread(Socket socket, ServerThread serverThread){
		clientSocket = socket;
		this.serverThread = serverThread;
		try {
			
			//创建服务器端数据的输入/输出流.
			dis = new DataInputStream(clientSocket.getInputStream());
			dos = new DataOutputStream(clientSocket.getOutputStream());
			
		} catch (IOException e) {
			System.out.println("发生异常"+e.toString());
			System.out.println("建立I/O通道失败");
			System.exit(3);
		}
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String message = dis.readUTF();
				//同步化
				synchronized (serverThread.messages) {
					if(message!=null){
						serverThread.messages.addElement(message);
						Server.JTextArea1.append(message+'\n');
					}
				}
			} catch (Exception e) {
				break;
			}
		}
	}


}






















