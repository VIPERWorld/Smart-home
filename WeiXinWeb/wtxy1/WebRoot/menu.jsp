   <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<table width="100%" align="center">
			<tr>
				<td bgcolor="gray" align="center">
				<font size="5px" >环境监控</font>
				</td>
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="ToolServlet?method=temp" target="showframe">查看温度</a></font>
				</td>			
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="ToolServlet?method=image" target="showframe">实况截图</a></font>
				</td>
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="ToolServlet?method=pm" target="showframe">PM2.5查看</a></font>
				</td>			
			</tr>
			<tr>
				<td bgcolor="gray" align="center">
				<font size="5px" >家电调节</font>
				</td>			
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="lightoff.jsp" target="showframe">灯控</a></font>
				</td>
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="airconditioner0.jsp" target="showframe">空调</a></font>
				</td>			
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="ToolServlet?method=waterheater" target="showframe">热水器</a></font>
				</td>			
			</tr>
			<tr>
				<td bgcolor="gray" align="center">
				<font size="5px" >楼宇系统</font>
				</td>
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="ToolServlet?method=dialogue" target="showframe">对话</a></font>
				</td>			
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="ToolServlet?method=kaimen" target="showframe">开门</a></font>
				</td>
			</tr>
			<tr>
				<td align="center">
				<font size="5px" ><a href="ToolServlet?method=louyu" target="showframe">楼宇查看</a></font>
				</td>			
			</tr>
			<tr>
				<td bgcolor="gray" align="center">
				<font size="5px" color="#000000"><a href="javascript:top.location='login.jsp'">退出</a></font>
				</td>
			</tr>
		</table>
</body>
</html>
