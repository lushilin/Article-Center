<%@ page contentType="text/html; charset=utf-8" import="java.util.*,instance.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
List<article> list = (ArrayList<article>) request.getAttribute("list");
article article = new article();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'search_reason.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	 body {
	 	background-image: url(image/7.jpg);    
			      background-repeat: repeat-x;   
	 }
	 form {
		//background-color: antiquewhite;
	    width:600px;
	    margin:0 auto;
	}
	</style>
  </head>
  <body>
<table border="1" align="center">
	<tr>
	<td><div align="center">论文号</div></td>
	<td><div align="center">标题</div></td>
	<td><div align="center">关键词</div></td>
	<td><div align="center">描述</div></td>
	<td><div align="center">所需积分</div></td>
	<td><div align="center">评论</div></td>
	<td><div align="center">下载操作</div></td>
	</tr>
	<%
		for(int i = 0;i<list.size();i++){
			article = (article)list.get(i);
	 %>
	<tr>
	<td><%=article.getId()%></td>
	<td><%=article.getTitle()%></td>
	<td><%=article.getKey()%></td>
	<td><%=article.getDescription()%></td>
	<td><%=article.getPoint()%></td>
	<td><a href=""><button>评论</button></a></td>
	<td><a href=""><button>下载</button></a></td>
	</tr>
	<%} %>
</table>
  </body>
</html>
