<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<html>
<head>
</head>
<body>
<table border="0">
  <tr  align="center">
    <td  width="550" height="10"><p><font size="10px">�鿴�¶�</font></p></td>
  </tr>
  <tr>
   <td  height="330" align="center">

    <table border="0">
     <tr>
       <td colspan="3">
       <img src="image/hotwater.jpg" width="400" height="150"></td>
     </tr>
     <tr>
       <td align="center"><font size="6px">��ǰˮ��Ϊ��</font></td>
       <td align="right">
       <%=session.getAttribute("watertemp")+"��" %>
       </td>
       <td><a href="#">ˢ��</a></td>
     </tr>
     <tr>
       <td align="center"><font size="6px">����ˮ��Ϊ��</font></td>
       <td align="right">
       <input name="text" type="text" size="10" height="10px">��
       </td>
       <td><a href="ToolServlet?method=settemp">����</a></td>
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

