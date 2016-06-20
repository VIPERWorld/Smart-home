#-*- coding: utf-8 -*-
#接收32报警信息

from socket import *
#import 32client

HOST=''
PORT=8091 #设置侦听端口
BUFSIZ=1024
ADDR=(HOST,PORT)
sock=socket(AF_INET, SOCK_STREAM)
sock.bind(ADDR)
sock.listen(5)


while(1):
	print('等待接入，侦听端口:%d' % (PORT))
	tcpClientSock,addr=sock.accept()
	print('接受连接，客户端地址：',addr)
	data=tcpClientSock.recv(BUFSIZ)
	print(data)
#	32client(data)
	#tcpClientSock.send(data)
	tcpClientSock.close()
