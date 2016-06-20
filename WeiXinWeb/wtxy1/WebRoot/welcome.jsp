<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<frameset cols="250,*">
  <frame src="black.jsp" name="left.frame" frameborder="0" noresize="noresize" />
<frameset cols="*,200">
<frameset rows="165,*">
<frame src="top.jsp" name="topframe" noresize="noresize" frameborder="0" scrolling="no">
<frameset cols="180,*">
  <frame src="menu.jsp" name="menuframe" noresize="noresize" frameborder="0" scrolling="no"/>
  <frame src="show.jsp" name="showframe" frameborder="0" noresize="noresize" />
</frameset>
</frameset>
  <frame src="black.jsp" name="right.frame" frameborder="0" noresize="noresize" />
</frameset>
</frameset>

<base href="<%=basePath%>">

<title>welcome.jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
退出
</body>
</html>
