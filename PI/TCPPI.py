#-*- coding: utf-8 -*-
#接收服务器信息
from socket import *
import JudgeInfo

HOST=''
PORT=8066  #设置侦听端口
BUFSIZ=1024
ADDR=(HOST, PORT)
sock=socket(AF_INET, SOCK_STREAM)
sock.bind(ADDR)
sock.listen(5)

#设置退出条件
while(1):
	print('等待接入，侦听端口:%d' % (PORT))
	tcpClientSock,addr=sock.accept()
	print('接受连接，客户端地址：',addr)
	data=tcpClientSock.recv(BUFSIZ)
	print(data.decode('utf8'))
	data1 = JudgeInfo.judgeinfo(data.decode('utf8'))
#	print(data1)
	tcpClientSock.send(data1)
	tcpClientSock.close()


