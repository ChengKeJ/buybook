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
		//��ȡbook.jsp���ݹ������û���������	
		String username=  request.getParameter("username");
		String password= request.getParameter("password");
		//�������ȷ���û��������룬ҳ���Ҫ��ת
		UserService userService=new UserService();
		User user=new User();
		user= userService.getUserByNameAndPSW(username, password);
		
		//��ҳ
		int pageNow=1;//��ǰҳ
		int pageCount=1;//һ�����Էֶ���ҳ����ϵͳ�������ݼ����
		int pageSize=5;//ÿһҳ����ʾ���ݵ�����
		int rowCount=1;//���ݿ���һ���ж�����
		
		
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
			//���ϳ������Ϣ
			request.setAttribute("errorinfo", "������û��������룡");
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
