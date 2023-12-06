package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.PostDAO;
import com.postlounge.model.vo.MemberVO;
import com.postlounge.model.vo.PostListVO;

public class UpdateSelectCommand implements Command{
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//detail(글 상세보기) 요청처리
		//전달받은 파라미터값 확인
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		System.out.println("선택한 게시글의 postIdx : " + postIdx);
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		//화면분기처리
		if (loginUser == null) {
			return "login.jsp";
		}
		
		//DB연동
		PostListVO vo = PostDAO.selectOne(postIdx);
		System.out.println("선택한 게시글의 vo : " + vo);
		request.setAttribute("vo", vo);
		
		//최종응답페이지 리턴
		return "postupdate.jsp";
	}

}
