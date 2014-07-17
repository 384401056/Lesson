package com.pcchat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements Runnable,ActionListener {


	private static final long serialVersionUID= 1L;

	public static final int PORT = 8521;
	
	BorderLayout borderLayout1 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();
	
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JButton btnSend = new JButton();
	JButton btnLogin = new JButton();
	JButton btnOutLine = new JButton();
	JTextField tfName = new JTextField(10);
	JTextField tfStr = new JTextField(15);
	JTextField tfIp = new JTextField(10);
	JTextArea jTextArea = new JTextArea(15,30);
	JScrollPane jScrollPane = new JScrollPane();
	
	//声明套按字对象。
	Socket socket;
	Thread thread;
	
	//声明客户端数据输入输出流。
	DataInputStream dis;
	DataOutputStream dos;

	//是否登录的标记.
	boolean flag = false;
	
	//name存储用户名，chat_txt存储发言信息，chat_in存储从服务器接收到的信息。
	String name,chat_txt,chat_in;
	String ip = null;
	
	//构造方法，生成客户端界面。
	public Client(){
		super("Client");
		getContentPane().setLayout(borderLayout1);
		
		//初始化按钮组件.
		btnLogin.setText("进入聊天室");
		btnLogin.addActionListener(this); //添加事件监听器。
		btnOutLine.setText("退出聊天室");
		btnOutLine.addActionListener(this);//添加事件监听器。
		btnSend.setText("发送");
		btnSend.addActionListener(this);//添加事件监听器。
		

		//初始化jPanel1面板对象，并向其中加入组件。
		jPanel1.add(tfIp);
		jPanel1.add(tfName);
		jPanel1.add(btnLogin);
		jPanel1.add(btnOutLine);
		jPanel1.add(jScrollPane);
		jScrollPane.getViewport().add(jTextArea);
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
		
		
		//初始化jPanel2面板对象，并向其中加入组件。
		jPanel2.add(tfStr);
		jPanel2.add(btnSend);
		this.getContentPane().add(jPanel2,java.awt.BorderLayout.SOUTH);
		
		this.setSize(400,400);
		this.setVisible(true);
		
	}
	
	//客户端主函数.
	public static void main(String[] args){
		Client client = new Client();
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//客户端线程启动后的动作。
	public void run() {
		
		//循环执行，作用是一直监听服务端是否有消息。
		while(flag){
			try {
				chat_in = dis.readUTF();
				jTextArea.append(chat_in+"\n\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//登陆按钮事件。
		if(e.getSource()==btnLogin){
			name = tfName.getText();
			ip = tfIp.getText();
			
			if(name!=null && ip!=null){
				try {
					
					//对服务器建立连接。
					Socket socket = new Socket(ip,PORT);
					
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());
					
					//获取当前系统时间。并格式化时间。
					Date now = new Date(System.currentTimeMillis());
					SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
					String nowStr = format.format(now);
					
					//发送数据
					dos.writeUTF("$$"+name+nowStr+" 上线了!");
					
					
				} catch (IOException ex) {
					System.out.println("Can not connect");
					return;
				}
				
				thread = new Thread(this);//将Runnable传入。
				thread.start(); //执行Runnable的run方法.
				
				flag = true; //说明已经登录成功。
			}
		}
		
		//发送按钮事件.
		else if(e.getSource()== btnSend){
			
			if(!flag) return;
			
			chat_txt = tfStr.getText();
			if(chat_txt!=null){
				
				//获取当前系统时间。并格式化时间。
				Date now = new Date(System.currentTimeMillis());
				SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
				String nowStr = format.format(now);
				
				try {
					//发言，向服务器发送信息。
					dos.writeUTF("^_^"+ name +" "+nowStr+"："+chat_txt);

				} catch (IOException e2) {
					
				}
			}
		}
		
		//退出聊天室按钮事件。
		else if(e.getSource()==btnOutLine){
			if(flag){
				
				flag = false;

				try {
					dos.writeUTF("##"+name+" 下线了!");
					dos.close();
					dis.close();
					socket.close();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
			
			this.setVisible(false);
		}
		
	}
}
























