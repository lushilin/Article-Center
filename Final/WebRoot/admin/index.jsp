<%@ page language="java" import="java.util.*,instance.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String judge = (String)request.getSession().getAttribute("username");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
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
		alert("确认删除？");
	}
	</script>

  </head>
  <body>
  <h1>hello <%=judge %></h1>
  <a href="servlet/Logout">登出</a>
  <table border="1" align="center">
  <tr>
  <td>ID</td>
  <td>NAME</td>
  <td>PASSWORD</td>
  <td>POINT</td>
  <td>DELETE</td>
  <td>MODIFY</td>
  </tr>
  <%
  List<user> user_list = (ArrayList<user>) request.getAttribute("list");
  int id = 0;
  String username = null;
  String password = null;
  int point = 0;
  for(int i = 0;i < user_list.size();i++){
  id = user_list.get(i).getId();
  username = user_list.get(i).getUsername();
  password = user_list.get(i).getPassword();
  point = user_list.get(i).getPoint();
   %>
   <tr>
   <td><%=id %></td>
   <td><%=username %></td>
   <td><%=password %></td>
   <td><%=point %></td>
   <td><a href="/Final/servlet/Delete?id=<%=id%>"><button onclick="check()">删除</button></a></td>
   <td><a href="servlet/Modify?id=<%=id%>"><button>修改密码</button></a></td>
   </tr>
   <%} %>
  </table>
  </body>
</html>
