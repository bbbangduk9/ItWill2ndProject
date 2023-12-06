package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.CommentDAO;
import com.postlounge.model.vo.CommentVO;
import com.postlounge.model.vo.MemberVO;

@WebServlet("/POSTLOUNGE/JsonInsertCommentController")
public class JsonInsertCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("JsonInsertCommentCOntroller_doPost() 실행 ~");
		
		String commContent; 
		int postIdx; 
				
		try {
			//로그인 정보 세션 정보 갖고와
			HttpSession session = request.getSession();
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
			
			System.out.println("session.getAttribute(\"loginUser\") : " + session.getAttribute("loginUser"));
			
			if(loginUser != null ) {
				//파라미터로부터 내용과 idx가져와
				commContent = request.getParameter("content");
				postIdx = Integer.parseInt(request.getParameter("postIdx"));
				
				System.out.println("request.getParameter(\"content\") : " + request.getParameter("content"));
				System.out.println("request.getParameter(\"postIdx\") : " + request.getParameter("postIdx"));
				
				//댓글 객체 생성
				CommentVO comment = new CommentVO();
				comment.setMemberIdx(loginUser.getMemberIdx());
				comment.setPostIdx(postIdx);
				comment.setCommContent(commContent);
				
				//CommentDAO로 댓글객체를 DB에 저장함 1성공 or 실패
				int isCommentSaved = CommentDAO.insertComment(comment);
				
				System.out.println("생성된 댓글 객체 comment : " + comment);
				// JSON 응답을 생성하여 클라이언트에 반환합니다.
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				// 댓글 저장 결과에 따라 JSON 응답을 생성합니다.
				String jsonResponse;
				if (isCommentSaved == 1) {
					jsonResponse = "{ \"result\": \"success\" }";
				} else {
					jsonResponse = "{ \"result\": \"failure\" }";
				}

				response.getWriter().write(jsonResponse);
			}
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}