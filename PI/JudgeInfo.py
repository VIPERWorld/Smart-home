# coding=utf-8
#判断接收消息类型
import TCP32
import file_base64
import lircd

def judgeinfo(data):
     if data=="10":
       str = file_base64.imgstr()
       return str
     elif data=="11":
       str = file_base64.musicstr()
       return str
     elif data=="20":
       str = TCP32.BuildTCP32(data)
       return (str.encode('utf8'))
     elif (data=='30'):
       str = TCP32.BuildTCP32(data)
       return (str.encode('utf8'))
     elif data=="40":
       str = lircd.openlircd()
       print(str)
       return (str.encode('utf8'))
     elif data=="50":
       str = lircd.stoplircd()
       print(str)
       return (str.encode('utf8'))
     elif (data=='A'):
       str = TCP32.BuildTCP32(data)
       return str
     elif (data=='B'):
       str = TCP32.BuildTCP32(data)
       return str
     elif (data=='PM'):
       str = TCP32.BuildTCP32(data)
       return str
     elif data=="60":
       str = 'door is open'
       print (str)
       return (str.encode('utf8'))
     elif data=="70":
       str = file_base64.videostr()
       print ('ok')
       return str
