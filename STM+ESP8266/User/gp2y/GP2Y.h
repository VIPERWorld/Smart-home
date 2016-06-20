 #ifndef __GP2Y_H
#define __GP2Y_H
//#include "stm32f10x_lib.h"
#define GP2Y_High GPIO_SetBits(GPIOB,GPIO_Pin_1);
#define GP2Y_Low  GPIO_ResetBits(GPIOB,GPIO_Pin_1);

void GP2Yinit(void);
float GetGP2Y(void);
#endif
