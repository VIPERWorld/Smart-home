package com.wtxy.weixin.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.wtxy.weixin.media.Media;
import com.wtxy.weixin.message.Image;
import com.wtxy.weixin.message.ImageMessage;
import com.wtxy.weixin.message.TextMessage;
import com.wtxy.weixin.message.Video;
import com.wtxy.weixin.message.VideoMessage;
import com.wtxy.weixin.message.Voice;
import com.wtxy.weixin.message.VoiceMessage;
import com.wtxy.weixin.pojo.Token;
import com.wtxy.weixin.pojo.WeixinMedia;
import com.wtxy.weixin.socket.Client;
import com.wtxy.weixin.socket.Server;
import com.wtxy.weixin.util.CommonUtil;
import com.wtxy.weixin.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author Ran
 * @date 2016-04-21
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXML = "出现错误，请重新点击！";
		
		TextMessage textMessage = new TextMessage();
		ImageMessage imageMessage = new ImageMessage();
		VoiceMessage voiceMessage = new VoiceMessage();
		VideoMessage videoMessage = new VideoMessage();
		
		// 默认返回的文本消息内容
		String respContent = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXML(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
	        Image image = new Image(); 
	    	Voice voice = new Voice();
	    	Video video = new Video();
	    	
	    	imageMessage.setFromUserName(toUserName);
	    	imageMessage.setToUserName(fromUserName);
	    	imageMessage.setCreateTime(new Date().getTime());
	    	imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
	    	imageMessage.setImage(image);
	    	
	    	voiceMessage.setFromUserName(toUserName);
	    	voiceMessage.setToUserName(fromUserName);
	    	voiceMessage.setCreateTime(new Date().getTime());
	    	voiceMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
	    	voiceMessage.setVoice(voice);
	    	
	    	videoMessage.setFromUserName(toUserName);
	    	videoMessage.setToUserName(fromUserName);
	    	videoMessage.setCreateTime(new Date().getTime());
	    	videoMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
	    	videoMessage.setVideo(video);
	    	
	    	String appId = "wxaba96c1d5934b842";
			String appSecret = "ab7b56feb36cf8656dd776f3dd4cce52";
			Token token = CommonUtil.getToken(appId, appSecret);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content"); 
				if(content.equals("on")){
					//开灯
					String str = Client.SendInfo("20");
					respContent = str;
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}else if(content.equals("off")){
					//关灯
					String str = Client.SendInfo("30");
					respContent = str;
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}else if(content.equals("open")){
					//开空调
					String str = Client.SendInfo("40");
					respContent = str;
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}else if(content.equals("close")){
					//关空调
					String str = Client.SendInfo("50"); 
					respContent = str;
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}
			}else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				//语音事件
				String str = Client.SendInfo("11");
		    	byte[] b = Base64.decodeBase64(str);
		    	String voiceFilePath = "D:\\MyEclipse\\workspace\\wtxy2\\WebRoot\\voice\\test.mp3";//新生成的声音
		    	OutputStream out = new FileOutputStream(voiceFilePath);    
	            out.write(b);
	            out.flush();
	            out.close();
		    	WeixinMedia weixinMedia = Media.uploadMedia(token.getAccessToken(), "voice", "http://localhost:8080/coreServlet/voice/test.mp3");  
		    	voice.setMediaId(weixinMedia.getMediaId());
		    	respXML = MessageUtil.messageToXML(voiceMessage); 
			}
			
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "欢迎关注 智能管家wtxy!\n请选择我们为您提供的相应服务";
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}

				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					//处理菜单点击事件
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("0")) { 
						//获取温度
						String str = Client.SendInfo("B");
						respContent = "当前温度为:"+str+"℃";
						textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("1")){
				    	//获取湿度
				    	String str = Client.SendInfo("A");
						respContent = "当前湿度为:"+str+"%"; 
						textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("2")){
				    	//实况截图
				    	String str = Client.SendInfo("10");
				    	byte[] b = Base64.decodeBase64(str);
				    	String imgFilePath = "D:\\MyEclipse\\workspace\\wtxy2\\WebRoot\\image\\1.jpg";//新生成的图片
				    	OutputStream out = new FileOutputStream(imgFilePath);    
			             out.write(b);
			            out.flush();
			            out.close();
				    	WeixinMedia weixinMedia = Media.uploadMedia(token.getAccessToken(), "image", "http://localhost:8080/coreServlet/image/1.jpg");  
				    	image.setMediaId(weixinMedia.getMediaId());
				    	respXML = MessageUtil.messageToXML(imageMessage);
                          
				    }else if(eventKey.equals("3")){
				    	//PM2.5查看
				    	String str = Client.SendInfo("PM");
				    	respContent = "当前PM值:"+str+"mg/m^3";
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("4")){
				    	//灯控
				    	respContent = "您选择的是灯控设置，请输入"+"\n"+"on:开灯"+"\n"+"off:关灯";
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("5")){
				    	//空调
				    	String str = "您选择的是灯控设置，请输入"+"\n"+"open:开空调"+"\n"+"close:关空调";
				    	respContent = str;
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("6")){
				    	//热水器
				    	respContent = "该功能尚未开发，敬请期待！";
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("7")){
				    	//对话
				    	respContent = "请发送语音消息进行对讲！";
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("8")){
				    	//开门
				    	String str = Client.SendInfo("60");
				    	respContent = str;
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("9")){
				    	//楼宇监控
				    	String str = Client.SendInfo("70");
				    	byte[] b = Base64.decodeBase64(str); 
				    	String vedioFilePath = "D:\\MyEclipse\\workspace\\wtxy2\\WebRoot\\video\\02.mp4";//新生成的视频
				    	OutputStream out = new FileOutputStream(vedioFilePath);    
			            out.write(b);
			            out.flush();
			            out.close(); 
				    	WeixinMedia weixinMedia = Media.uploadMedia(token.getAccessToken(), "video", "http://localhost:8080/coreServlet/video/wt.mp4");  
				    	video.setMediaId(weixinMedia.getMediaId());
				    	respXML = MessageUtil.messageToXML(videoMessage);
				    }else if(eventKey.equals("10")){
				    	//安全报警
				    	Server server = new Server();
				    	respContent = "安全警报已开启！";	
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }
			  }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXML;
	}
}
