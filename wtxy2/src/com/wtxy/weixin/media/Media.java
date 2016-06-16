package com.wtxy.weixin.media;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import com.wtxy.weixin.pojo.WeixinMedia;
import com.wtxy.weixin.util.CommonUtil;

public class Media {

	/**
	 * @param args
	 */

	public static WeixinMedia uploadMedia(String accessToken, String type, String mediaFileUrl) {  
	    WeixinMedia weixinMedia = null;  
	    // 拼装请求地址  
	    String uploadMediaUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE"; 
	    uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);  
	  
	    // 定义数据分隔符  
	    String boundary = "------------7da2e536604c8";  
	    try {  
	        URL uploadUrl = new URL(uploadMediaUrl);  
	        HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();  
	        uploadConn.setDoOutput(true);  
	        uploadConn.setDoInput(true);  
	        uploadConn.setRequestMethod("POST");  
	        // 设置请求头Content-Type  
	        uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);  
	        // 获取媒体文件上传的输出流（往微信服务器写数据）  
	        OutputStream outputStream = uploadConn.getOutputStream();  
	  
	        URL mediaUrl = new URL(mediaFileUrl);  
	        HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();  
	        meidaConn.setDoOutput(true);  
	        meidaConn.setRequestMethod("GET");  
	  
	        // 从请求头中获取内容类型  
	        String contentType = meidaConn.getHeaderField("Content-Type");  
	        // 根据内容类型判断文件扩展名  
	        String fileExt =  CommonUtil.getFileExt(contentType);  
	        // 请求体开始  
	        outputStream.write(("--" + boundary + "\r\n").getBytes());  
	        outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt).getBytes());  
	        outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());  
	  
	        // 获取媒体文件的输入流（读取文件）  
	        BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());  
	        byte[] buf = new byte[8096];  
	        int size = 0;  
	        while ((size = bis.read(buf)) != -1) {  
	            // 将媒体文件写到输出流（往微信服务器写数据）  
	            outputStream.write(buf, 0, size);  
	        }  
	        // 请求体结束  
	        outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());  
	        outputStream.close();  
	        bis.close();  
	        meidaConn.disconnect();  
	  
	        // 获取媒体文件上传的输入流（从微信服务器读数据）  
	        InputStream inputStream = uploadConn.getInputStream();  
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	        StringBuffer buffer = new StringBuffer();  
	        String str = null;  
	        while ((str = bufferedReader.readLine()) != null) {  
	            buffer.append(str);  
	        }  
	        bufferedReader.close();  
	        inputStreamReader.close();  
	        // 释放资源  
	        inputStream.close();  
	        inputStream = null;  
	        uploadConn.disconnect();  
	  
	        // 使用JSON-lib解析返回结果  
	        JSONObject jsonObject = JSONObject.fromObject(buffer.toString());  
	        // 测试打印结果  
	        System.out.println("打印测试结果"+jsonObject);  
	        weixinMedia = new WeixinMedia();  
	        weixinMedia.setType(jsonObject.getString("type"));  
	        // type等于 缩略图（thumb） 时的返回结果和其它类型不一样  
	        if ("thumb".equals(type))
	            weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));  
	        else  
	            weixinMedia.setMediaId(jsonObject.getString("media_id"));  
	            weixinMedia.setCreateAt(jsonObject.getInt("created_at"));  
	    } catch (Exception e) {  
	        weixinMedia = null;  
	        String error = String.format("上传媒体文件失败：%s", e);  
	        System.out.println(error);  
	    }  
	    return weixinMedia;  
	}  
	
	/** 
     * 获取媒体文件 
     * @param accessToken 接口访问凭证 
     * @param media_id 媒体文件id 
     * @param savePath 文件在服务器上的存储路径 
     * */  
    public static String downloadMedia(String accessToken, String mediaId, String savePath) {  
        String filePath = null;  
        // 拼接请求地址  
        String requestUrl = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";  
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);  
        System.out.println(requestUrl);  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            conn.setDoInput(true);  
            conn.setRequestMethod("GET");  
  
            if (!savePath.endsWith("/")) {  
                savePath += "/";  
            }  
            // 根据内容类型获取扩展名  CommonUtil.getFileExt(contentType); 
            String fileExt = CommonUtil.getFileExt(conn.getHeaderField("Content-Type"));  
            // 将mediaId作为文件名  
            filePath = savePath + mediaId + fileExt;  
  
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());  
            FileOutputStream fos = new FileOutputStream(new File(filePath));  
            byte[] buf = new byte[8096];  
            int size = 0;  
            while ((size = bis.read(buf)) != -1)  
                fos.write(buf, 0, size);  
            fos.close();  
            bis.close();  
  
            conn.disconnect();  
            String info = String.format("下载媒体文件成功，filePath=" + filePath);  
            System.out.println(info);  
        } catch (Exception e) {  
            filePath = null;  
            String error = String.format("下载媒体文件失败：%s", e);  
            System.out.println(error);  
        }  
        return filePath;  
    }  
}
