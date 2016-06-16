package com.wtxy.weixin.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.autosend.AutoPostMessage;
import com.autosend.CustomMessage;
import com.wtxy.weixin.util.CommonUtil;
  
public class Server {  
    public static void main(String[] args) {  
        try {  
            ServerSocket ss = new ServerSocket(8086);  
            System.out.println("������������");   
            Socket s = ss.accept();  
            //����IO��  
            while(true){  
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));  
                String mess = br.readLine();
                System.out.println(mess);
                //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                
                //��ȡ�ӿڷ���ƾ֤
        		String accessToken = CommonUtil.getToken("wx457d986945fbd1c4", "15a63dc8a93039b5eac3925366df8208").getAccessToken();
        		//��װ�ı��ͷ���Ϣ
        		String jsonTextMsg = AutoPostMessage.makeTextCustomMessage("o4e8AwHbrZSLda3adeyM75aJ761M",mess);
        		//���Ϳͷ���Ϣ
        		CustomMessage.sendCustomMessage(accessToken,jsonTextMsg);
                //Ϊ��֤���Ƿ��������ص����ݣ��Ҷ�mess�޸��ڷ��͵��ͻ���  
//                String str = "������>>"+mess+"\n";  
//                bw.write(str);  
//                bw.flush();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
