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
	
	//�����װ��ֶ���
	Socket socket;
	Thread thread;
	
	//�����ͻ������������������
	DataInputStream dis;
	DataOutputStream dos;

	//�Ƿ��¼�ı��.
	boolean flag = false;
	
	//name�洢�û�����chat_txt�洢������Ϣ��chat_in�洢�ӷ��������յ�����Ϣ��
	String name,chat_txt,chat_in;
	String ip = null;
	
	//���췽�������ɿͻ��˽��档
	public Client(){
		super("Client");
		getContentPane().setLayout(borderLayout1);
		
		//��ʼ����ť���.
		btnLogin.setText("����������");
		btnLogin.addActionListener(this); //����¼���������
		btnOutLine.setText("�˳�������");
		btnOutLine.addActionListener(this);//����¼���������
		btnSend.setText("����");
		btnSend.addActionListener(this);//����¼���������
		

		//��ʼ��jPanel1�����󣬲������м��������
		jPanel1.add(tfIp);
		jPanel1.add(tfName);
		jPanel1.add(btnLogin);
		jPanel1.add(btnOutLine);
		jPanel1.add(jScrollPane);
		jScrollPane.getViewport().add(jTextArea);
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
		
		
		//��ʼ��jPanel2�����󣬲������м��������
		jPanel2.add(tfStr);
		jPanel2.add(btnSend);
		this.getContentPane().add(jPanel2,java.awt.BorderLayout.SOUTH);
		
		this.setSize(400,400);
		this.setVisible(true);
		
	}
	
	//�ͻ���������.
	public static void main(String[] args){
		Client client = new Client();
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//�ͻ����߳�������Ķ�����
	public void run() {
		
		//ѭ��ִ�У�������һֱ����������Ƿ�����Ϣ��
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
		
		//��½��ť�¼���
		if(e.getSource()==btnLogin){
			name = tfName.getText();
			ip = tfIp.getText();
			
			if(name!=null && ip!=null){
				try {
					
					//�Է������������ӡ�
					Socket socket = new Socket(ip,PORT);
					
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());
					
					//��ȡ��ǰϵͳʱ�䡣����ʽ��ʱ�䡣
					Date now = new Date(System.currentTimeMillis());
					SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
					String nowStr = format.format(now);
					
					//��������
					dos.writeUTF("$$"+name+nowStr+" ������!");
					
					
				} catch (IOException ex) {
					System.out.println("Can not connect");
					return;
				}
				
				thread = new Thread(this);//��Runnable���롣
				thread.start(); //ִ��Runnable��run����.
				
				flag = true; //˵���Ѿ���¼�ɹ���
			}
		}
		
		//���Ͱ�ť�¼�.
		else if(e.getSource()== btnSend){
			
			if(!flag) return;
			
			chat_txt = tfStr.getText();
			if(chat_txt!=null){
				
				//��ȡ��ǰϵͳʱ�䡣����ʽ��ʱ�䡣
				Date now = new Date(System.currentTimeMillis());
				SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
				String nowStr = format.format(now);
				
				try {
					//���ԣ��������������Ϣ��
					dos.writeUTF("^_^"+ name +" "+nowStr+"��"+chat_txt);

				} catch (IOException e2) {
					
				}
			}
		}
		
		//�˳������Ұ�ť�¼���
		else if(e.getSource()==btnOutLine){
			if(flag){
				
				flag = false;

				try {
					dos.writeUTF("##"+name+" ������!");
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
























