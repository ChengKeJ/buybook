<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.bbc.model.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showdetail.jsp' starting page</title>
    
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
  <%  User user =(User)request.getSession().getAttribute("user"); %>
  <center> 你好，欢迎 <%=user.getUsername() %> <br></center>
  <% Book book=  (Book)request.getAttribute("book"); %>
  	<table align="center" border="1" bordercolor="green" cellspacing="0">
  	  <tr><td>图书名称：</td><td><%=book.getBookname() %></td></tr>
  	  <tr><td>图书封面</td><td><img src="images/<%=book.getImg()%>" alt="图书封面"  height="300px" width="270px"/></td></tr>
  	  <tr><td>出版社名：</td><td><%=book.getPublishHouse() %></td></tr>
  	  <tr><td>图书作者：</td><td><%=book.getAuthor() %></td></tr>
  	  <tr><td>图书价格：</td><td><%=book.getPrice() %></td></tr>
  	  <tr><td>图书库存：</td><td><%=book.getBuynums() %></td></tr>
  	  <tr><td>购买图书</td><td><a href="#">购买图书</a></td></tr> 	
  	
  	</table>
  	<br/>
  	<br/>
  	<center><a href="#">返回购物页面</a>   <a href="#">查看购物车</a></center>
  
  
  
  
  
  
  </body>
</html>
