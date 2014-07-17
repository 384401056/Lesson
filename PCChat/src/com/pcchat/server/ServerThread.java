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
	Vector<ClientThread> clients; //���ڴ洢�ͻ������ӵ�ClientThread����.
	Vector<Object> messages;  //���ڴ洢�ͻ��˷�������Ϣ��
	BroadCast broadcast; //BroadCast�ฺ���������ͻ���������Ϣ��
	
	String ip;
	InetAddress myIPaddress = null; //InetAddress�����ʾ������Э�飨IP����ַ��
	
	public ServerThread(){
		
		//��������.
		clients = new Vector<ClientThread>();
		messages = new Vector<Object>();
		try {
			serverSocket = new ServerSocket(PORT); //�󶨶˿ں�.
		} catch (Exception e) {	}
		
		try {
			myIPaddress = InetAddress.getLocalHost(); //��ȡ����IP��ַ��
		} catch (UnknownHostException e) {
			System.out.print(e.toString());
		}
		
		ip = myIPaddress.getHostAddress(); //�õ��ı���ʾ��IP��ַ�ַ�����
		Server.JTextArea1.append("��������ַ��"+ip+" �˿ںţ�"+String.valueOf(serverSocket.getLocalPort())+"\n");
		
		broadcast = new BroadCast(this);
		broadcast.start();
	}
	

	@Override
	public void run() {
		while (true) {
			try {
				//��������������һ��socket����.
				Socket socket = serverSocket.accept();
				
				System.out.println(socket.getInetAddress().getHostAddress());
				
				ClientThread clientThread = new ClientThread(socket,this);
				clientThread.start();
				
				if(socket!=null){
					//ͬ����.
					synchronized (clients) {
						//���ͻ��˼��뵽Vector������.
						clients.addElement(clientThread);
					}
				}
			} catch (IOException e) {
				System.out.println("�����쳣��"+e.toString());
				System.out.println("�����ͻ�������ʧ��!");
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
























