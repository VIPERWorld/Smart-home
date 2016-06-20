<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
</head>
<title>login.jsp</title>
<body bgcolor="gray">
<center>
<form name="form1" action="UserServlet.do?method=a" method="post">
<table>
<tr height="150">
</tr>
<tr><td>
<table border="0" align="center" >
    <tr>
       <td colspan="2" align="center">
        <img src="image/4.jpg" width="250" height="200" align="left" />
       </td>
    </tr>
    <tr>
       <td colspan="2" align="center">
        <font size="5" >用户名：</font>
        <input type="text" name="username" size="10">
    </tr>
    <tr> 
       <td colspan="2" align="center"> 
        <font size="5" >密&nbsp;&nbsp;码：</font> 
        <input type="password" name="password" size="10">
       </td>
    </tr>
    <tr>
      <td align="right">
         <input type="submit" name="submit" value="登录">
      </td>
      <td align="center">
         <a href="register.jsp"><font color="red">注册新用户</font></a>
      </td>
    </tr>
</table>
</td>
</tr>

</table>
</form>
</center>
</body>
</html>
