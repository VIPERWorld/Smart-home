package com.autosend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.wtxy.weixin.util.CommonUtil;

public class CustomMessage {
	/**
	 * ���Ϳͷ���Ϣ
	 * 
	 * @param accessToken �ӿڷ���ƾ֤
	 * @param jsonMsg json ��ʽ�Ŀͷ���Ϣ������touser��msgtype����Ϣ���ݣ�
	 * @return
	 */
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	public static boolean sendCustomMessage(String accessToken,String jsonMsg){
		log.info("��Ϣ���ݣ�{}",jsonMsg);
		boolean result = false;
		//ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/" +
				"custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		//���Ϳͷ���Ϣ
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		
		if (null != jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			//String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode){
				result = true;
				//log.info("�ͷ���Ϣ���ͳɹ� errcode:{} errmsg:{}",errorCode,errorMsg);
			}else{
				//log.error("�ͷ���Ϣ����ʧ�� errcode:{} errmsg:{}",errorCode,errorMsg);
			}
		}
	return result;
	}

}
