package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.FollowDAO;
import com.postlounge.model.vo.MemberVO;

public class FollowCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//follow 추가 요청 처리
		//전달된 파라미터값 확인
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		System.out.println(">>> FollowCommand loginUser : " + loginUser);
		
		//로그인 유저가 null인 경우 -> 로그인페이지로 이동
		if (loginUser == null) {
			return "login.jsp";
		} 
		
		int follower = loginUser.getMemberIdx();
		System.out.println(":: 로그인 유저 idx follower : " + follower);
		int followee = Integer.parseInt(request.getParameter("param")); //친구의 idx값
		int postIdx = Integer.parseInt(request.getParameter("postIdx")); 
		System.out.println(":: 글 작성자 idx followee : " + followee);
		
		//내 게시물에서 친구추가 눌렀을 때
		if (follower == followee) {
			request.setAttribute("myPostMessage", "나 자신을 친구로 추가할 수 없습니다.");
			return "controller?type=detailMain&postIdx=" + postIdx;
		}
		
		//친구의 idx값으로 isDel값이 0인지 1인지 확인
		//int 값으로 받고 1이면 "탈퇴한 회원입니다"
		int friendIsdel = FollowDAO.findFriendIsdel(followee);
		System.out.println("friendIsdel : " + friendIsdel);
		if (friendIsdel > 0) {
			request.setAttribute("errorMessage", "이미 탈퇴한 회원입니다");
			return "controller?type=detailMain&postIdx=" + postIdx;
		}
		
		//팔로우 중복체크
		boolean isAlreadyFriends = FollowDAO.isAlreadyFriends(follower, followee);
		System.out.println("!!!!! isAlreadyFriends : " + isAlreadyFriends); //친구면 true 리턴
		
		if (isAlreadyFriends) { //true일 때 (DB에 데이터가 이미 존재하는 경우)
			request.setAttribute("errorMessage", "이미 추가한 팔로워입니다.");
			return "controller?type=detailMain&postIdx=" + postIdx;
		} else {
			//DB에 insert 처리
			int result = FollowDAO.insert(follower, followee);
			System.out.println("> insert 처리결과 : " + result);
			
			request.setAttribute("successMessage", "팔로우 추가 완료!");
			return "controller?type=detailMain&postIdx=" + postIdx;
		}
	}
}
