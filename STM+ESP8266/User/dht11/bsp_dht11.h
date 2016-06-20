#ifndef __DHT11_H
#define	__DHT11_H



#include "stm32f10x.h"



/************************** DHT11 �������Ͷ���********************************/
typedef struct
{
	uint8_t  humi_int;		//ʪ�ȵ���������
	uint8_t  humi_deci;	 	//ʪ�ȵ�С������
	uint8_t  temp_int;	 	//�¶ȵ���������
	uint8_t  temp_deci;	 	//�¶ȵ�С������
	uint8_t  check_sum;	 	//У���
		                 
} DHT11_Data_TypeDef;


#define      macDHT11_Dout_SCK_APBxClock_FUN              RCC_APB2PeriphClockCmd
#define      macDHT11_Dout_GPIO_CLK                       RCC_APB2Periph_GPIOB

#define      macDHT11_Dout_GPIO_PORT                      GPIOB
#define      macDHT11_Dout_GPIO_PIN                       GPIO_Pin_7

#define      macDHT11_Dout_GPIO_PIN1                       GPIO_Pin_1

/************************** DHT11 �����궨��********************************/
#define      macDHT11_Dout_0	                            GPIO_ResetBits ( macDHT11_Dout_GPIO_PORT, macDHT11_Dout_GPIO_PIN ) 
#define      macDHT11_Dout_1	                            GPIO_SetBits ( macDHT11_Dout_GPIO_PORT, macDHT11_Dout_GPIO_PIN) 
#define      macDHT11_Dout_2                              GPIO_SetBits ( macDHT11_Dout_GPIO_PORT, macDHT11_Dout_GPIO_PIN1) 
#define      macDHT11_Dout_IN()	                          GPIO_ReadInputDataBit (  macDHT11_Dout_GPIO_PORT, macDHT11_Dout_GPIO_PIN ) 



/************************** DHT11 �������� ********************************/
void                     DHT11_Init                      ( void );
uint8_t                  DHT11_Read_TempAndHumidity      ( DHT11_Data_TypeDef * DHT11_Data );


void DHT11_GPIO_Config ( void );
uint8_t DHT11_ReadByte ( void );
void DHT11_Mode_Out_PP(void);
void DHT11_Mode_IPU(void);
#endif /* __DHT11_H */







