package com.pcchat.server;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;


public class ServerThread extends Thread{

	ServerSocket serverSocket;
	public static final int PORT= 8521;
	Vector<ClientThread> clients; //用于存储客户端连接的ClientThread对象.
	Vector<Object> messages;  //用于存储客户端发来的信息。
	BroadCast broadcast; //BroadCast类负责服务器向客户端主播消息。
	
	String ip;
	InetAddress myIPaddress = null; //InetAddress此类表示互联网协议（IP）地址。
	
	public ServerThread(){
		
		//创建数组.
		clients = new Vector<ClientThread>();
		messages = new Vector<Object>();
		try {
			serverSocket = new ServerSocket(PORT); //绑定端口号.
		} catch (Exception e) {	}
		
		try {
			myIPaddress = InetAddress.getLocalHost(); //获取本地IP地址。
		} catch (UnknownHostException e) {
			System.out.print(e.toString());
		}
		
		ip = myIPaddress.getHostAddress(); //得到文本表示的IP地址字符串。
		Server.JTextArea1.append("服务器地址："+ip+" 端口号："+String.valueOf(serverSocket.getLocalPort())+"\n");
		
		broadcast = new BroadCast(this);
		broadcast.start();
	}
	

	@Override
	public void run() {
		while (true) {
			try {
				//阻塞监听并返回一个socket对象.
				Socket socket = serverSocket.accept();
				
				System.out.println(socket.getInetAddress().getHostAddress());
				
				ClientThread clientThread = new ClientThread(socket,this);
				clientThread.start();
				
				if(socket!=null){
					//同步化.
					synchronized (clients) {
						//将客户端加入到Vector数组中.
						clients.addElement(clientThread);
					}
				}
			} catch (IOException e) {
				System.out.println("发生异常："+e.toString());
				System.out.println("建立客户端联机失败!");
				System.exit(2);
			}
			
		}
	}
	
	public void finalize(){
		try {
			serverSocket.close();
		} catch (IOException e) {
			serverSocket = null;
		}
	}

	
}
























