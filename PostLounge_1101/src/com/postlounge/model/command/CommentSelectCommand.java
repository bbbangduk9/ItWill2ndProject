package com.postlounge.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.CommentDAO;
import com.postlounge.model.dao.PostDAO;
import com.postlounge.model.vo.AllMixVO;

public class CommentSelectCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터 가져와
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		System.out.println("postIdx : " + postIdx);

		// 작업처리 및 DB연동
		List<AllMixVO> list = CommentDAO.selectComment(postIdx);
		request.setAttribute("listComment", list);
		System.out.println("list : " + list);

		return "commentList.jsp";
	}

}
