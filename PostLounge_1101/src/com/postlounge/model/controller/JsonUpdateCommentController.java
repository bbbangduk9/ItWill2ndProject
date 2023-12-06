package com.postlounge.model.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.CommentDAO;
import com.postlounge.model.vo.CommentVO;


@WebServlet("/POSTLOUNGE/JsonUpdateCommentController")
public class JsonUpdateCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("JsonUpdateCommentController _ doPost() 실행 ");
		
		response.setContentType("application/json; charset=UTF-8");

		try {
			String content = request.getParameter("content");
			System.out.println("content : " + content);
			int commentIdx = Integer.parseInt( request.getParameter("commentIdx"));
			System.out.println("request.getParameter(\"commentIdx\") : " + request.getParameter("commentIdx"));
			
			String commentIdxParam = request.getParameter("commentIdx");
			commentIdx = 0; // 기본값 설정
			if (commentIdxParam != null && !commentIdxParam.isEmpty()) {
			    commentIdx = Integer.parseInt(commentIdxParam);
			}
			
			CommentVO vo = new CommentVO();
			vo.setCommContent(content);
			vo.setCommentIdx(commentIdx);
			
			int result = CommentDAO.updateComment(vo);
			
			if(result > 0 ){
				//SON 객체를 반환
                response.getWriter().write("{\"result\": \"success\"}"); //ajax와 맞아야함, json이냐 text냐도 맞아야함.
			} else {
				response.getWriter().write("{\"result\": \"실패\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//오류시 JSON 객체를 반환
			response.getWriter().write("{\"result\": \"오류\"}");
		}
	}
}
