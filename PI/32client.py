#-*- coding: utf-8 -*-
#树莓派处理报警信息

from scoket import *

def SoftClient(data):
   
    HOST = '192.168.1.103'
    PORT = 8088
    BUFSIZ = 1024
    ADDR = (HOST, PORT)
    tcpClientSock = socket(AF_INET, SOCK_STREAM)
    tcpClientSock.connect(ADDR)
    tcpClientSock.send(bytes(data,'utf8'))
    print('send----ok-----')
    tcpClientSock.close()
