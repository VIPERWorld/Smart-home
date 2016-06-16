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
 * 菜单管理器类
*/
public class MenuManager {

	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */

	private static Menu getMenu() {
		
		ClickButton btn11 = new ClickButton();
		btn11.setName("查看温度");
		btn11.setType("click");
		btn11.setKey("0");
		
		ClickButton btn12 = new ClickButton();
		btn12.setName("查看湿度");
		btn12.setType("click");
		btn12.setKey("1");

		ClickButton btn13 = new ClickButton();
		btn13.setName("实况截图");
		btn13.setType("click");
		btn13.setKey("2");
		
       ClickButton btn14 = new ClickButton();
		btn14.setName("PM2.5查看");
		btn14.setType("click");
		btn14.setKey("3");
		
	    ClickButton btn21 = new ClickButton();
		btn21.setName("灯控");
		btn21.setType("click");
		btn21.setKey("4");
		
        ClickButton btn22 = new ClickButton();
		btn22.setName("空调");
		btn22.setType("click");
		btn22.setKey("5");

		ClickButton btn23 = new ClickButton();
		btn23.setName("热水器");
		btn23.setType("click");
		btn23.setKey("6");
		
	    ClickButton btn31 = new ClickButton();
		btn31.setName("对话");
		btn31.setType("click");
		btn31.setKey("7");
		
		 ClickButton btn32 = new ClickButton();
		btn32.setName("开门");
		btn32.setType("click");
		btn32.setKey("8");
		
		ClickButton btn33 = new ClickButton();
		btn33.setName("楼宇查看");
		btn33.setType("click");
		btn33.setKey("9");
		
		ClickButton btn34 = new ClickButton();
		btn34.setName("开启警报");
		btn34.setType("click");
		btn34.setKey("10");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("环境监控");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13,btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("家电调节");
		mainBtn2.setSub_button(new Button[] { btn21, btn22,btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("楼宇系统");
		mainBtn3.setSub_button(new Button[] { btn31,btn32,btn33,btn34});

		Menu menu = new Menu();
		menu.setButton(new Button[] {mainBtn1,mainBtn2,mainBtn3});

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxaba96c1d5934b842";
		// 第三方用户唯一凭证密钥
		String appSecret = "ab7b56feb36cf8656dd776f3dd4cce52";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result)
				log.info("创建成功");
			
			else
				log.info("菜单创建失败！");
			
		}
	}
	}