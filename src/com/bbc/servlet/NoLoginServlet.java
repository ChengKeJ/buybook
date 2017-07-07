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
		
		/* //��ҳ
		int pageNow=1;//��ǰҳ
		int pageCount=1;//һ�����Էֶ���ҳ����ϵͳ�������ݼ����
		int pageSize=5;//ÿһҳ����ʾ���ݵ�����
		int rowCount=1;//���ݿ���һ���ж�����
		BookService bookService=new BookService();
		pageCount= bookService.getPageCount(pageSize);
		request.setAttribute("pageCount", pageCount);
		//����ת��book.jspҳ���ʱ����Ҫ�����ݿ���ȡ��ȫ�����飬��������attribute����
		ArrayList<Book> books= bookService.getBooksBySelected(pageNow, pageSize);
		request.setAttribute("books", books);
		request.setAttribute("pageNow", pageNow);
		request.getSession().setAttribute("user", null);
		request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
		*/
		request.getSession().setAttribute("user", null);//userΪ�գ�˵��δ��¼��������乺�ﳵ
		request.getRequestDispatcher("/PageServlet").forward(request, response);
		
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doGet(request, response);
	}

}
