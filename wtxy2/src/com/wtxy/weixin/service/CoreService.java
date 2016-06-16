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
 * ���ķ�����
 * 
 * @author Ran
 * @date 2016-04-21
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXML = "���ִ��������µ����";
		
		TextMessage textMessage = new TextMessage();
		ImageMessage imageMessage = new ImageMessage();
		VoiceMessage voiceMessage = new VoiceMessage();
		VideoMessage videoMessage = new VideoMessage();
		
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = null;
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXML(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
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

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content"); 
				if(content.equals("on")){
					//����
					String str = Client.SendInfo("20");
					respContent = str;
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}else if(content.equals("off")){
					//�ص�
					String str = Client.SendInfo("30");
					respContent = str;
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}else if(content.equals("open")){
					//���յ�
					String str = Client.SendInfo("40");
					respContent = str;
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}else if(content.equals("close")){
					//�ؿյ�
					String str = Client.SendInfo("50"); 
					respContent = str;
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}
			}else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				//�����¼�
				String str = Client.SendInfo("11");
		    	byte[] b = Base64.decodeBase64(str);
		    	String voiceFilePath = "D:\\MyEclipse\\workspace\\wtxy2\\WebRoot\\voice\\test.mp3";//�����ɵ�����
		    	OutputStream out = new FileOutputStream(voiceFilePath);    
	            out.write(b);
	            out.flush();
	            out.close();
		    	WeixinMedia weixinMedia = Media.uploadMedia(token.getAccessToken(), "voice", "http://localhost:8080/coreServlet/voice/test.mp3");  
		    	voice.setMediaId(weixinMedia.getMediaId());
		    	respXML = MessageUtil.messageToXML(voiceMessage); 
			}
			
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "��ӭ��ע ���ܹܼ�wtxy!\n��ѡ������Ϊ���ṩ����Ӧ����";
					textMessage.setContent(respContent);
					respXML = MessageUtil.messageToXML(textMessage);
				}

				// �Զ���˵�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					//����˵�����¼�
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("0")) { 
						//��ȡ�¶�
						String str = Client.SendInfo("B");
						respContent = "��ǰ�¶�Ϊ:"+str+"��";
						textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("1")){
				    	//��ȡʪ��
				    	String str = Client.SendInfo("A");
						respContent = "��ǰʪ��Ϊ:"+str+"%"; 
						textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("2")){
				    	//ʵ����ͼ
				    	String str = Client.SendInfo("10");
				    	byte[] b = Base64.decodeBase64(str);
				    	String imgFilePath = "D:\\MyEclipse\\workspace\\wtxy2\\WebRoot\\image\\1.jpg";//�����ɵ�ͼƬ
				    	OutputStream out = new FileOutputStream(imgFilePath);    
			             out.write(b);
			            out.flush();
			            out.close();
				    	WeixinMedia weixinMedia = Media.uploadMedia(token.getAccessToken(), "image", "http://localhost:8080/coreServlet/image/1.jpg");  
				    	image.setMediaId(weixinMedia.getMediaId());
				    	respXML = MessageUtil.messageToXML(imageMessage);
                          
				    }else if(eventKey.equals("3")){
				    	//PM2.5�鿴
				    	String str = Client.SendInfo("PM");
				    	respContent = "��ǰPMֵ:"+str+"mg/m^3";
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("4")){
				    	//�ƿ�
				    	respContent = "��ѡ����ǵƿ����ã�������"+"\n"+"on:����"+"\n"+"off:�ص�";
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("5")){
				    	//�յ�
				    	String str = "��ѡ����ǵƿ����ã�������"+"\n"+"open:���յ�"+"\n"+"close:�ؿյ�";
				    	respContent = str;
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("6")){
				    	//��ˮ��
				    	respContent = "�ù�����δ�����������ڴ���";
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("7")){
				    	//�Ի�
				    	respContent = "�뷢��������Ϣ���жԽ���";
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("8")){
				    	//����
				    	String str = Client.SendInfo("60");
				    	respContent = str;
				    	textMessage.setContent(respContent);
						respXML = MessageUtil.messageToXML(textMessage);
				    }else if(eventKey.equals("9")){
				    	//¥����
				    	String str = Client.SendInfo("70");
				    	byte[] b = Base64.decodeBase64(str); 
				    	String vedioFilePath = "D:\\MyEclipse\\workspace\\wtxy2\\WebRoot\\video\\02.mp4";//�����ɵ���Ƶ
				    	OutputStream out = new FileOutputStream(vedioFilePath);    
			            out.write(b);
			            out.flush();
			            out.close(); 
				    	WeixinMedia weixinMedia = Media.uploadMedia(token.getAccessToken(), "video", "http://localhost:8080/coreServlet/video/wt.mp4");  
				    	video.setMediaId(weixinMedia.getMediaId());
				    	respXML = MessageUtil.messageToXML(videoMessage);
				    }else if(eventKey.equals("10")){
				    	//��ȫ����
				    	Server server = new Server();
				    	respContent = "��ȫ�����ѿ�����";	
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
