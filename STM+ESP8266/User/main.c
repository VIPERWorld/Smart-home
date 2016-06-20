 
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
  * @brief  主函数
  * @param  无
  * @retval 无
  */
void DHT11_GPIO1_Config ( void )
	{		
		/*定义一个GPIO_InitTypeDef类型的结构体*/
		GPIO_InitTypeDef GPIO_InitStructure;
		/*开启macDHT11_Dout_GPIO_PORT的外设时钟*/
		macDHT11_Dout_SCK_APBxClock_FUN ( macDHT11_Dout_GPIO_CLK, ENABLE );	
		/*选择要控制的macDHT11_Dout_GPIO_PORT引脚*/															   
		GPIO_InitStructure.GPIO_Pin = macDHT11_Dout_GPIO_PIN1;	
		/*设置引脚模式为通用推挽输出*/
  	GPIO_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;   
		/*设置引脚速率为50MHz */   
  	GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz; 
		/*调用库函数，初始化macDHT11_Dout_GPIO_PORT*/
  	GPIO_Init ( macDHT11_Dout_GPIO_PORT, &GPIO_InitStructure );		  
	}


int main ( void )
	{
		/* 初始化 */
		USARTx_Config ();                                                              //初始化串口1
		SysTick_Init ();                                                               //配置 SysTick 为 1ms 中断一次 
		ESP8266_Init ();                                                               //初始化WiFi模块使用的接口和外设
		DHT11_Init ();																																	//DHT11初始化
		DHT11_GPIO1_Config ( );																													
		GP2Yinit();                                                                      //gp2y初始化
		Body_Configuration();                                                            //人体红外初始化
		RGB_KEY_GPIO_Init();
		RGB_LED_Init();	
		
		ESP8266_StaTcpClient_UnvarnishTest ();	
	}


/*********************************************END OF FILE**********************/
