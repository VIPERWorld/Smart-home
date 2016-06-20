#ifndef __BODYINDUCTION_H
#define __BODYINDUCTION_H


/******************************************************************************
                                 ��������Ӧ ͷ�ļ�                        
******************************************************************************/

#include "stm32f10x.h"

#define BODY_GPIO_PORT   				GPIOB
#define BODY_GPIO_Pin					GPIO_Pin_11
#define BODY_RCC_CLK	       RCC_APB2Periph_GPIOA

void Body_Configuration(void);
void Run_Induction(void);  //ֻ������������ܸı�ȫ�ֱ���
void Run_BodyInduction_Action(void);

#endif
