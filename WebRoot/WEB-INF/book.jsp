<%@ page language="java" import="java.util.*,com.bbc.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>品雅书屋</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="书屋 品雅  购物" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/comm.css">
  </head>
  
  <body>
  
  <% User user=  (User)request.getSession().getAttribute("user"); %>
  <% ArrayList<Book> books=(ArrayList<Book>) request.getAttribute("books"); %>
  <% Integer pageCount=(Integer) request.getAttribute("pageCount"); %>
  <% Integer pageNow =(Integer) request.getAttribute("pageNow"); %>
  <div id="top">
			<img src="img/banner.gif" width="980px"/>
			<ul id="nav"> 
    			<li><a href="#">首页</a></li> 
    			<li><a href="#">新品上架</a></li> 
    			<li><a href="#">特价商品</a></li> 
    			<li><a href="#">销售排行</a></li> 
    			<li><a href="/buybook/BookServlet?type=showcart">购物车</a></li> 
    			<li><a href="#">联系我们</a></li>
    			<li><a href="/buybook/SafeExitServlet">安全退出</a></li>     		
			</ul> 
	</div>
	<div id="searchstyle">
		<input id="searchtextstyle" type="text" name="search"/>
		<input id="submitstyle" type="submit" value="搜索"/>
	</div>
	
	<div id="mainbody">

		<div id="left">
			<img src="img/booklogo.png" width="200px" height="50px">
			
			<% if(user!=null){ %>
			   <br/>
			   <center> <h3><font color="blue"> 欢迎会员<%= user.getUsername()%>回来 ...</font></h3></center>
			  
			<%}else {%>
			<form action="/buybook/LoginServlet" method="post">
			<table border="1px" bordercolor="gray" cellspacing="0px">
				<tr><td>会员名:</td><td><input type="text"   name="username" /></td></tr>
				<tr><td>密　码:</td><td><input type="password" name="password"/></td></tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="登录"/>
						<input type="reset"  value="重输"/>
					</td>
				</tr>
			</table>
			</form>
			<% }%>
			<img src="img/gonggao.png" width="200px" height="50px">
			<marquee direction="up" width="170" height="80" onmouseout="this.start()" onmouseover="this.stop()" scrollAmount="2" scrollDelay="1">
			新店开张...<br/><br/>
			欢迎大家的光临...
			</marquee>
			<img src="img/paihang.png" width="200px" height="50px">

		</div>
		<div id="main">
		<table>
			<tr>
				<td>
				<% for(int i=0;i<books.size();i++){ 
				 Book book= books.get(i);
				%>
					<dl>
					<dd><img src="./img/<%=book.getImg()%>"></dd>
					<dd>作者:<%=book.getAuthor() %></dd>
					<dd>定价:<%=book.getPrice() %></dd>
					<dd><a href="/buybook/BookServlet?type=buy&id=<%=book.getBookid() %>&pageNow=<%=pageNow %>">购买</a></dd>
					</dl>
				<%} %>	
				</td>
			</tr>
			<tr>
				<td>
				
				<center>
				<%for(int i=1;i<=pageCount;i++){ %>
				
				<a href="/buybook/PageServlet?pageNow=<%=i%>">[<%=i %>]</a>
				
				
				<%} %>
				</center>
				</td>
			</tr>
		</table>
		
		</div>	

	</div>
	<div id="footer">
	<hr>
		<center>
		<img src="img/footer.png"><br/>		
		品雅书屋，我们致力于打造一个优秀的图书体验购买平台！<br/>
		<p>Copyright  &copy 2017 
		</center>
	</div>
  </body>
</html>
