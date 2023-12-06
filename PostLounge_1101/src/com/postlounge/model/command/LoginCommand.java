package com.postlounge.model.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.MemberDAO;
import com.postlounge.model.vo.MemberVO;

public class LoginCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession();

		List<String> idList = new ArrayList<>(); // 전체 id를 담을 idList 생성
		List<MemberVO> users = MemberDAO.memberInfos(); // 회원의 모든 아이디를 가져옴
		MemberVO vo = MemberDAO.selectOne(id); // 전달받은 id값으로 idx, 이름, 아이디, 비밀번호, 닉네임 가져옴
		for (MemberVO member : users) {
			idList.add(member.getId()); // idList에 전체 id 담기
		}
		System.out.println("전체 users : " + users);
		System.out.println("전체 idList : " + idList);

		if (!idList.contains(id)) { // 입력한 id가 확인되지 않은 경우
			System.out.println("[로그인 실패] 확인되지 않은 id");
			request.setAttribute("errorMessage", "존재하지 않는 아이디입니다.");
			return "error.jsp";
		} else if (idList.contains(id) && !vo.getPwd().equals(pwd)) {
			System.out.println("[로그인 실패] 비밀번호 틀림");
			request.setAttribute("errorMessage", "비밀번호가 올바르지 않습니다.");
			return "error.jsp";
		} else {
			MemberVO loginUser = MemberDAO.selectOne(vo.getId());
			System.out.println(">> loginUser : " + loginUser);
			//로그인 : 세션에 정보 저장
			session.setAttribute("memberVO", loginUser);
			session.setAttribute("loginUser", loginUser);
			return "index.jsp";
		}
	}

}
