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
		// 第三方用户唯一凭证
		String appId = "wx9b0660964a1ff315";
		// 第三方用户唯一凭证密钥
		String appSecret = "4cade28e04a9e1d383a258cc3233f236";
		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		WeixinMedia weixinMedia = Media.uploadMedia(token.getAccessToken(), "image", "http://localhost:8080/coreServlet/image/2.jpg");  
		    //media_id  
		System.out.println("media_id："+weixinMedia.getMediaId());  
		    //类型  
		System.out.println("类型："+weixinMedia.getType());  
		    //时间戳  
		System.out.println("时间戳："+weixinMedia.getCreateAt());  
		    //打印结果  
		if(null!=weixinMedia){  
		      System.out.println("上传成功！");  
	     } 
		      
		    System.out.println("分割线*********************************************************");  
		    /** 
		     * 下载多媒体文件 
		     */  
		    //String savePath = UpImage01.downloadMedia(token.getAccessToken(), weixinMedia.getMediaId(), "E:/download");  
		    //System.out.println("下载成功之后保存在本地的地址为："+savePath);
	}
}
