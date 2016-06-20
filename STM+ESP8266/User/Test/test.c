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
  * @brief  ESP8266 ��Sta Tcp Server��
  * @����ESP82266 TCP����ˣ�
	*  1.���ESP8266�Ƿ���������
	*	 2.����ΪSTA_APģʽ
	*  3.����ģ��
	*  4.����wifi
	*  5.����Ϊ������
	*  6.����Ϊ�����
  ********************************************************************************/
void ESP8266_StaServer(void)
	{
		printf ( "\r\n�������� ESP8266 ......\r\n" );
		macESP8266_CH_ENABLE();
		ESP8266_AT_Test ();
		printf("���Ͳ���AT");
		ESP8266_Net_Mode_Choose ( STA_AP );
		ESP8266_Rst();
		printf("����ΪSTA");
		while ( ! ESP8266_JoinAP ( macUser_ESP8266_ApSsid, macUser_ESP8266_ApPwd ) );
		printf("����wifi");	
		ESP8266_Enable_MultipleId ( ENABLE );
		printf("���ö�����");
		while ( !	ESP8266_StartOrShutServer ( ENABLE, macUser_ESP8266_TcpServer_Port, macUser_ESP8266_TcpServer_OverTime ) );
		printf("����TCP�����");
		printf ( "\r\n���� ESP8266 ���\r\n" );
		
	}
	
/*********************************************************************************
  * @brief  ESP8266 ��Sta Tcp Client��
  * @����ESP82266 TCP�ͻ��ˣ�
	*  1.���ESP8266�Ƿ���������
	*	 2.����ΪSTAģʽ
	*  3.����wifi
	*  4.����Ϊ������
	*  5.����Ϊ�ͻ���
	*  6.����Ϊ͸��ģʽ
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
		printf("͸������");
		printf ( "\r\n���� ESP8266 ���\r\n" );
	}


/*********************************************************************************
  * @brief  ϵͳ��ͨ��
	*  1.���������
	*	 2.����ΪSTAģʽ
	*  3.����wifi
	*  4.����Ϊ������
	*  5.����Ϊ�ͻ���
	*  6.����Ϊ͸��ģʽ
  ********************************************************************************/	
void ESP8266_StaTcpClient_UnvarnishTest ( void )
{
	DHT11_Data_TypeDef DHT11_Data;
	uint8_t ucStatus;
	char cStr [ 100 ] = { 0 };
	
	//���������
	ESP8266_StaServer();
	
	//ѭ�����ܷ�������
	while ( 1 )
	{
		char *body;
		char *PM;
		printf ( "\r\n��������\r\n" );
		//��������
		pCh = strstr ( ESP8266_ReceiveString ( DISABLE ), "+IPD,");
	  printf("�յ�����%s\r\n",pCh);
		//ȡ������ID
		ucId = * ( pCh + strlen ( "+IPD," ) ) - '0';
		
		//����ʪ������
		if(strstr(pCh,"A"))
			{
				if( DHT11_Read_TempAndHumidity(&DHT11_Data)==SUCCESS)
					{
						sprintf(cStr,"%d.%d",\
						DHT11_Data.humi_int,DHT11_Data.humi_deci);//cstr�����������
						printf("��ʼ����");
						ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) ucId  );
						printf ("\r\nʪ��Ϊ%d.%d\r\n",DHT11_Data.humi_int,DHT11_Data.humi_deci);
						printf("ʪ�ȷ������");
					}
				else if(DHT11_Read_TempAndHumidity(&DHT11_Data)==ERROR)
					{
						printf("��ȡʧ��");
					}
			  else continue;
			}
			
			
		//�����¶�����	
		else if(strstr(pCh,"B"))
			{
			  if( DHT11_Read_TempAndHumidity(&DHT11_Data)==SUCCESS)
					{
						sprintf(cStr,"%d.%d",\
						DHT11_Data.temp_int,DHT11_Data.temp_deci);//cstr�����������
						ESP8266_SendString ( DISABLE,cStr, strlen ( cStr), ( ENUM_ID_NO_TypeDef ) ucId  );
						printf ("\r\n�¶�Ϊ%d.%d\r\n",DHT11_Data.temp_int,DHT11_Data.temp_deci);  
						printf("�¶ȷ������");					 
					}
				else if(DHT11_Read_TempAndHumidity(&DHT11_Data)==ERROR)
					{
						printf("��ȡʧ��");
					}
				 else continue;
			 }
		
		 //����pm2.5������
	   else	 if(strstr(pCh,"PM"))
			 {
				 printf("��ʼ�ɼ�");
				 GetGP2Y();
		     printf("�ɼ�����");
				 printf("%f\n",GetGP2Y());	
		     sprintf(cStr,"%f",GetGP2Y());
		     ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) ucId  );
		     printf ("\r\nPM2.5=%f\r\n",GetGP2Y());	 
			 }
			 
			 
		 //���� 
	   else if(strstr(pCh,"20"))
			{
				LED_RGB_Control(255,0,0);
				sprintf(cStr,"open led");
		    ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) ucId  );  
			}
			
			
			//�ص�
		 else if(strstr(pCh,"30"))
			{
				LED_RGB_Control(0,0,0);
				sprintf(cStr,"close led");
		    ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) ucId  );
			}
			
			//�����쳣����
		 else if (!strstr(pCh,"A")&&(!strstr(pCh,"B"))&&(!strstr(pCh,"PM"))&&(!strstr(pCh,"20"))&&(!strstr(pCh,"30")))
			{
				sprintf(cStr,"error");
		    ESP8266_SendString ( DISABLE, cStr, strlen ( cStr ), ( ENUM_ID_NO_TypeDef ) (ucId=0)  ); 
		    printf ("ָ�����");
			  printf ("UCID=%d",ucId);
				continue ;
			}
			
			
		 else continue;
	   }
}

