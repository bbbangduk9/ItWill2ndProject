package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.command.Command;
import com.postlounge.model.command.PostDeleteCommand;
import com.postlounge.model.dao.PostDAO;
import com.postlounge.model.vo.PostVO;

@WebServlet("/POSTLOUNGE/postdelete")
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Command comman = new PostDeleteCommand();
		String view = comman.exec(req, resp);

		if (view != null) {
			req.getRequestDispatcher(view).forward(req, resp);
		} else {
			resp.sendRedirect("error.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}
}
