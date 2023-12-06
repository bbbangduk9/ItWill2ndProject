package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.command.Command;
import com.postlounge.model.command.PostUpdateCommand;

@WebServlet("/POSTLOUNGE/updatePost")
public class PostUpdateController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		Command command = new PostUpdateCommand();
		String view = command.exec(request, response);
		System.out.println("postUpdateController : " + view);
		
		if (view != null) {
			request.getRequestDispatcher(view).forward(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
		} catch (Exception e) {
			e.getMessage();
			System.out.println("오류" + e.getMessage());
		}
	}
}
