#ifndef __BODYINDUCTION_H
#define __BODYINDUCTION_H


/******************************************************************************
                                 人体红外感应 头文件                        
******************************************************************************/

#include "stm32f10x.h"

#define BODY_GPIO_PORT   				GPIOB
#define BODY_GPIO_Pin					GPIO_Pin_11
#define BODY_RCC_CLK	       RCC_APB2Periph_GPIOA

void Body_Configuration(void);
void Run_Induction(void);  //只有这个函数才能改变全局变量
void Run_BodyInduction_Action(void);

#endif
