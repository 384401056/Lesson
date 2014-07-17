package com.pcchat.server;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * �������˳��򣬸�������Լ����������ServerThread����������������߳�ServerThread�ֲ���
 * BroadCast��ClientThread�̡߳�
 */

public class Server extends JFrame implements ActionListener {
	BorderLayout borderLayout1 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();
	
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JScrollPane jScrollPane = new JScrollPane();
	
	static JTextArea JTextArea1 = new JTextArea();
	boolean bool = false, start = false;
	
	ServerThread serverThread;
	Thread thread;
	
	public Server(){
		super("Server");
		getContentPane().setLayout(borderLayout1);
		
		//��ʼ����ť���.
		jButton1.setText("����������");
		jButton1.addActionListener(this);
		jButton2.setText("�رշ�����");
		jButton2.addActionListener(this);
		
		//��ʼ��jPanel1�����󣬲������м��������
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
		jPanel1.add(jButton1);
		jPanel1.add(jButton2);
		
		//��ʼ��jPanel2�����󣬲������м��������
		JTextArea1.setText("");
		jPanel2.setLayout(borderLayout2);
		jPanel2.add(jScrollPane,java.awt.BorderLayout.CENTER);
		jScrollPane.getViewport().add(JTextArea1);
		this.getContentPane().add(jPanel2,java.awt.BorderLayout.CENTER);

		this.setSize(400,400);
		this.setVisible(true);
		
		
	}

	public static void main(String[] args){
		Server server = new Server();
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//�����������еİ�ť�¼�,ActionListener���еķ�����
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jButton1){
			serverThread = new ServerThread();
			serverThread.start();
		}else if(e.getSource()==jButton2){
			bool = false;
			start = false;
			serverThread.finalize();
			this.setVisible(false);
		}
	}

}































