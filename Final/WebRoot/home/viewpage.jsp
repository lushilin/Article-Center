<%@ page contentType="text/html; charset=utf-8" import="java.util.*,instance.*" %>
<%! %>
<jsp:useBean id="page2" scope="request" class="home.PageBean"/>
<%String check = (String)request.getAttribute("check"); %>
<script language="JavaScript" type="text/JavaScript">
function Jumping(){
  document.PageForm.submit();
  return ;
}

function gotoPage(pagenum){
  document.PageForm.jumpPage.value = pagenum;
  document.PageForm.submit();
  return ;
}

function Return(){
	window.location.href = "/Final/home/user_index.jsp";
}

</script>
<style type="text/css">
 body {
 	background-image: url(image/7.jpg);    
		      background-repeat: repeat-x;   
 }
 form {
	//background-color: antiquewhite;
    width:600px;
    margin:0 auto;
}
</style>
<html>
<head>
<title>
分页功能
</title>
</head>
<body>
<h1 align="center">可下载论文</h1>
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
String s=String.valueOf(page2.getCurPage());
try{
ArrayList list=page2.getResult(s).data;
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
<td><a href="/Final/home/pingjia.jsp?title=<%=art.getTitle()%>"><button>评论</button></a></td>
<td><a href="/Final/servlet/DownLoadServlet?title=<%=art.getTitle()%>&point=<%=art.getPoint()%>&username=<%=art.getUsername()+""%>">
<button>下载</button></a></td>
<td><a href="/Final/servlet/LookServlet?title=<%=art.getTitle()%>"><button>查看评论</button></a></td>
</tr>
<%
 }
}catch(Exception e){}
%>
</table>
<%if(page2.getMaxPage()!=1){ %>
<form action="/Final/servlet/PageServlet" name="PageForm" method="post">
每页
<%=page2.rowsPerPage%>
行
共
<%=page2.getMaxRowCount()%>行

第
<%=page2.getCurPage()%>
页
共
<%=page2.getMaxPage()%>页
<br/>
<%
  if (page2.getCurPage() == 1) {
    out.print(" 首页 上一页");
  }
  else {
%>
<a HREF="javascript:gotoPage(1)">首页</a>
<a HREF="javascript:gotoPage(<%=page2.getCurPage()-1%>)">上一页</a>
<%}%>
<%
  if (page2.getCurPage() == page2.getMaxPage()) {
    out.print("下一页 尾页");
  }
  else {
%>
<a HREF="javascript:gotoPage(<%=page2.getCurPage()+1%>)">下一页</A>
<a HREF="javascript:gotoPage(<%=page2.getMaxPage()%>)">尾页</A>
<%}%>
转到第
<select name="jumpPage" onchange="Jumping()">
<%
  for (int i = 1; i <= page2.getMaxPage(); i++) {
    if (i == page2.getCurPage()) {
%>
  <option selected value="<%=i%>"><%=i%> </option>
<%} else {%>
  <option value="<%=i%>"><%=i%> </option>
<%}
  }%>
</select>
页
</form>
<%}%>
<div><button onclick="Return()">返回</button></div>
</body>
</html>