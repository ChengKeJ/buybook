package com.bbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SafeExitServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		if(request.getSession().getAttribute("user")!=null){
			request.getSession().invalidate();
			request.getRequestDispatcher("/NoLoginServlet").forward(request, response);
			
		}else{//���δ��¼��ȴ�����ȫ�˳�����������ᣬֱ�Ӷ�λ����ҳ�棬������δ��¼״̬
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
