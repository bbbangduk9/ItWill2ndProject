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

@WebServlet("/POSTLOUNGE/getPostList")
public class PostlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메인페이지에서 top10 게시물 가져오기(isDel값이 0 또는 1인 게시물만 가져와야됨)
		System.out.println("PostlistController doGet() 실행~~");

		// 최근 이틀 top10 게시물 가져오기 (조회수순으로)
		List<PostListVO> postList = PostDAO.getPostList();
		System.out.println("postList : " + postList);

		// postList를 request의 "postList"라는 이름의 속성으로 설정
		req.setAttribute("postList", postList);

		// JSP 파일로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("all.jsp");
		dispatcher.forward(req, resp);

	}
}
