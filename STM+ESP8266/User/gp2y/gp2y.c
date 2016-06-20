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
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA |RCC_APB2Periph_ADC1 , ENABLE );   //ʹ��ADC2ͨ��ʱ��
     
    RCC_ADCCLKConfig(RCC_PCLK2_Div6);   //����ADC��Ƶ����6,72M/6=12,ADC���ʱ�䲻�ܳ���14M
 
    //PA0��Ϊģ��ͨ����������                      
    G_InitStructure.GPIO_Pin = GPIO_Pin_0;
    G_InitStructure.GPIO_Mode = GPIO_Mode_AIN; //ģ����������
    GPIO_Init(GPIOA, &G_InitStructure);
   
    ADC_DeInit(ADC1);  //��λADC1,������ADC1��ȫ���Ĵ�������Ϊȱʡֵ
   
    A_InitStructure.ADC_Mode = ADC_Mode_Independent; //ADC����ģʽ��ADC1��ADC2�����ڶ���ģʽ
    A_InitStructure.ADC_ScanConvMode = DISABLE; //ģ��ת�������ڵ�ͨ��ģʽ
    A_InitStructure.ADC_ContinuousConvMode = DISABLE; //ģ��ת�������ڵ���ת��ģʽ
    A_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None; //ת��������������ⲿ��������
    A_InitStructure.ADC_DataAlign = ADC_DataAlign_Right; //ADC�����Ҷ���
    A_InitStructure.ADC_NbrOfChannel = 1; //˳����й���ת����ADCͨ������Ŀ
    ADC_Init(ADC1, &A_InitStructure); //����ADC_InitStruct��ָ���Ĳ�����ʼ������ADCx�ļĴ���
   
   
    ADC_Cmd(ADC1, ENABLE);         //ʹ��ָ����ADC1
    ADC_ResetCalibration(ADC1); //ʹ�ܸ�λУ׼
    while(ADC_GetResetCalibrationStatus(ADC1)); //�ȴ���λУ׼����
    ADC_StartCalibration(ADC1);  //����ADУ׼
    while(ADC_GetCalibrationStatus(ADC1));  //�ȴ�У׼����
    Gpio_InitStructure.GPIO_Pin = GPIO_Pin_1;	                 
    Gpio_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;//������� 
    Gpio_InitStructure.GPIO_Speed=GPIO_Speed_50MHz;           
    GPIO_Init(GPIOB, &Gpio_InitStructure);
    GP2Y_High;
}
u16 Get_Adc(u8 ch) 
{
//�����ƶ�adc�Ĺ���ͨ�����������ǵ�ת��˳��Ͳ���ʱ�����ʱ��239.5����
ADC_RegularChannelConfig(ADC1, ch, 1, ADC_SampleTime_239Cycles5 ); ///ADC1,ADCͨ��3�������˳��Ϊ1

ADC_SoftwareStartConvCmd(ADC1, ENABLE); //ʹ��ָ����adc1���ת����������

while(!ADC_GetFlagStatus(ADC1, ADC_FLAG_EOC ));//�ȴ�ת������

return ADC_GetConversionValue(ADC1); //�������һ��adc1�������ת�����
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
