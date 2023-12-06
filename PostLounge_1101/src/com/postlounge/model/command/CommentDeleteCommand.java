package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.CommentDAO;

public class CommentDeleteCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommnetDeleteCommand_ exec() 실행");
		// int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		// System.out.println("postIdx : " + postIdx);
		int commentIdx = Integer.parseInt(request.getParameter("commentIdx"));
		System.out.println("commentIdx : " + commentIdx);

		try {
			int result = CommentDAO.deleteComment(commentIdx);
			System.out.println("result(결과) : " + result);

			if (result > 0) {
				// 댓글 삭제 성공
				return "index.jsp";
			} else {
				request.setAttribute("errorMessage", "[삭제 실패] 입력 정보를 다시 한번 확인해주세요.");
				return "error.jsp"; // 에러 페이지로 이동
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "[삭제 실패] 입력 정보를 다시 한번 확인해주세요.");
			return "error.jsp"; // 에러 페이지로 이동
		}

	}

}