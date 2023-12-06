package com.postlounge.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.PostDAO;
import com.postlounge.model.vo.MemberVO;
import com.postlounge.model.vo.PostListVO;

public class MyPostCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		System.out.println(">>> MyPostCommand loginUser : " + loginUser);
		
		if (loginUser == null) {
			return "login.jsp";
		}
		String memberId = loginUser.getId();
		List<PostListVO> myPostList = PostDAO.getMyPostList(memberId); // 해당 memberIdx의 게시물 리스트 가져오기
		System.out.println("MyPostCommand myPostList : " + myPostList);
		
		int myPostListCnt = myPostList.size();
        
		session.setAttribute("myPostList", myPostList); // 가져온 게시물 리스트를 request에 저장
		session.setAttribute("myPostListCnt", myPostListCnt);
		
		List<PostListVO> list = PostDAO.getMyPostList(memberId);
		session.setAttribute("list", list);
		
		
        return "mypostlist.jsp";
	}
	
}
