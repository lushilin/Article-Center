<%@ page language="java" import="java.util.*,instance.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'viewpsge2.jsp' starting page</title>
    
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
    <table border="1" align="center">
    <tr>
	<td><div align="center">论文号</div></td>
	<td><div align="center">上传者</div></td>
	<td><div align="center">标题</div></td>
	<td><div align="center">关键词</div></td>
	<td><div align="center">描述</div></td>
	<td><div align="center">所需积分</div></td>
	<td><div align="center">评论</div></td>
	<td><div align="center">下载操作</div></td>
	<td><div align="center">查看评论</div></td>
	</tr>
	<%
		List<article> list = new ArrayList<article>();
		list = (List<article>)request.getAttribute("list");
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			article art=(article)list.get(i);
	 %>
		<tr>
		<td><%=art.getId()%></td>
		<td><%=art.getUsername()%></td>
		<td><%=art.getTitle()%></td>
		<td><%=art.getKey()%></td>
		<td><%=art.getDescription()%></td>
		<td><%=art.getPoint()%></td>
		<td><a href="/Final/home/pingjia.jsp?title=<%=art.getTitle()+".doc"%>"><button>评论</button></a></td>
		<td><a href="/Final/servlet/DownLoadServlet?title=<%=art.getTitle()+".doc"%>&point=<%=art.getPoint()%>&username=<%=art.getUsername()+""%>">
		<button>下载</button></a></td>
		<td><a href="/Final/servlet/LookServlet?title=<%=art.getTitle()+".doc"%>"><button>查看评论</button></a></td>
		</tr>
		<%} %>
    </table>
  </body>
</html>
