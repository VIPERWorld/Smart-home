package com.wtxy.weixin.media;

import com.wtxy.weixin.pojo.Token;
import com.wtxy.weixin.pojo.WeixinMedia;
import com.wtxy.weixin.util.CommonUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// �������û�Ψһƾ֤
		String appId = "wx9b0660964a1ff315";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "4cade28e04a9e1d383a258cc3233f236";
		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);

		WeixinMedia weixinMedia = Media.uploadMedia(token.getAccessToken(), "image", "http://localhost:8080/coreServlet/image/2.jpg");  
		    //media_id  
		System.out.println("media_id��"+weixinMedia.getMediaId());  
		    //����  
		System.out.println("���ͣ�"+weixinMedia.getType());  
		    //ʱ���  
		System.out.println("ʱ�����"+weixinMedia.getCreateAt());  
		    //��ӡ���  
		if(null!=weixinMedia){  
		      System.out.println("�ϴ��ɹ���");  
	     } 
		      
		    System.out.println("�ָ���*********************************************************");  
		    /** 
		     * ���ض�ý���ļ� 
		     */  
		    //String savePath = UpImage01.downloadMedia(token.getAccessToken(), weixinMedia.getMediaId(), "E:/download");  
		    //System.out.println("���سɹ�֮�󱣴��ڱ��صĵ�ַΪ��"+savePath);
	}
}
