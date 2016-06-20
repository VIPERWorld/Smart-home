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
			//8035本机监听端口
			ServerSocket server = new ServerSocket(8035);
			System.out.println("监听开始");
			// 阻塞等待，直到传入客户端socket
			Socket socket = server.accept();
			System.out.println("端口堵塞");
			// 输入来自客户端的信息
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// System.out.println("Server end");
			// 向客户端输出信息
			//PrintWriter out = new PrintWriter(socket.getOutputStream());
			// 打印客户端socket发送字符，按行(\n,\r或\r\n)
			//System.out.println("收到树莓派发来的信息为：" + in.readLine());
			info = in.readLine();
			//out.println("Server response");
			//out.flush();
			System.out.println("返回信息成功--->服务器接收消息完成！！");
			socket.close();
			server.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
}
	