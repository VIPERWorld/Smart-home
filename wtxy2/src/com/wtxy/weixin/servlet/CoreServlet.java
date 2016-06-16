package com.wtxy.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wtxy.weixin.service.CoreService;
import com.wtxy.weixin.util.SignUtil;


public class CoreServlet extends HttpServlet {

	private static final long serialVersionUID = 4440739483644821987L;

	/**
	 * ����У�飨ȷ����������΢�ŷ�������
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ΢�ż���ǩ��
		String signature = request.getParameter("signature");
		// ʱ���
		String timestamp = request.getParameter("timestamp");
		// �����
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// ����У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * ����΢�ŷ�������������Ϣ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");

				// ���ղ���΢�ż���ǩ���� ʱ����������
				String signature = request.getParameter("signature");
				String timestamp = request.getParameter("timestamp");
				String nonce = request.getParameter("nonce");

				PrintWriter out = response.getWriter();
				// ����У��
				if (SignUtil.checkSignature(signature, timestamp, nonce)) {
					// ���ú��ķ�������մ�������
					String respXML = CoreService.processRequest(request);
					out.write(respXML);
				}
				out.close();
				out = null;
	}

}
