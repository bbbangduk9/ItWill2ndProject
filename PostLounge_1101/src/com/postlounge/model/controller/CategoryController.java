package com.postlounge.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.PostDAO;
import com.postlounge.model.vo.PostListVO;

@WebServlet("/POSTLOUNGE/post")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<PostListVO> postList = null;
		String type = req.getParameter("type");

		if (type == null || type.equals("/PostAllList")) { // 전체 카테고리인 경우
			postList = PostDAO.getPostList();
			
		} else if (type.equals("food")) { // 음식 카테고리인 경우
			postList = PostDAO.ctTypelist("food"); // ctFoods() 메서드의 인자로 해당하는 값(예시로 "음식") 전달하여 필요한 SQL문 실행
			System.out.println(">> CategoryController postList : " + postList);
			req.setAttribute("postList", postList); // postList를 request 객체에 저장
			RequestDispatcher dispatcher = req.getRequestDispatcher("food.jsp");
			dispatcher.forward(req, resp);

		} else if (type.equals("health")) { // 건강 카테고리인 경우
			postList = PostDAO.ctTypelist("health");

			req.setAttribute("postList", postList); // postList를 request 객체에 저장
			RequestDispatcher dispatcher = req.getRequestDispatcher("health.jsp");
			dispatcher.forward(req, resp);

		} else if (type.equals("sport")) { // 스포츠 카테고리인 경우
			postList = PostDAO.ctTypelist("sport");
			
			req.setAttribute("postList", postList); // postList를 request 객체에 저장
			RequestDispatcher dispatcher = req.getRequestDispatcher("sport.jsp");
			dispatcher.forward(req, resp);

		} else if (type.equals("music")) { // 음악 카테고리인 경우
			postList = PostDAO.ctTypelist("music");

			req.setAttribute("postList", postList); // postList를 request 객체에 저장
			RequestDispatcher dispatcher = req.getRequestDispatcher("music.jsp");
			dispatcher.forward(req, resp);

		} else if (type.equals("movie")) { // 영화 관련 카테고리인 경우
			postList = PostDAO.ctTypelist("movie");
			
			req.setAttribute("postList", postList); // postList를 request 객체에 저장
			RequestDispatcher dispatcher = req.getRequestDispatcher("movie.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
