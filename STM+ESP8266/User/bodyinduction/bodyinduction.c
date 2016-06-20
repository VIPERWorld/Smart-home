
#include "bodyinduction.h"
#include "stdio.h"
#include "bsp_esp8266.h"
#include <stdio.h>  
#include <string.h>  

int BodyInduction;

/*******************************************************************************
* Function Name  : 
* Description    :         ��������Ӧ  ���ŵ�����         
* Input          : None                                                
* Output         : None
* Return         : None
*******************************************************************************/
void Body_Configuration(void)
{
	GPIO_InitTypeDef GPIO_InitStructure;
	RCC_APB2PeriphClockCmd(	BODY_RCC_CLK,ENABLE);

	
	GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IPD;//��Ӧ�ߵ�ƽ��������������
	GPIO_InitStructure.GPIO_Speed = GPIO_Speed_2MHz;
	
	GPIO_InitStructure.GPIO_Pin = BODY_GPIO_Pin;
	GPIO_Init(BODY_GPIO_PORT,&GPIO_InitStructure);


}

/*******************************************************************************
* Function Name  : 
* Description    :        ��������Ӧ����������Ӧ���ˣ�����ߵ�ƽ������ȫ�ֱ���BodyInduction��1 
													������0;   
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
* Description    :        �жϣ�ȫ�ֱ���BodyInduction  Ϊ1 ʱ�����г���      
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
		printf ( "���˽���" );
	}
if(0==BodyInduction)
	printf ( "û���˽���" );
}

