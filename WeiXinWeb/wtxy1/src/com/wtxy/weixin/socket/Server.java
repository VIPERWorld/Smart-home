package com.wtxy.weixin.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private String info;
	public String Accepte() {
		try {
			//8035���������˿�
			ServerSocket server = new ServerSocket(8035);
			System.out.println("������ʼ");
			// �����ȴ���ֱ������ͻ���socket
			Socket socket = server.accept();
			System.out.println("�˿ڶ���");
			// �������Կͻ��˵���Ϣ
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// System.out.println("Server end");
			// ��ͻ��������Ϣ
			//PrintWriter out = new PrintWriter(socket.getOutputStream());
			// ��ӡ�ͻ���socket�����ַ�������(\n,\r��\r\n)
			//System.out.println("�յ���ݮ�ɷ�������ϢΪ��" + in.readLine());
			info = in.readLine();
			//out.println("Server response");
			//out.flush();
			System.out.println("������Ϣ�ɹ�--->������������Ϣ��ɣ���");
			socket.close();
			server.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
}
	