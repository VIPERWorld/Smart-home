package com.autosend;

public class AutoPostMessage {

	/**
	 * ��װ�ı��ͷ���Ϣ
	 * 
	 * @param openId ��Ϣ���Ͷ���
	 * @param content �ı���Ϣ����
	 * @return
	 */
	public static String makeTextCustomMessage(String openId,
			String content){
    content = content.replace("\"","\\\"");
    String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
	return String.format(jsonMsg, openId,content);
	}
	
	
	
	
	
	
	
	
	
	
	
}
