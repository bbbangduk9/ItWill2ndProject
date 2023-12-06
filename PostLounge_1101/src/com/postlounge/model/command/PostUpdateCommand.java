package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.PostDAO;

public class PostUpdateCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		System.out.println("postIdx : " + postIdx);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String nickname = request.getParameter("nickname");
		System.out.println("PostUpdateCommand nickname : " + nickname);
		System.out.println("PostUpdateCommand content : " + content);
		
		int update = PostDAO.updatePost(postIdx, title, content);
		
		if (update > 0) {
			// 게시물이 성공적으로 업데이트됨
			// 성공 페이지로 리디렉션 또는 포워딩
			return "my.jsp";
		} else {
			// 업데이트 실패
			// 오류 메시지 설정 및 오류 페이지로 리디렉션
			request.setAttribute("errorMessage", "[수정 실패] 오류가 발생했습니다. 담당자(8282)에게 연락하세요");
			return "error.jsp"; // 에러 페이지로 이동
		}
	
	}
}