#ifndef _GOKIT_H
#define _GOKIT_H

#include "stm32f10x.h"
#include "bsp_dht11.h"
#include "Hal_infrared.h"
#include "bsp_usart1.h"
#include "ringbuffer.h"
#include "Protocol.h"
#include <string.h>

__packed	typedef struct	
{
	uint8_t       		Infrared;
	uint8_t       		Temperature;
	uint8_t       		Humidity;
	uint8_t				Alert;
	uint8_t				Fault;
}ReadTypeDef_t; 

__packed typedef struct	
{
	uint8_t							Attr_Flags;
}WirteTypeDef_t;

void HW_Init(void);
void Printf_SystemRccClocks(void);
void GizWits_GatherSensorData(void);
void GizWits_ControlDeviceHandle(void);
void SW_Init(void);
void KEY_Handle(void);
//void GizWits_WiFiStatueHandle(uint16_t wifiStatue);

#endif
