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
import com.bbc.service.Cart;

public class BookServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();	
		
		
		String type= request.getParameter("type");		
		BookService bookService=new BookService();		
		if("detail".equals(type)){
			String id= request.getParameter("id");			
			Book book=bookService.getOneBookById(id);
			//在页面跳转之前，把获取的这本书的信息，放置到attribute域中，以便带到下一个页面中。
			//需要使用 转发的形式，不能使用重定向。
			request.setAttribute("book", book);
			request.getRequestDispatcher("/WEB-INF/showdetail.jsp").forward(request, response);
			
		}else if("buy".equals(type)){
			if(request.getSession().getAttribute("user")==null){
				//如果没有登录，而且点击了查看购物车，则显示出错页面，两秒钟后重定向到主页面
				request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				
			}else{
			String id=  request.getParameter("id");			
			//从这个用户分配的session中取出购物车。
			Cart cart =  (Cart) request.getSession().getAttribute("cart");
			cart.buyBook(id);	
			request.getRequestDispatcher("/PageServlet").forward(request, response);
			}		
		}else if("showcart".equals(type)){	
			if(request.getSession().getAttribute("user")==null){
				//如果没有登录，而且点击了查看购物车，则显示出错页面，两秒钟后重定向到主页面
				request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				
			}else{			
				Cart cart =  (Cart) request.getSession().getAttribute("cart");					
				ArrayList<Book> books= cart.showMyCart();
				request.setAttribute("books", books);
				float price =cart.getPrice();
				request.setAttribute("price", price);			
				request.getRequestDispatcher("/WEB-INF/showcart.jsp").forward(request, response);
				}
			} else if("delete".equals(type)){
			String id=  request.getParameter("id");
			Cart cart =  (Cart) request.getSession().getAttribute("cart");
			cart.deleteBook(id);
			ArrayList<Book> books= cart.showMyCart();
			request.setAttribute("books", books);
			float price =cart.getPrice();
			request.setAttribute("price", price);
			request.getRequestDispatcher("/WEB-INF/showcart.jsp").forward(request, response);
			
		}else if("clear".equals(type)){
			Cart cart =  (Cart) request.getSession().getAttribute("cart");
			cart.clearBook();
			ArrayList<Book> books= cart.showMyCart();
			request.setAttribute("books", books);
			float price =cart.getPrice();
			request.setAttribute("price", price);
			request.getRequestDispatcher("/WEB-INF/showcart.jsp").forward(request, response);
			
		}else if("update".equals(type)){
			Cart cart =  (Cart) request.getSession().getAttribute("cart");
			String []id= request.getParameterValues("id");
			String []buynums=request.getParameterValues("buynums");
			for(int i=0;i<id.length;i++){				
				String id2= id[i];
				cart.updateCart(id2, Integer.parseInt(buynums[i]));				
			}
			ArrayList<Book> books= cart.showMyCart();
			request.setAttribute("books", books);
			float price =cart.getPrice();
			request.setAttribute("price", price);
			request.getRequestDispatcher("/WEB-INF/showcart.jsp").forward(request, response);
		}		
		out.flush();
		out.close();
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
