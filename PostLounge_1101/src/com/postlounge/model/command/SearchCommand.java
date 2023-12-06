package com.postlounge.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.SearchDAO;
import com.postlounge.model.vo.PostListVO;

public class SearchCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 요청 처리
		// String stIdx = request.getParameter("searchTypeIdx");
		String idx = request.getParameter("idx");
		String keyword = request.getParameter("keyword");

		if (keyword == null || keyword.trim().equals("")) {
			return "search.jsp";
		}
		
		//DB연동 데이터 처리
		List<PostListVO> list = SearchDAO.getSearch(idx, keyword);
		int searchResult = list.size(); //처리결과
		
		System.out.println("DB처리결과 : " + list);
		System.out.println("DB처리결과 몇개? : " + searchResult);
		
		request.setAttribute("list", list);
		
		//검색결과 없는 경우
		if (searchResult < 1) {
			request.setAttribute("searchResult", "검색결과가 없습니다.");
		}
		
		request.setAttribute("keyword", keyword);
		
		return "searchList.jsp";
		
	}

}

