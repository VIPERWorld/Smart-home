package com.demo.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.wtxy.weixin.socket.Client;

public class ToolServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = (String)request.getParameter("method");
		if(method.equals("temp")) {
			temp(request, response);
		}else if(method.equals("humidity")) {
			humidity(request, response);
		}else if(method.equals("image")) {
			image(request, response);
		}else if(method.equals("pm")) {
			pm(request, response);
		}else if(method.equals("lighton")) {
			lighton(request, response);
		}else if(method.equals("lightoff")) {
			lightoff(request, response);
		}else if(method.equals("air1")) {
			air1(request, response);
		}else if(method.equals("air2")) {
			air2(request, response);
		}else if(method.equals("waterheater")) {
			waterheater(request, response);
		}else if(method.equals("dialogue")) {
			dialogue(request, response);
		}else if(method.equals("kaimen")) {
			kaimen(request, response);
		}else if(method.equals("louyu")) {
			louyu(request, response);
		}
	

	}


	private void air2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String str1 = Client.SendInfo("50");
		session.setAttribute("airconditioner1",str1);
		response.sendRedirect("airconditioner0.jsp");		
	}


	private void air1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String str1 = Client.SendInfo("40");
		session.setAttribute("airconditioner0", str1);
		response.sendRedirect("airconditioner1.jsp");
	}


	private void lighton(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String str1 = Client.SendInfo("20");
		session.setAttribute("lighton", str1);
		response.sendRedirect("lighton.jsp");
	}


	private void humidity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String str1 = Client.SendInfo("A");
		session.setAttribute("humidity", str1);
		response.sendRedirect("temp.jsp");
		
	}


	private void louyu(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String str = Client.SendInfo("70");
    	byte[] b = Base64.decodeBase64(str);
    	String vedioFilePath = "D:\\MyEclipse\\workspace\\wtxy1\\WebRoot\\video\\01.mp4";//新生成的视频
    	OutputStream out = new FileOutputStream(vedioFilePath);    
        out.write(b);
        out.flush();
        out.close();
		response.sendRedirect("louyu.jsp");
	}


	private void kaimen(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String str1 = Client.SendInfo("60");
		session.setAttribute("kaimen", str1);
		response.sendRedirect("kaimen.jsp");
	}


	private void dialogue(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String str = Client.SendInfo("11");
    	byte[] b = Base64.decodeBase64(str);
    	String voiceFilePath = "D:\\MyEclipse\\workspace\\wtxy1\\WebRoot\\voice\\test.mp3";//新生成的声音
    	OutputStream out = new FileOutputStream(voiceFilePath);    
        out.write(b);
        out.flush();
        out.close();
		response.sendRedirect("dialogue.jsp");
	}


	private void waterheater(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect("waterheater.jsp");
	}


	private void lightoff(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String str1 = Client.SendInfo("30");
		session.setAttribute("lightoff", str1);
		response.sendRedirect("lightoff.jsp");
	}


	private void pm(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String str1 = Client.SendInfo("PM");
		session.setAttribute("pm", str1);
		response.sendRedirect("pm.jsp");
	}


	private void image(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String str = Client.SendInfo("10");
		byte[] b = Base64.decodeBase64(str);
    	String imgFilePath = "D:\\MyEclipse\\workspace\\wtxy1\\WebRoot\\image\\1.jpg";//新生成的图片
    	OutputStream out = new FileOutputStream(imgFilePath);    
        out.write(b);
        out.flush();
        out.close();
		response.sendRedirect("image.jsp");
	}


	private void temp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String str1 = Client.SendInfo("B");
		session.setAttribute("temp", str1);
		response.sendRedirect("temp.jsp");
	}


}
