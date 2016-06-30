<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>评价</title>
    
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
            background-color: antiquewhite;
            width:300px;
            margin:0 auto;
        }
        form input {
            width:120px;
            height:30px;
            margin-top:3px;
        }
    </style>

  </head>
  
  <body>
  <%
  	String x = request.getParameter("title");
  	 String filename = new String(x.getBytes("ISO-8859-1"), "utf-8");
  	String username = (String)request.getSession().getAttribute("user");
   %>
   <h1>对论文<%=filename%>进行评价</h1>
    <form action="/Final/servlet/PingJiaServlet" method="post">
    	论文名：<input type="text" name="filename" value=<%=filename%> /></br>
    	评　论：<textarea name="content" cols="30" rows="6"></textarea><br/>
    	</br>
    	</br>
    	</br>
    	<input type="submit" value="评论"/>
    	<input type="reset" value="清空"/>
    </form>
  </body>
</html>
