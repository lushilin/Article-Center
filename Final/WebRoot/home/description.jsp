<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'description.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%String filename = (String)request.getAttribute("filename"); %>
  <body>
  <h1 align="center">对论文：<%=filename%>的评论</h1>
    <table align="center" border="1">
    <tr>
    <td>用户</td>
    <td>评论</td>
    <td>评论时间</td>
    </tr>
    <%
    String p="";
    String q="";
    String time="";
    List<String> user_list = (List<String>)request.getAttribute("list_user");
    List<String> description_list = (List<String>)request.getAttribute("list_description");
    List<String> time_list = (List<String>)request.getAttribute("list_time");
    for(int i=0;i<description_list.size();i++){
    p = user_list.get(i);
    q = description_list.get(i);
    time = time_list.get(i);
     %>
     <tr>
     <td><%=p%></td>
     <td><%=q%></td>
     <td><%=time%></td>
     </tr>
     <%} %>
    </table>
  </body>
</html>
