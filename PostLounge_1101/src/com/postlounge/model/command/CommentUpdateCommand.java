package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.CommentDAO;
import com.postlounge.model.vo.CommentVO;
import com.postlounge.model.vo.MemberVO;

public class CommentUpdateCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String commContent = request.getParameter("commContent");
		int commentIdx = Integer.parseInt(request.getParameter("commentIdx"));

		System.out.println("commContent : " + commContent);
		System.out.println("commentIdx : " + commentIdx);

		// 로그인 유저정보 넘겨받음
		HttpSession ss = request.getSession();
		MemberVO writer = (MemberVO) ss.getAttribute("loginUser");
		System.out.println("writer : " + writer);

		if (writer != null) {
			CommentVO vo = null;

			try {
				vo = new CommentVO();
				vo.setCommContent(commContent);
				vo.setCommentIdx(commentIdx);

				int result = CommentDAO.updateComment(vo);

				if (result > 0) {
					return "index.jsp";
				} else {
					return "error.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return "error.jsp";
		}

		return null;

	}

}
