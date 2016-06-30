<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
		body{    
			background-image: url(image/6.jpg);    
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
    <script type="text/javascript">
    function change(){
    	var imgcode = document.getElementById("codeimg");
	           imgcode.src="servlet/validatecode?"+Math.random();
    }
    </script>
  </head>
  
  <body>
    <h1 align="center">欢迎注册</h1>
    <form action="servlet/Register" method="post" name="myform">
    	用 户 名：  <input type="text" name="username" id="username"/><br/>
    	密　　码：<input type="password" name="password" id="password"/><br/>
    	确认密码：<input type="password" name="repassword" id="repassword"/><br/>
    	验 证 码： <input type="text" name="code" id="code"/>
    	<img id="codeimg" src="servlet/validatecode" onclick="change()"/><br/>
    	<input type="submit" value="提交" onclick=""/> 
    </form>
  </body>
</html>
