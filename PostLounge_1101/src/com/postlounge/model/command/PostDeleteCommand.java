package com.postlounge.model.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.PostDAO;
import com.postlounge.model.vo.PostVO;

public class PostDeleteCommand implements Command {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vo = 0;
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		System.out.println("postIdx : " + postIdx);
		try {
			vo = PostDAO.deleteSelect(postIdx); // 인덱스 없으면 0
			System.out.println(vo);

			if (vo < 0) {
				request.setAttribute("errorMessage", "[삭제 실패] 입력 정보를 다시 한번 확인해주세요.");
				return "error.jsp"; // 에러 페이지로 이동

			}
			return "my.jsp";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "[삭제 실패] 오류가 발생했습니다. 담당자(8282)에게 연락하세요");
			return "error.jsp"; // 에러 페이지로 이동
		}
	}
}
