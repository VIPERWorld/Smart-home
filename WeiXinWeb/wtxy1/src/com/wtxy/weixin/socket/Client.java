package com.wtxy.weixin.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	private Socket socket = null;
	   private BufferedReader reader = null;
	   private BufferedWriter writer = null;

	   public Client(InetAddress address, int port) throws IOException
	   {
	      socket = new Socket(address, port);
	      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	   }

	   public void send(String msg) throws IOException
	   {
	      writer.write(msg, 0, msg.length());
	      writer.flush();
	   }

	   public String recv() throws IOException
	   {
		   System.out.println("接受成功");
	      return reader.readLine();
	   }

	   public static String SendInfo(String info)
	   {
		   String response = null;
	      try {
	    	  
	         InetAddress host = InetAddress.getByName("10.20.32.234");
	         Client client = new Client(host, 8066);

	         client.send(info);
	         //System.out.println("发送成功");
	         response = client.recv();
	         //System.out.println("Client received: " + response);
	      }
	      catch (IOException e) {
	         System.out.println("Caught Exception: " + e.toString());
	      }
	      return response;
	   }
}
