package com.pcchat.server;
import java.io.IOException;

/**
 * �����run����ÿ200����ִ��һ�Σ����������ӵ��������Ŀͻ��˷���message����δ���͵���Ϣ��
 * @author �����
 *
 */
public class BroadCast extends Thread {

	ClientThread clientThread;
	ServerThread serverThread;
	
	String str;
	
	public BroadCast(ServerThread serverThread){
		this.serverThread = serverThread;
	}

	@Override
	public void run() {
		while (true){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
			}
			
			synchronized (serverThread.messages) {
				if(serverThread.messages.isEmpty()){
					continue;
				}
				
				//��ȡ�������˴洢����Ҫ���͵ĵ�һ��������Ϣ��
				str = (String)this.serverThread.messages.firstElement();
			}
			
			synchronized (serverThread.clients) {
				for(int i=0;i<serverThread.clients.size();i++){
					clientThread = (ClientThread)serverThread.clients.elementAt(i);
					try {
						clientThread.dos.writeUTF(str);
					} catch (IOException e) {}
				}
				
				//��Vector������ɾ���Ѿ����͹�������������Ϣ��
				this.serverThread.messages.removeElement(str);
			}
		}
	}
	
}
