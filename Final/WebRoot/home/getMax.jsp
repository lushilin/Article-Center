<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'getMax.jsp' starting page</title>
    
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
  <h1 align="center">请输入相应的时间区间</h1>
  <h2 align="center">时间格式为：YYYY-MM-DD HH:MM:SS</h2>
    <br/>
    <br/>
    <form action="/Final/servlet/MaxServlet" method="post">
    <table align="center" border="0">
    <tr><td><div align="center">起始时间：</div></td><td><input type="text" name="from"/></td></tr>
    <tr><td><div align="center">结束时间：</div></td><td><input type="text" name="to"/></td></tr>
    <tr>
    <td><div align="center"><input type="submit"/></div></td>
    <td><div align="center"><input type="reset"/></div></td>
    </tr>
    </table>	
    </form>
  </body>
</html>
