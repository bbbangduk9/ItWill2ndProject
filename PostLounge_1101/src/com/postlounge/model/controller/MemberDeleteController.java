package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.command.Command;
import com.postlounge.model.command.MemberDeleteCommand;

@WebServlet("/POSTLOUNGE/memberDelete")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 실제 작업 수행
		Command command = new MemberDeleteCommand();
		String view = command.exec(request, response);

		// 처리 결과에 따라 뷰로 포워딩 or 리다이렉트
		if (view != null) {
			request.getRequestDispatcher(view).forward(request, response);
		} else {
			// 에러 처리
			response.sendRedirect("error.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}
