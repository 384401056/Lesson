package com.pcchat.server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ά�ַ������뵥���ͻ��˵������߳�,������տͻ��˷�������Ϣ��
 *
 */
public class ClientThread extends Thread {

	Socket clientSocket;
	
	//�������������д洢��Socket�������������/�������
	DataInputStream dis = null;
	DataOutputStream dos = null;
	
	ServerThread serverThread;
	
	/**
	 * ���캯��.
	 * @param socket �������յ���socket
	 * @param serverThread ����˵��߳�.
	 */
	public ClientThread(Socket socket, ServerThread serverThread){
		clientSocket = socket;
		this.serverThread = serverThread;
		try {
			
			//���������������ݵ�����/�����.
			dis = new DataInputStream(clientSocket.getInputStream());
			dos = new DataOutputStream(clientSocket.getOutputStream());
			
		} catch (IOException e) {
			System.out.println("�����쳣"+e.toString());
			System.out.println("����I/Oͨ��ʧ��");
			System.exit(3);
		}
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String message = dis.readUTF();
				//ͬ����
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






















