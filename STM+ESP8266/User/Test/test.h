#ifndef  __TEST_H
#define	 __TEST_H



#include "stm32f10x.h"



/********************************** �û���Ҫ���õĲ���**********************************/
#define      macUser_ESP8266_ApSsid                       "PI"                //Ҫ���ӵ��ȵ������
#define      macUser_ESP8266_ApPwd                        "raspberry"           //Ҫ���ӵ��ȵ����Կ


//#define      macUser_ESP8266_ApSsid                       "amazon3"                //Ҫ���ӵ��ȵ������
//#define      macUser_ESP8266_ApPwd                        "1234567890"           //Ҫ���ӵ��ȵ����Կ


#define      macUser_ESP8266_TcpServer_IP                 "192.168.191.1"      //Ҫ���ӵķ������� IP
#define      macUser_ESP8266_TcpServer_Port               "8090"               //Ҫ���ӵķ������Ķ˿�
#define      macUser_ESP8266_TcpServer_Port1               "8091"
#define   macUser_ESP8266_TcpServer_OverTime   "1800" 


/********************************** �ⲿȫ�ֱ��� ***************************************/
extern volatile uint8_t ucTcpClosedFlag;



/********************************** ���Ժ������� ***************************************/
void                     ESP8266_StaTcpClient_UnvarnishTest  ( void );



#endif

