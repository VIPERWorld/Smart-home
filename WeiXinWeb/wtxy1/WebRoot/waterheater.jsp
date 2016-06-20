<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<html>
<head>
</head>
<body>
<table border="0">
  <tr  align="center">
    <td  width="550" height="10"><p><font size="10px">查看温度</font></p></td>
  </tr>
  <tr>
   <td  height="330" align="center">

    <table border="0">
     <tr>
       <td colspan="3">
       <img src="image/hotwater.jpg" width="400" height="150"></td>
     </tr>
     <tr>
       <td align="center"><font size="6px">当前水温为：</font></td>
       <td align="right">
       <%=session.getAttribute("watertemp")+"℃" %>
       </td>
       <td><a href="#">刷新</a></td>
     </tr>
     <tr>
       <td align="center"><font size="6px">设置水温为：</font></td>
       <td align="right">
       <input name="text" type="text" size="10" height="10px">℃
       </td>
       <td><a href="ToolServlet?method=settemp">设置</a></td>
     </tr>
    </table>

   </td>
  </tr>
  <tr>
   <td align="center">&nbsp;All CopyRights reserved 2016 by 河海大学文天学院  软设小组</td>
  </tr>
</table>
</body>
</html>

