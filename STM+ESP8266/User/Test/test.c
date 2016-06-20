#include "test.h"
#include "bsp_esp8266.h"
#include "bsp_SysTick.h"
#include <stdio.h>  
#include <string.h>  
#include <stdbool.h>
#include "bsp_dht11.h"
#include "bsp_usart1.h"
#include "GP2Y.h"
#include "bodyinduction.h"
#include "Hal_rgb_led.h"

extern int BodyInduction;
volatile uint8_t ucTcpClosedFlag = 0;

char cStr [ 1500 ] = { 0 };
uint8_t ucId;
char * pCh;


/*********************************************************************************
  * @brief  ESP8266 （Sta Tcp Server）
  * @建立ESP82266 TCP服务端，
	*  1.检测ESP8266是否正常工作
	*	 2.设置为STA_AP模式
	*  3.重启模块
	*  4.加入wifi
	*  5.设置为多连接
	*  6.设置为服务端
  ********************************************************************************/
void ESP8266_StaServer(void)
	{
		printf ( "\r\n正在配置 ESP8266 ......\r\n" );
		macESP8266_CH_ENABLE();
		ESP8266_AT_Test ();
		printf("发送测试AT");
		ESP8266_Net_Mode_Choose ( STA_AP );
		ESP8266_Rst();
		printf("设置为STA");
		while ( ! ESP8266_JoinAP ( macUser_ESP8266_ApSsid, macUser_ESP8266_ApPwd ) );
		printf("加入wifi");	
		ESP8266_Enable_MultipleId ( ENABLE );
		printf("设置多连接");
		while ( !	ESP8266_StartOrShutServer ( ENABLE, macUser_ESP8266_TcpServer_Port, macUser_ESP8266_TcpServer_OverTime ) );
		printf("建立TCP服务端");
		printf ( "\r\n配置 ESP8266 完毕\r\n" );
		
	}
	
/*********************************************************************************
  * @brief  ESP8266 （Sta Tcp Client）
  * @建立ESP82266 TCP客户端，
	*  1.检测ESP8266是否正常工作
	*	 2.设置为STA模式
	*  3.加入wifi
	*  4.设置为多连接
	*  5.设置为客户端
	*  6.设置为透传模式
  ********************************************************************************/
void ESP8266_StaClient(void)
	{
		macESP8266_CH_ENABLE();
		ESP8266_AT_Test ();	
		ESP8266_Net_Mode_Choose ( STA );
		while ( ! ESP8266_JoinAP ( macUser_ESP8266_ApSsid, macUser_ESP8266_ApPwd ) );	 
		ESP8266_Enable_MultipleId ( ENABLE );
		ESP8266_Link_Server ( enumTCP, macUser_ESP8266_TcpServer_IP, macUser_ESP8266_TcpServer_Port1, Single_ID_0 );
		while ( ! ESP8266_UnvarnishSend () );
		printf("透传发送");
		printf ( "\r\n配置 ESP8266 完毕\r\n" );
	}


/*********************************************************************************
  * @brief  系统主通道
	*  1.建立服务端
	*	 2.设置为STA模式
	*  3.加入wifi
	*  4.设置为多连接
	*  5.设置为客户端
	*  6.设置为透传模式
  ********************************************************************************/	
void ESP8266_StaTcpClient_UnvarnishTest ( void )
{
	DHT11_Data_TypeDef DHT11_Data;
	uint8_t ucStatus;
	char cStr [ 100 ] = { 0 };
	
	//建立服务端
	ESP8266_StaServer();
	
	//循环接受发送数据
	while ( 1 )
	{
		char *body;
		char *PM;
		printf ( "\r\n接受数据\r\n" );
		//接受数据
		pCh = strstr ( ESP8266_ReceiveString ( DISABLE ), "+IPD,");
	  printf("收到数据%s\r\n",pCh);
		//取到连接ID
		ucId = * ( pCh + strlen ( "+IPD," ) ) - '0';
		
		//发送湿度数据
		if(strstr(pCh,"A"))
			{
				if( DHT11_Read_TempAndHumidity(&DHT11_Data)==SUCCESS)
					{
						sprintf(cStr,"%d.%d",\
						DHT11_Data.humi_int,DHT11_Data.humi_deci);//cstr数组接收数据
						printf("开始发送");
						ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) ucId  );
						printf ("\r\n湿度为%d.%d\r\n",DHT11_Data.humi_int,DHT11_Data.humi_deci);
						printf("湿度发送完成");
					}
				else if(DHT11_Read_TempAndHumidity(&DHT11_Data)==ERROR)
					{
						printf("读取失败");
					}
			  else continue;
			}
			
			
		//发送温度数据	
		else if(strstr(pCh,"B"))
			{
			  if( DHT11_Read_TempAndHumidity(&DHT11_Data)==SUCCESS)
					{
						sprintf(cStr,"%d.%d",\
						DHT11_Data.temp_int,DHT11_Data.temp_deci);//cstr数组接收数据
						ESP8266_SendString ( DISABLE,cStr, strlen ( cStr), ( ENUM_ID_NO_TypeDef ) ucId  );
						printf ("\r\n温度为%d.%d\r\n",DHT11_Data.temp_int,DHT11_Data.temp_deci);  
						printf("温度发送完成");					 
					}
				else if(DHT11_Read_TempAndHumidity(&DHT11_Data)==ERROR)
					{
						printf("读取失败");
					}
				 else continue;
			 }
		
		 //发送pm2.5的数据
	   else	 if(strstr(pCh,"PM"))
			 {
				 printf("开始采集");
				 GetGP2Y();
		     printf("采集结束");
				 printf("%f\n",GetGP2Y());	
		     sprintf(cStr,"%f",GetGP2Y());
		     ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) ucId  );
		     printf ("\r\nPM2.5=%f\r\n",GetGP2Y());	 
			 }
			 
			 
		 //开灯 
	   else if(strstr(pCh,"20"))
			{
				LED_RGB_Control(255,0,0);
				sprintf(cStr,"open led");
		    ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) ucId  );  
			}
			
			
			//关灯
		 else if(strstr(pCh,"30"))
			{
				LED_RGB_Control(0,0,0);
				sprintf(cStr,"close led");
		    ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) ucId  );
			}
			
			//处理异常请求
		 else if (!strstr(pCh,"A")&&(!strstr(pCh,"B"))&&(!strstr(pCh,"PM"))&&(!strstr(pCh,"20"))&&(!strstr(pCh,"30")))
			{
				sprintf(cStr,"error");
		    ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) (ucId=0)  ); 
		    printf ("指令出错");
			  printf ("UCID=%d",ucId);
				continue ;
			}
			
			
		 else continue;
	   }
}

