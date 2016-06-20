
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'register.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body >
<form name="form1" action="UserServlet.do?method=register" method="post">
<table width="200" border="0" align="center">
	<tr align="center">
		<td colspan="2">注册窗口</td>
	<tr align="center">
		<td>用户名</td>
		<td><input type="text" name="username" size="10"></td>
	</tr>
	<tr align="center">
		<td>密码</td>
		<td><input type="password" name="password1" size="10"></td>
	</tr>
	<tr align="center">
		<td>确认密码</td>
		<td><input type="password" name="password2" size="10"></td>
	</tr>
	<tr align="center">
		<td>Email</td>
		<td><input type="text" name="email" size="10"></td>
	</tr>
	<tr align="center">
		<td colspan="2"><input type="submit" name="submit" value="登录"> <a
			href="login.jsp">返回</a></td>
	</tr>
</table>
</form>

</body>
</html>
