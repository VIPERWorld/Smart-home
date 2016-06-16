package com.autosend;

import com.wtxy.weixin.util.CommonUtil;

public class SendTest {
	public static void main(){
		//获取接口访问凭证
		String accessToken = CommonUtil.getToken("wx457d986945fbd1c4", "15a63dc8a93039b5eac3925366df8208").getAccessToken();
		//组装文本客服消息
		String jsonTextMsg = AutoPostMessage.makeTextCustomMessage("o4e8AwHbrZSLda3adeyM75aJ761M","哈哈！");
		//发送客服消息
		CustomMessage.sendCustomMessage(accessToken,jsonTextMsg);
	}
}
