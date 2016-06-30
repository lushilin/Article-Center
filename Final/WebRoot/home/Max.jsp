<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Max.jsp' starting page</title>
    
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
  <%ArrayList<String> filename_Array = (ArrayList<String>)request.getAttribute("filename_Array");
  ArrayList<Integer> count_Array = (ArrayList<Integer>)request.getAttribute("count_Array");
  ArrayList<Integer> sum_Array = (ArrayList<Integer>)request.getAttribute("sum_Array");
  ArrayList<String> upuser_Array = (ArrayList<String>)request.getAttribute("upuser_Array");
  int max = count_Array.get(0);
  int max_count = 0;
  for(int i=1;i<count_Array.size();i++){
  	if(count_Array.get(i) > max){
  		max = count_Array.get(i);
  		max_count = i;
  	}
  }
  System.out.println(sum_Array.size());
  int max2 = sum_Array.get(0);
  int max_count2 = 0;
  for(int a=1;a<sum_Array.size();a++){
  	if(sum_Array.get(a) > max2){
  	max2 = sum_Array.get(a);
  	max_count2 = a;
  	}
  }
   %>
   <h1 align="center">当前时间段下载次数最多论文是<%=filename_Array.get(max_count)%></h1></br>
   <h1 align="center">当前时间段获得积分最多的作者是<%=upuser_Array.get(max_count2)%></h1>
  </body>
</html>
