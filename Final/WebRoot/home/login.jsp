<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    		background-image: url(image/4.jpg);    
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
        form.register {
        	width: 150px; 
        	height: 30px;
        }
    </style>
    <!-- 引入js框架 -->
    <script src="js/jquery.js"/>
    <!-- 测试js框架 -->
      <script type="text/javascript">
   function testjquery()
   {
    var user_name=$("#test").attr("value");
    alert(user_name);
    }
  </script>
    <script type="text/javascript">
	    function change(){
	           var imgcode = document.getElementById("codeimg");
	           imgcode.src="servlet/validatecode?"+Math.random();
	       }
    </script>
    
  </head>  
  
  <body>
    <h1 align="center">欢迎用户登录</h1>
    <input id="test" value="jquery"/><br/>
    <form action="servlet/user_login" method="post">
    	用户名：<input id="username" type="text" name="username" onblur="usernameCheck()"><br>
    	密　码：<input type="password" name="password"><br>
    	验证码：<input type="text" name="code">
    	<img id="codeimg" src="servlet/validatecode" onclick="change()"><br>
    	<input type="submit" value="登录">
    	<input type="button" value="注册" class="register" onclick="window.location.href='home/register.jsp';">
    	<input type="button" value="测试" onclick="testjquery()"/>
    </form>
  </body>
</html>
</i>