from socket import *  

def BuildTCP32(data):
    HOST = '172.24.1.51'  
    PORT = 8090  
    BUFSIZ = 1024  
    ADDR = (HOST, PORT)   
    tcpClientSock = socket(AF_INET, SOCK_STREAM)  
    tcpClientSock.connect(ADDR)  
 
    tcpClientSock.send(bytes(data,'utf8'))
#    tcpClientSock.send(bytes(data,'utf8'))
#    tcpClientSock.send(bytes(data,'utf8'))
    print('send----ok--32---')
	
    data = tcpClientSock.recv(BUFSIZ)  
    tcpClientSock.close()
  #  print('接收到32发来的信息：',str(data.decode('utf8'))
    return data
#    tcpClientSock.close()
