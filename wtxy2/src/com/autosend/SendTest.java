package com.autosend;

import com.wtxy.weixin.util.CommonUtil;

public class SendTest {
	public static void main(){
		//��ȡ�ӿڷ���ƾ֤
		String accessToken = CommonUtil.getToken("wx457d986945fbd1c4", "15a63dc8a93039b5eac3925366df8208").getAccessToken();
		//��װ�ı��ͷ���Ϣ
		String jsonTextMsg = AutoPostMessage.makeTextCustomMessage("o4e8AwHbrZSLda3adeyM75aJ761M","������");
		//���Ϳͷ���Ϣ
		CustomMessage.sendCustomMessage(accessToken,jsonTextMsg);
	}
}
