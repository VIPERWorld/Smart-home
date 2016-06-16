package com.autosend;

public class AutoPostMessage {

	/**
	 * 组装文本客服消息
	 * 
	 * @param openId 消息发送对象
	 * @param content 文本消息处理
	 * @return
	 */
	public static String makeTextCustomMessage(String openId,
			String content){
    content = content.replace("\"","\\\"");
    String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
	return String.format(jsonMsg, openId,content);
	}
	
	
	
	
	
	
	
	
	
	
	
}
