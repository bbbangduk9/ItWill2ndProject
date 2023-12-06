package com.postlounge.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.postlounge.model.dao.CommentDAO;
import com.postlounge.model.vo.CommentVO;


@WebServlet("/POSTLOUNGE/jsonCommentListByPage")
public class JsonCommentListByPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("JsonCommentListByPage _ doPost() 실행 ");
		
		// 한글깨짐 방지를 위한 문자타입(UTF-8) 처리
		response.setContentType("text/html; charset=UTF-8");
		
		int postIdx = Integer.parseInt( request.getParameter("postIdx"));
		System.out.println("postIdx : " + postIdx);
		
		int lastCommentIndex = Integer.parseInt(request.getParameter("lastCommentIndex"));
		System.out.println("lastCommentIndex : " + lastCommentIndex);
		
		List<CommentVO> additionalComments = CommentDAO.moreComment(postIdx, lastCommentIndex);
		System.out.println("additionalComments : " + additionalComments);
		
		// 가져온 데이터를 JSON으로 변환
        Gson gson = new Gson();
        String jsonData = gson.toJson(additionalComments);
        
        // JSON 데이터를 클라이언트에 응답으로 전송
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
        
		
	}
	
		
}
