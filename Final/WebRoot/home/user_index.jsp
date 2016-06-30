<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%request.setAttribute("from", "user_index"); %>
<%
String judge = (String)request.getSession().getAttribute("user");
if(judge == null){
	request.getRequestDispatcher("servlet/user_login").forward(request, response);
}
int point = (Integer)request.getSession().getAttribute("user_point");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_index.jsp' starting page</title>
	<style type="text/css">
		 body{    
		      background-image: url(image/2.jpg);    
		      background-repeat: repeat-x;    
		 }  
		 #bt1,#bt2,#bt3{ 
				font-family:微软雅黑 ;    <!-- 字体  -->
				width: 60px ;             <!-- 宽度  -->
				height:30px ;             <!-- 高度 -->
				font-size:14px;           <!-- 字体大小-->
				color:gray;               <!--字体颜色  -->
				border: 1px solid red； <!-- 边框；边框宽度、单线、边框颜色 -->
				margin-left: 10px;            <!-- 左边距，相应的还有右边距margin-right,                                      上margin-top,下 margin-buttom -->
				background-color:#F1F1F1;        <!--背景色；十六位颜色表示时前加#符号  
				                                  transparent是透明-->
				box-shadow:10px 10px 10px gray;  <!-- 阴影；x轴偏移（右偏为正），y轴偏移（向下                                为正），模糊度，模糊颜色  -->
				border-radius:10px 10px 10px 10px;<!-- 圆角；左上，右上，右下，左下-->
				cursor:pointer;                  <!-- 鼠标经过时鼠标变成小手  -->
		} 
  	</style>
  	<script type="text/javascript">
  	function release(){
  		window.location.href = "home/release.jsp";
  	}
  	function look(){
  		window.location.href = "home/viewpage.jsp";
  	}
  	function search(){
  		window.location.href = "home/search_reason.jsp";
  	}
  	function match(){
  		window.location.href = "home/getMax.jsp";
  	}
  	</script>
  </head>

  
  <body>
    <h1>欢迎<%=judge %></h1>
    <!--<h2>当前积分:<%=point %></h2> -->
    </br>
    <h3>搜索论文</h3>
    <form action="/Final/servlet/Search" name="selectform" method="post">
	<table border="0">
		<tr>
		<td><div align="right">关键词：</div></td>
		<td><input type="text" name="keyword"/></td>
		<td><div align="right">标题：</div></td>
		<td><input type="text" name="title"/></td>
		<td></td>
		<td><button>搜索</button></td>
		</tr>
	</table>
</form> 
</br>
    <button id="bt1" onclick="release()">发布论文</button>
    <button id="bt2" onclick="look()">查看所有论文</button>
    <button id="bt3" onclick="search()">搜索论文</button>
    <button id="bt4" onclick="match()">查看积分与下载量</button>
  </body>
</html>
