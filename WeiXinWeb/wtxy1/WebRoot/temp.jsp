<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<html>
<head>
</head>
<body>
<table >
  <tr  align="center">
    <td  width="550" height="10"><p><font size="10px">�鿴��ʪ��</font></p></td>
  </tr>
  <tr>
   <td  height="330" align="center">

    <table >
     <tr>
       <td rowspan="4">
       <img src="image/wd.jpg" width="100" height="200"></td>
       <td colspan="2"> ��ǰ�¶�Ϊ��</td>
     </tr>
     <tr>
       <td><font size="10px">
       <%=session.getAttribute("temp")+"��" %>
       </font>
       </td>
       <td align="center">
        <a href="ToolServlet?method=temp">
        <img src="image/sx.jpg" width="60" height="50"></a>
       </td>
     </tr>
     <tr>
     <td colspan="2">��ǰʪ��Ϊ��</td>
     </tr>
     <tr>
       <td><font size="10px">
       <%=session.getAttribute("humidity")+"%" %>
       </font>
       </td>
       <td align="center">
        <a href="ToolServlet?method=humidity">
        <img src="image/sx.jpg" width="60" height="50"></a>
       </td>
     </tr>
    </table>

   </td>
  </tr>
  <tr>
   <td align="center">&nbsp;All CopyRights reserved 2016 by �Ӻ���ѧ����ѧԺ  ����С��</td>
  </tr>
</table>
</body>
</html>

