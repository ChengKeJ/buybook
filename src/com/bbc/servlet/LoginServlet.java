package com.bbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbc.model.Book;
import com.bbc.model.User;
import com.bbc.service.BookService;
import com.bbc.service.Cart;
import com.bbc.service.UserService;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//获取book.jsp传递过来的用户名和密码	
		String username=  request.getParameter("username");
		String password= request.getParameter("password");
		//如果是正确的用户名和密码，页面就要跳转
		UserService userService=new UserService();
		User user=new User();
		user= userService.getUserByNameAndPSW(username, password);
		
		//分页
		int pageNow=1;//当前页
		int pageCount=1;//一共可以分多少页，是系统根据数据计算的
		int pageSize=5;//每一页，显示数据的行数
		int rowCount=1;//数据库中一共有多少行
		
		
		boolean b= userService.checkUser(user);
		if(b){
						
			/*BookService bookService=new BookService();
			
			pageCount= bookService.getPageCount(pageSize);
			request.setAttribute("pageCount", pageCount);
			
			Cart cart=new Cart();
			request.getSession().setAttribute("cart", cart);
			ArrayList<Book> books= bookService.getBooksBySelected(pageNow, pageSize);
			request.setAttribute("books", books);
			request.setAttribute("pageNow", pageNow);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
			*/
			Cart cart=new Cart();
			request.getSession().setAttribute("cart", cart);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/PageServlet").forward(request, response);
		}else{
			//带上出错的信息
			request.setAttribute("errorinfo", "错误的用户名或密码！");
			request.getRequestDispatcher("/NoLoginServlet").forward(request, response);
		}
		
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
