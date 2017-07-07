<%@ page language="java" import="java.util.*" import ="com.bbc.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'showcart.jsp' starting page</title>    
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
  <%  User user= (User)request.getSession().getAttribute("user");  %>
      <center>欢迎<%=user.getUsername()%>        <a href="/buybook/BookServlet?type=clear">清空购物车</a></center>
      <br/> <br/> <br/>
      <% ArrayList<Book> books= (ArrayList<Book>)request.getAttribute("books"); %>
      <% float price =(Float)request.getAttribute("price"); %>
      <form action="/buybook/BookServlet?type=update" method="post">
      <table align="center" cellspacing="0" border="1" bordercolor="green">
      <tr><th>编号</th><th>图书名称</th><th>图书价格</th><th>购买数量</th><th>编辑</th><th>删除</th></tr>
      <% for(int i=0;i<books.size();i++){
      	 Book book= books.get(i);
       %>
      		<tr>
      			<td><%=book.getBookid() %><input type="hidden" value="<%=book.getBookid()%>" name="id"/></td>
      			<td><%=book.getBookname() %></td>
      			<td><%=book.getPrice() %></td>
      			<td><input type="text" value="<%=book.getBuynums() %>" name="buynums"/></td>
      			<td><input type="checkbox" name="sel"/></td>
      			<td><a href="/buybook/BookServlet?type=delete&id=<%=book.getBookid()%>">删除</a></td>
      		</tr>
      		<%} %>
      		<tr align="center">
      			<td colspan="6">
      			您的购物车物品总价格：<%=price %>元                        <input type="submit" value="更新购物车"/>
      			</td>
      		</tr>      
      </table>      
      <center>
      	<a href="/buybook/PageServlet?pageNow=1">返回购物</a>     <a href="/buybook/OrderServlet">结算</a>
      </center>      
      </form>
  </body>
</html>
