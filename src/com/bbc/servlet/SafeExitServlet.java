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
			
		}else{//如果未登录，却点击安全退出，则无需理会，直接定位到主页面，并保持未登录状态
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
