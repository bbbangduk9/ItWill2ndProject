package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.FollowDAO;
import com.postlounge.model.vo.MemberVO;

public class FollowDeleteCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//팔로우 삭제요청 처리
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		System.out.println(">>> DeleteFollowCommand loginUser : " + loginUser);
		String friendIdx = request.getParameter("param");
		System.out.println(":: 삭제할 friendIdx : " + friendIdx);
		
		//로그인 유저 null인 경우 -> 로그인페이지로 이동
		if (loginUser == null) {
			return "login.jsp";
		}
		
		int memberIdx = loginUser.getMemberIdx();	
		System.out.println(":: 로그인 유저 memberIdx : " + memberIdx);
		
		//삭제처리를 위해 friendIdx와 memberIdx값 전달(두개가 일치해야 값을 찾을 수 있음)
		int result = FollowDAO.memberDelete(friendIdx, memberIdx); 

		if (result < 0) {
			System.out.println("삭제처리 실패!! result : " + result);
		} else {
			System.out.println(">>> 삭제처리 결과 result : " + result);
		}
		
		//최종응답페이지
		return "followingList.jsp";
	}
	
}
