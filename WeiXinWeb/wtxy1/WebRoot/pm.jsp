<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<html>
<head>
</head>
<body>
<table >
  <tr  align="center">
    <td  width="550" height="10"><p><font size="10px">PM2.5�鿴</font></p></td>
  </tr>
  <tr>
   <td  height="330" align="center">

    <table>
     <tr align="center">
       <td rowspan="3">
       <img src="image/pm.jpg" width="150" height="200"></td>
       <td colspan="2" align="center"> ��ǰPM2.5ֵΪ��<td>
     </tr>
     <tr align="center">
       <td ><font size="10px">
       <%=session.getAttribute("pm") %>
       </font>
       </td>
       <td>��m/m3</td>
     </tr>
     <tr align="center">
       <td colspan="2">
        <a href="ToolServlet?method=pm">
        <img src="image/sx.jpg" width="60" height="50"></a>
       </td>
    </table>

   </td>
  </tr>
  <tr>
   <td align="center">&nbsp;All CopyRights reserved 2016 by �Ӻ���ѧ����ѧԺ  ����С��</td>
  </tr>
</table>
</body>
</html>

