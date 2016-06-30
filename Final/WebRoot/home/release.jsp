<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>论文发布</title>
    
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
		      background-image: url(image/5.jpg);    
		      background-repeat: repeat-x;    
		 } 
		 </style>
	<!-- 
	<script type="text/javascript">
		function upload1(){
			var upform1 = document.getElementById("upform1");
			upform1.submit();
		}
		function upload2(){
			var upform2 = document.getElementById("upform2");
			upform2.submit();
		}
	</script>
	 -->
	
  </head>
  
  <body>
  <h1 align="center">论文发布</h1>
  <h2 align="center">论文格式仅限pdf,doc,txt</h2>
    <form id="upform2" action="servlet/UploadServlet" method="post" id="upform1" action="servlet/UploadServlet" enctype="multipart/form-data">
  <table align="center" border="0">
  <tr>
	<td><div align="right">论文题目</div></td>
	<td><input type="text" name="title"/>
	</tr>
	<tr>
	<td><div align="right">关键词</div></td>
	<td><input type="text" name="keyword"/></td>
	</tr>
	<tr>
	<td><div align="right">论文描述</div></td>
	<td><input type="text" name="description"/></td>
	</tr>
	<tr>
	<td><div align="right">积分</div></td>
	<td><input type="text" name="point"/></td>
	</tr>
  	<tr>
	<td><div align="right">上传文件</div></td>
	<td><input type="file" name="file" size="30"/></td>
	</tr>
	<tr>
	<td></td>
	<td><input type="submit" value="上传"/></td>
	</tr>
  </table>
  </form>
  <!--  
    <table border="0" align="center">
  <tr>
  <td><button onclick="upload1()">确定</button></td>
  <td><button onclick="upload2()">上传</button></td>
  </tr>
  </table>
  -->

  </body>
</html>
