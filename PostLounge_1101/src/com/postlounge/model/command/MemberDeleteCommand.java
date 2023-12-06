package com.postlounge.model.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.MemberDAO;
import com.postlounge.model.vo.MemberVO;

public class MemberDeleteCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");

		try {
			MemberVO vo = MemberDAO.selectOne(id); // 아이디 검색으로 정보 가져오기. 없다면 null

			if (vo != null && vo.getName().equals(name) && vo.getId().equals(id) && vo.getPwd().equals(pwd)) {
				// 입력된 정보가 올바름
				MemberDAO.memberDelete(id);

				// 세션 무효화 (메모리 누수 방지, 정보 노출 방지)
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}

				// 회원 탈퇴 성공 후 페이지 이동
				return "memberDelete_success.jsp";
			} else {
				request.setAttribute("errorMessage", "[탈퇴 실패] 입력 정보를 다시 한번 확인해주세요.");
				return "error.jsp"; // 에러 페이지로 이동
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "[탈퇴 실패] 오류가 발생했습니다. 담당자(8282)에게 연락하세요");
			return "error.jsp"; // 에러 페이지로 이동
		}
	}
}
