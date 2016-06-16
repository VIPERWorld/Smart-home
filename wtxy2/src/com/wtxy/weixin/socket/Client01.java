package com.wtxy.weixin.socket;

import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.Socket;  
import java.net.UnknownHostException;  
import java.util.Scanner;  
  
public class Client01 {  
    public static void main(String[] args) {  
        try {  
            Socket s = new Socket("127.0.0.1",8066);  
            System.out.println("�ͻ���IP:"+s.getLocalAddress()+"�˿�"+s.getPort());  
            //����IO��  
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));  
              
            //�����������룺  
            Scanner scanner = new Scanner(System.in);  
            while(true){  
                System.out.println("�����뷢����Ϣ���ݣ�");  
                bw.write(scanner.nextLine()+"\n");  
                bw.newLine();  
                bw.flush();  
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));  
                //��ȡ���������ص���Ϣ����  
                System.out.println(s.getInetAddress().getLocalHost()+":"+s.getPort()+">>"+br.readLine());  
            }  
              
              
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
    }  
}  
