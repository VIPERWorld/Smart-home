#include "GP2Y.h"
#include "gokit.h"
#include "stm32f10x_adc.h"
#include "bsp_SysTick.h"
#include "bsp_esp8266.h"
    u16 AD_PM;
void GP2Yinit(void)
{ 
//
    ADC_InitTypeDef A_InitStructure;
    GPIO_InitTypeDef G_InitStructure;	//PA0
    GPIO_InitTypeDef Gpio_InitStructure;//PB1
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA |RCC_APB2Periph_ADC1 , ENABLE );   //使能ADC2通道时钟
     
    RCC_ADCCLKConfig(RCC_PCLK2_Div6);   //设置ADC分频因子6,72M/6=12,ADC最大时间不能超过14M
 
    //PA0作为模拟通道输入引脚                      
    G_InitStructure.GPIO_Pin = GPIO_Pin_0;
    G_InitStructure.GPIO_Mode = GPIO_Mode_AIN; //模拟输入引脚
    GPIO_Init(GPIOA, &G_InitStructure);
   
    ADC_DeInit(ADC1);  //复位ADC1,将外设ADC1的全部寄存器重设为缺省值
   
    A_InitStructure.ADC_Mode = ADC_Mode_Independent; //ADC工作模式：ADC1和ADC2工作在独立模式
    A_InitStructure.ADC_ScanConvMode = DISABLE; //模数转换工作在单通道模式
    A_InitStructure.ADC_ContinuousConvMode = DISABLE; //模数转换工作在单次转换模式
    A_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None; //转换由软件而不是外部触发启动
    A_InitStructure.ADC_DataAlign = ADC_DataAlign_Right; //ADC数据右对齐
    A_InitStructure.ADC_NbrOfChannel = 1; //顺序进行规则转换的ADC通道的数目
    ADC_Init(ADC1, &A_InitStructure); //根据ADC_InitStruct中指定的参数初始化外设ADCx的寄存器
   
   
    ADC_Cmd(ADC1, ENABLE);         //使能指定的ADC1
    ADC_ResetCalibration(ADC1); //使能复位校准
    while(ADC_GetResetCalibrationStatus(ADC1)); //等待复位校准结束
    ADC_StartCalibration(ADC1);  //开启AD校准
    while(ADC_GetCalibrationStatus(ADC1));  //等待校准结束
    Gpio_InitStructure.GPIO_Pin = GPIO_Pin_1;	                 
    Gpio_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;//推挽输出 
    Gpio_InitStructure.GPIO_Speed=GPIO_Speed_50MHz;           
    GPIO_Init(GPIOB, &Gpio_InitStructure);
    GP2Y_High;
}
u16 Get_Adc(u8 ch) 
{
//设置制定adc的规则通道，设置他们的转化顺序和采样时间采样时间239.5周期
ADC_RegularChannelConfig(ADC1, ch, 1, ADC_SampleTime_239Cycles5 ); ///ADC1,ADC通道3规则采样顺序为1

ADC_SoftwareStartConvCmd(ADC1, ENABLE); //使能指定的adc1软件转换启动功能

while(!ADC_GetFlagStatus(ADC1, ADC_FLAG_EOC ));//等待转换结束

return ADC_GetConversionValue(ADC1); //返回最近一次adc1规则组的转换结果
}
float GetGP2Y(void)
{
    float pm;
    GP2Y_Low;
    Delay_us(280);
  //  AD_PM = IS_ADC_CHANNEL(ADC_Channel_0);	//PA0
	 AD_PM = Get_Adc(ADC_Channel_0)+40 ; 
    Delay_us(40);
    GP2Y_High;
    Delay_us(9680);
	return AD_PM;
}
