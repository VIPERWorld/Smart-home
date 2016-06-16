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
            System.out.println("服务器已启动");   
            Socket s = ss.accept();  
            //构建IO流  
            while(true){  
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));  
                String mess = br.readLine();
                System.out.println(mess);
                //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                
                //获取接口访问凭证
        		String accessToken = CommonUtil.getToken("wx457d986945fbd1c4", "15a63dc8a93039b5eac3925366df8208").getAccessToken();
        		//组装文本客服消息
        		String jsonTextMsg = AutoPostMessage.makeTextCustomMessage("o4e8AwHbrZSLda3adeyM75aJ761M",mess);
        		//发送客服消息
        		CustomMessage.sendCustomMessage(accessToken,jsonTextMsg);
                //为了证明是服务器返回的数据，我对mess修改在发送到客户端  
//                String str = "服务器>>"+mess+"\n";  
//                bw.write(str);  
//                bw.flush();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
