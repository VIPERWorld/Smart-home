
#include "bodyinduction.h"
#include "stdio.h"
#include "bsp_esp8266.h"
#include <stdio.h>  
#include <string.h>  

int BodyInduction;

/*******************************************************************************
* Function Name  : 
* Description    :         人体红外感应  引脚的配置         
* Input          : None                                                
* Output         : None
* Return         : None
*******************************************************************************/
void Body_Configuration(void)
{
	GPIO_InitTypeDef GPIO_InitStructure;
	RCC_APB2PeriphClockCmd(	BODY_RCC_CLK,ENABLE);

	
	GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IPD;//感应高电平，是输入下拉吧
	GPIO_InitStructure.GPIO_Speed = GPIO_Speed_2MHz;
	
	GPIO_InitStructure.GPIO_Pin = BODY_GPIO_Pin;
	GPIO_Init(BODY_GPIO_PORT,&GPIO_InitStructure);


}

/*******************************************************************************
* Function Name  : 
* Description    :        人体红外感应函数，当感应到人，输出高电平，并将全局变量BodyInduction置1 
													否则置0;   
* Input          : None                                                
* Output         : None
* Return         : None
*******************************************************************************/

void Run_Induction(void)
{
	if(1 == GPIO_ReadInputDataBit(BODY_GPIO_PORT,BODY_GPIO_Pin) ) BodyInduction=1;
//	else BodyInduction=0;

}

/*******************************************************************************
* Function Name  : 
* Description    :        判断，全局变量BodyInduction  为1 时，运行程序      
* Input          : None                                                
* Output         : None
* Return         : None
*******************************************************************************/
void Run_BodyInduction_Action(void)
{
	char sstr[16]={0};
	if(1==BodyInduction)
	{
		
		sprintf (sstr ,"BodyInduction");
		ESP8266_SendString ( ENABLE, sstr, strlen(sstr), Single_ID_0 );
		printf ( "有人进入" );
	}
if(0==BodyInduction)
	printf ( "没有人进入" );
}

