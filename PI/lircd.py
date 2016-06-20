import os

def openlircd():
	val = os.system("sudo irsend SEND_ONCE wtxy KEY_OPEN")
	return 'air-condition is open'
def stoplircd():
	val1 = os.system("sudo irsend SEND_ONCE wtxy KEY_OPEN")
	return 'air-condition is stop'
def settemp():
	val = os.system("sudo irsend SEND_ONCE wtxy KEY_UP")
	return 'temp set success'
#val = os.system("sudo irsend SEND_ONCE wtxy KEY_UP")
#val = os.system("sudo irsend SEND_ONCE wtxy KEY_DOWN")
