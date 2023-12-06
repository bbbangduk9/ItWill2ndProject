package com.postlounge.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.SearchDAO;
import com.postlounge.model.vo.PostVO;

public class BestCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<PostVO> list = SearchDAO.getTopList();

		request.setAttribute("list", list);

		System.out.println("성공 : " + list);
		return "bestList.jsp";

	}

}
