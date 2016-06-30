<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login_Jsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <style type="text/css">
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
  <h1 align="center">欢迎管理员登录</h1>
      <form action="servlet/Login" method="post" name="myForm" >
        用户名: <input type="text" name="username"/><br/></br>
        密　码: <input type="password" name="password"/><br/>
        </br></br>
        <input type="submit" value="登录"/>
    </form>
  </body>
</html>
