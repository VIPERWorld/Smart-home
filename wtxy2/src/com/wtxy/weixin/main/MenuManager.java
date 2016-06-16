package com.wtxy.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wtxy.weixin.menu.Button;
import com.wtxy.weixin.menu.ClickButton;
import com.wtxy.weixin.menu.ComplexButton;
import com.wtxy.weixin.menu.Menu;
import com.wtxy.weixin.pojo.Token;
import com.wtxy.weixin.util.CommonUtil;
import com.wtxy.weixin.util.MenuUtil;

/**
 * �˵���������
*/
public class MenuManager {

	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	/**
	 * ����˵��ṹ
	 * 
	 * @return
	 */

	private static Menu getMenu() {
		
		ClickButton btn11 = new ClickButton();
		btn11.setName("�鿴�¶�");
		btn11.setType("click");
		btn11.setKey("0");
		
		ClickButton btn12 = new ClickButton();
		btn12.setName("�鿴ʪ��");
		btn12.setType("click");
		btn12.setKey("1");

		ClickButton btn13 = new ClickButton();
		btn13.setName("ʵ����ͼ");
		btn13.setType("click");
		btn13.setKey("2");
		
       ClickButton btn14 = new ClickButton();
		btn14.setName("PM2.5�鿴");
		btn14.setType("click");
		btn14.setKey("3");
		
	    ClickButton btn21 = new ClickButton();
		btn21.setName("�ƿ�");
		btn21.setType("click");
		btn21.setKey("4");
		
        ClickButton btn22 = new ClickButton();
		btn22.setName("�յ�");
		btn22.setType("click");
		btn22.setKey("5");

		ClickButton btn23 = new ClickButton();
		btn23.setName("��ˮ��");
		btn23.setType("click");
		btn23.setKey("6");
		
	    ClickButton btn31 = new ClickButton();
		btn31.setName("�Ի�");
		btn31.setType("click");
		btn31.setKey("7");
		
		 ClickButton btn32 = new ClickButton();
		btn32.setName("����");
		btn32.setType("click");
		btn32.setKey("8");
		
		ClickButton btn33 = new ClickButton();
		btn33.setName("¥��鿴");
		btn33.setType("click");
		btn33.setKey("9");
		
		ClickButton btn34 = new ClickButton();
		btn34.setName("��������");
		btn34.setType("click");
		btn34.setKey("10");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("�������");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13,btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("�ҵ����");
		mainBtn2.setSub_button(new Button[] { btn21, btn22,btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("¥��ϵͳ");
		mainBtn3.setSub_button(new Button[] { btn31,btn32,btn33,btn34});

		Menu menu = new Menu();
		menu.setButton(new Button[] {mainBtn1,mainBtn2,mainBtn3});

		return menu;
	}

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wxaba96c1d5934b842";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "ab7b56feb36cf8656dd776f3dd4cce52";

		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// �����˵�
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// �жϲ˵��������
			if (result)
				log.info("�����ɹ�");
			
			else
				log.info("�˵�����ʧ�ܣ�");
			
		}
	}
	}