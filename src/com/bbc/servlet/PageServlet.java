package com.bbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bbc.model.Book;
import com.bbc.service.BookService;

public class PageServlet extends HttpServlet {	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//分页算法
		int pageNow=1; //当前页    1   2   3    4
		int pageCount=1; // 显示的页数 
		int pageSize=5;//一页显示多少本书
		int rowCount=1;//数据库中的某个表真实的行数 select
		String pageNowTemp = request.getParameter("pageNow");
		if(pageNowTemp!=null){
			pageNow= Integer.parseInt(pageNowTemp);
		}		
		BookService bookService=new BookService();		
		ArrayList<Book> books=bookService.getBooksBySelected(pageNow, pageSize);
		request.setAttribute("books", books);
		pageCount= bookService.getPageCount(pageSize);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);
		request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);	
		
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
