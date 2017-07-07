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

public class NoLoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		/* //分页
		int pageNow=1;//当前页
		int pageCount=1;//一共可以分多少页，是系统根据数据计算的
		int pageSize=5;//每一页，显示数据的行数
		int rowCount=1;//数据库中一共有多少行
		BookService bookService=new BookService();
		pageCount= bookService.getPageCount(pageSize);
		request.setAttribute("pageCount", pageCount);
		//在跳转到book.jsp页面的时候，需要从数据库中取出全部的书，并且置入attribute域中
		ArrayList<Book> books= bookService.getBooksBySelected(pageNow, pageSize);
		request.setAttribute("books", books);
		request.setAttribute("pageNow", pageNow);
		request.getSession().setAttribute("user", null);
		request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
		*/
		request.getSession().setAttribute("user", null);//user为空，说明未登录，无需分配购物车
		request.getRequestDispatcher("/PageServlet").forward(request, response);
		
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doGet(request, response);
	}

}
