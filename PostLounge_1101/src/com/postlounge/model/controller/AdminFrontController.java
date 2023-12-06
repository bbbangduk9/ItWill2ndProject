package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.command.Command;

//2.경로를 설정!
@WebServlet("/office/controller")
public class AdminFrontController extends HttpServlet { //1. 서블릿을 상속
	private static final long serialVersionUID = 1L;
	
	Command command = null;
	
	//3. 두겟 메소드!
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("프론트 컨트롤러의 두겟 메소드 정상 작동!");
		String type = request.getParameter("type");
		System.out.println("type : " + type);

		switch (type) {
			case "admin_search_all" :
				request.getRequestDispatcher("admin_search_all.jsp").forward(request, response);
				break;
			case "admin_all" :
				request.getRequestDispatcher("admin_all.jsp").forward(request, response);
				break;
			case "admin_best" :
				request.getRequestDispatcher("admin_best.jsp").forward(request, response);
				break;
			case "admin_manage_post" :
				request.getRequestDispatcher("admin_manage_post.jsp").forward(request, response);
				break;
			case "admin_manage_comment" :
				request.getRequestDispatcher("admin_manage_comment.jsp").forward(request, response);
				break;
			case "admin_manage_member" :
				request.getRequestDispatcher("admin_manage_member.jsp").forward(request, response);
				break;
			case "admin_manage_opr" :
				request.getRequestDispatcher("admin_manage_opr.jsp").forward(request, response);
				break;
			case "admin_manage_report" :
				request.getRequestDispatcher("admin_manage_report.jsp").forward(request, response);
				break;
			case "admin_main" :
				request.getRequestDispatcher("admin_main.jsp").forward(request, response);
				break;
		}
		
	}
	
	//4. 두포스트 메소드!
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	
	}
	
}
