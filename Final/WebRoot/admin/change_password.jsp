<%@ page language="java" import="java.util.*,instance.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'change_password.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		function check(){
			alert("确认修改？");
		}
	</script>
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
  <%user user = (user)request.getAttribute("user");
  String username = user.getUsername();
  String password = user.getPassword();
  int point = user.getPoint();
   %>
  <body>
	<h1 align="center">修改密码</h1>
	<form action="servlet/Modify2?id=<%=user.getId() %>" method="post" name="myForm" >
        用户名: <input type="text" name="username" value="<%=username%>" disabled="true"/><br/>
        密　码: <input type="password" name="password" value=<%=password%>/><br/>
        积　分: <input type="text" name="point" value=<%=point %> disabled="true"/><br/>
        <input type="submit" value="确认修改" onclick="check()"/>
    </form>
  </body>
</html>
