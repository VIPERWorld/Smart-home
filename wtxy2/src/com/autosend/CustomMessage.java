package com.autosend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.wtxy.weixin.util.CommonUtil;

public class CustomMessage {
	/**
	 * 发送客服消息
	 * 
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json 格式的客服消息（包括touser、msgtype和消息内容）
	 * @return
	 */
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	public static boolean sendCustomMessage(String accessToken,String jsonMsg){
		log.info("消息内容：{}",jsonMsg);
		boolean result = false;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/" +
				"custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		//发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		
		if (null != jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			//String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode){
				result = true;
				//log.info("客服消息发送成功 errcode:{} errmsg:{}",errorCode,errorMsg);
			}else{
				//log.error("客服消息发送失败 errcode:{} errmsg:{}",errorCode,errorMsg);
			}
		}
	return result;
	}

}
