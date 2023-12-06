package com.postlounge.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.CommentDAO;
import com.postlounge.model.vo.AllMixVO;
import com.postlounge.model.vo.MemberVO;

@WebServlet("/POSTLOUNGE/jsonCommentList")
public class JsonCommentSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberVO loginUser;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("JsonCOmmentSelectcontroller _ doPost() 실행 ");
		
		// 한글깨짐 방지를 위한 문자타입(UTF-8) 처리
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("request.getParameter(\"postIdx\") : " + request.getParameter("postIdx"));
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		
		List<AllMixVO> list = CommentDAO.selectComment(postIdx); //게시물에 달린 댓글들 호출
		System.out.println("게시물에 달린 댓글들 호출(postIdx) list : " + list);

		// JSON 응답을 생성하고
		HttpSession session = request.getSession();
		String result = makeJson(list, session); //게시물에 달린 댓글(list)들을 대상으로 댓글 객체별로 특정 요소값들을 출력 그리고 session값 포함 
		System.out.println("댓글객체당 닮긴 요소(list)result  : " + result);

		// 클라에게 전송해
		PrintWriter out = response.getWriter();
		out.print(result);
		
		return;
	}

	private String makeJson(List<AllMixVO> list, HttpSession session) {
		
		StringBuilder json = new StringBuilder();
		json.append("[");

		for (AllMixVO vo : list) {
			json.append("{");
			json.append("\"commentIdx\": \"" + vo.getCommentIdx() + "\",");
			json.append("\"memberIdx\": \"" + vo.getMemberIdx() + "\",");
			json.append("\"postIdx\": \"" + vo.getPostIdx() + "\",");
			json.append("\"nickname\": \"" + vo.getNickname() + "\",");
			json.append("\"commDate\": \"" + vo.getCommDate() + "\",");
			json.append("\"commContent\": \"" + vo.getCommContent() + "\"");
			
			// 세션에서 필요한 값을 가져와서 JSON 응답에 추가
	        if (session != null) {
	            MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	            if (loginUser != null) {
	                json.append(",\"loginUserMemberIdx\": \"" + loginUser.getMemberIdx() + "\"");
	            }
	        }

	        json.append("},");
	    }

	    // Remove the trailing comma (,) if there are elements
	    if (!list.isEmpty()) {
	        json.deleteCharAt(json.length() - 1);
	    }
		json.append("]");

		return json.toString();
	}
	
}
