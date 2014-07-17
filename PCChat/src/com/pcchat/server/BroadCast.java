package com.pcchat.server;
import java.io.IOException;

/**
 * 该类的run方法每200毫秒执行一次，向所有连接到服务器的客户端发送message中尚未发送的信息。
 * @author 高谚宾
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
				
				//获取服务器端存储的需要发送的第一条数据信息。
				str = (String)this.serverThread.messages.firstElement();
			}
			
			synchronized (serverThread.clients) {
				for(int i=0;i<serverThread.clients.size();i++){
					clientThread = (ClientThread)serverThread.clients.elementAt(i);
					try {
						clientThread.dos.writeUTF(str);
					} catch (IOException e) {}
				}
				
				//从Vector数组中删除已经发送过的那条数据信息。
				this.serverThread.messages.removeElement(str);
			}
		}
	}
	
}
