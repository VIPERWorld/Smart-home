 
#include "stm32f10x.h"
#include "bsp_usart1.h"
#include "bsp_SysTick.h"
#include "bsp_esp8266.h"
#include "test.h"
#include "bsp_dht11.h"
#include "GP2Y.h"
#include "stdio.h"
#include "stm32f10x_gpio.h"
#include "stm32f10x_adc.h"
#include "bodyinduction.h"
#include "Hal_rgb_led.h"
/**
  * @brief  ������
  * @param  ��
  * @retval ��
  */
void DHT11_GPIO1_Config ( void )
	{		
		/*����һ��GPIO_InitTypeDef���͵Ľṹ��*/
		GPIO_InitTypeDef GPIO_InitStructure;
		/*����macDHT11_Dout_GPIO_PORT������ʱ��*/
		macDHT11_Dout_SCK_APBxClock_FUN ( macDHT11_Dout_GPIO_CLK, ENABLE );	
		/*ѡ��Ҫ���Ƶ�macDHT11_Dout_GPIO_PORT����*/															   
		GPIO_InitStructure.GPIO_Pin = macDHT11_Dout_GPIO_PIN1;	
		/*��������ģʽΪͨ���������*/
  	GPIO_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;   
		/*������������Ϊ50MHz */   
  	GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz; 
		/*���ÿ⺯������ʼ��macDHT11_Dout_GPIO_PORT*/
  	GPIO_Init ( macDHT11_Dout_GPIO_PORT, &GPIO_InitStructure );		  
	}


int main ( void )
	{
		/* ��ʼ�� */
		USARTx_Config ();                                                              //��ʼ������1
		SysTick_Init ();                                                               //���� SysTick Ϊ 1ms �ж�һ�� 
		ESP8266_Init ();                                                               //��ʼ��WiFiģ��ʹ�õĽӿں�����
		DHT11_Init ();																																	//DHT11��ʼ��
		DHT11_GPIO1_Config ( );																													
		GP2Yinit();                                                                      //gp2y��ʼ��
		Body_Configuration();                                                            //��������ʼ��
		RGB_KEY_GPIO_Init();
		RGB_LED_Init();	
		
		ESP8266_StaTcpClient_UnvarnishTest ();	
	}


/*********************************************END OF FILE**********************/
