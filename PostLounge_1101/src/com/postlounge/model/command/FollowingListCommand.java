package com.postlounge.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.FollowDAO;
import com.postlounge.model.paging.FollowListPaging;
import com.postlounge.model.vo.FriendListVO;
import com.postlounge.model.vo.MemberVO;

public class FollowingListCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//팔로우 리스트 조회하기 (내가 팔로우한)
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		System.out.println(":: 로그인 유저 loginUser : " + loginUser);
		
		if (loginUser == null) {
			return "login.jsp";
		}
		
		Object followList = session.getAttribute("followList");
		System.out.println(">>> 내가 팔로우하는 followList : " + followList);
		int followListCnt = (Integer) session.getAttribute("followListCnt");
		
		//팔로우하는 사람이 없는 경우 -> 메인페이지로 이동
		if(followListCnt < 1) {
			request.setAttribute("followingmessage", "팔로우중인 작성자가 없습니다.");
			return "feed.jsp";
		}
		
		//====================================
		// 팔로우 목록 페이징 처리
		//0. 페이징 처리를 위한 객체(Paging) 생성
		FollowListPaging p = new FollowListPaging();

		//1. 전체 게시물 수량 구하기
		followListCnt = (int)session.getAttribute("followListCnt"); //팔로우 수
		p.setTotalRecord(followListCnt); //객체에 저장(페이지 수를 구하기 위해서)
	 	p.setTotalPage(); //페이지수 구하기
		
	 	//2. 현재 페이지 구하기
	 	//p의 nowpage를 cPage 파라미터로 전달함
	 	String cPage = request.getParameter("cPage"); 
	 	if (cPage != null) { //null이면 디폴트값(1) 사용
	 		p.setNowPage(Integer.parseInt(cPage));
	 	}
		
		//3. 현재 페이지에 표시할 게시글의 시작번호(begin), 끝번호(end) 구하기 
		p.setEnd(p.getNowPage() * p.getNumPerPage()); 
		p.setBegin(p.getEnd() - p.getNumPerPage() + 1);
				
		//3-1.(선택적) 끝 번호가 데이터 건수보다 많아지면 데이터 건수와 동일하게 설정
		if (p.getEnd() > p.getTotalRecord()) { //끝번호가 전체데이터보다 크냐
			p.setEnd(p.getTotalRecord());
		}
		
		//----- 블록(block) 계산하기 ------
		//4. 블록 시작페이지, 끝페이지 구하기(현재페이지 번호 사용)
		//시작페이지 구하기
		int beginPage = ((p.getNowPage() - 1) / p.getPagePerBlock() 
				* p.getPagePerBlock() + 1);
		p.setBeginPage(beginPage);
		p.setEndPage(beginPage + p.getPagePerBlock() - 1);
		
		//4-2. 끝페이지(endPage)가 전체 페이지 수(totalPage) 보다 크면
		// 끝페이지를 전체 페이지 수로 설정 (없는 것은 표시하지 않는다)
		if(p.getEndPage() > p.getTotalPage()) {
			p.setEndPage(p.getTotalPage());
		}
		
		//==========================
		int memberIdx = loginUser.getMemberIdx();
		
		//현재 페이지 기준으로 DB데이터(게시글) 가져오기
		//시작번호(begin), 끝번호(end) 사용해서 
		int begin = p.getBegin();
		int end = p.getEnd();
		
		List<FriendListVO> list = FollowDAO.getList(begin, end, memberIdx);
		System.out.println(">> 현재페이지 글목록(list) : " + list);
		
		//-----------------
		//JSTL, EL 사용을 위해 scope에 데이터 등록(page 영역)
		session.setAttribute("list", list);
		session.setAttribute("pvo", p);
		request.setAttribute("cPage", cPage);
		
		return "followingList.jsp";
	}

}
