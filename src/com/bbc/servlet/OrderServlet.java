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
import com.bbc.service.Cart;
import com.bbc.service.OrderService;
import com.bbc.service.OrderitemService;

public class OrderServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		OrderService orderService=new OrderService();
		OrderitemService orderitemService=new OrderitemService();
		Cart cart= (Cart) request.getSession().getAttribute("cart");
		System.err.println("---------");
		if(cart!=null){
			//1 ��ɶ���  �����  ���� ������  �� ���������
			
			User user= (User) request.getSession().getAttribute("user");
			
			orderService.addOrder(user.getId());
			
			int orderid=orderService.getMaxOrderid(user.getId()); //��ݵ�¼�û�useridȡ��max(orderid)
			Cart car= (Cart) request.getSession().getAttribute("cart");
			ArrayList<Book> al= cart.showMyCart();
			orderitemService.addOrderitem(orderid,al);
			
			request.getRequestDispatcher("/WEB-INF/buyok.jsp").forward(request, response);
		}else{
			
			//���ﳵΪ�գ�����ع�����棬����ʾ��
			
		}
		
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
