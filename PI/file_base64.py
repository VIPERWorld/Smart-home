# coding=utf-8 
import base64
import os

#将图片转为base64数据
def imgstr(): 
    val = os.system( "raspistill -o image.jpg -w 800 -h 640 -t 1000 -v")
    oldimg = open("image.jpg","rb")
    str = base64.b64encode(oldimg.read())
    return str
#将声音转为base64数据
def musicstr():
    oldmusic = open("test.mp3","rb")
    str = base64.b64encode(oldmusic.read())
    return str
#实况录像
def videostr():
    val = os.system("raspivid -o wtxy.h264 -t 5000 -w 848 -h 480 -v")
    val1 = os.system("sudo MP4Box -fps 30 -add wtxy.h264 wtxy.mp4")
    oldvideo = open("wtxy.mp4","rb")
    str = base64.b64encode(oldvideo.read())
    return str
