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

public class FollowerListCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//팔로워 리스트 조회하기 (나를 팔로우한)
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "login.jsp";
		}
		
		Object followerList = session.getAttribute("followerList");
		System.out.println(">>> 나를 팔로우하는 followerList : " + followerList);
		int followerListCnt = (Integer) session.getAttribute("followerListCnt");
		
		//나를 팔로우하는 사람이 없는 경우 -> 피드페이지 이동
		if(followerListCnt < 1) {
			request.setAttribute("followermessage", "팔로워가 없습니다. 피드로 이동합니다.");
			return "feed.jsp";
		}
		
		//====================================
		// 팔로우 목록 페이징 처리
		//0. 페이징 처리를 위한 객체(Paging) 생성
		FollowListPaging p = new FollowListPaging();

		//1. 전체 게시물 수량 구하기
		p.setTotalRecord(followerListCnt); //객체에 저장(페이지 수를 구하기 위해서)
	 	p.setTotalPage(); //페이지수 구하기
		
	 	System.out.println("> 전체 게시글 수 : " + p.getTotalRecord());
	 	System.out.println("> 전체 페이지 수 : " + p.getTotalPage());
	 	
	 	//2. 현재 페이지 구하기
	 	//p의 nowpage를 cPage 파라미터로 전달함
	 	String cPage = request.getParameter("cPage"); 
	 	if (cPage != null) { //null이면 디폴트값(1) 사용
	 		p.setNowPage(Integer.parseInt(cPage));
	 	}
		System.out.println("> cPage : " + cPage);
		System.out.println("> paging nowPage : " + p.getNowPage());
		
		//3. 현재 페이지에 표시할 게시글의 시작번호(begin), 끝번호(end) 구하기 
		//numPerPage에 설정된 수가 영향을 준다 (규칙 찾기)
		p.setEnd(p.getNowPage() * p.getNumPerPage()); 
		//end = 현재페이지 * 한페이지당 표시할 게시물의 갯수
		// 1페이지인 경우 (1 * 5) = 끝번호 5
		// 2페이지인 경우 (2 * 5) = 끝번호 10
		p.setBegin(p.getEnd() - p.getNumPerPage() + 1);
		//begin = end - 한페이지당 표시할 게시물의 갯수 + 1
		// 5 - 5 + 1 = 시작번호 1
		// 10 - 5 + 1 = 시작번호 6
				
		//3-1.(선택적) 끝 번호가 데이터 건수보다 많아지면 데이터 건수와 동일하게 설정
		if (p.getEnd() > p.getTotalRecord()) { //끝번호가 전체데이터보다 크냐
			p.setEnd(p.getTotalRecord());
		}
		System.out.println(">> 시작번호(begin) : " + p.getBegin());
		System.out.println(">> 끝번호(end) : " + p.getEnd());
		
		//----- 블록(block) 계산하기 ------
		//4. 블록 시작페이지, 끝페이지 구하기(현재페이지 번호 사용)
		//시작페이지 구하기
		int beginPage = ((p.getNowPage() - 1) / p.getPagePerBlock() 
				* p.getPagePerBlock() + 1);
		/* PagePerBlock은 하나의 블록에 표시되는 페이지 갯수
			3이면 블록[1 2 3] | 2인 경우 [1 2] | 4인 경우 [1 2 3 4] (괄호가 블럭임) */	
			
		p.setBeginPage(beginPage);
		p.setEndPage(beginPage + p.getPagePerBlock() - 1);
		
		//4-2. 끝페이지(endPage)가 전체 페이지 수(totalPage) 보다 크면
		// 끝페이지를 전체 페이지 수로 설정 (없는 것은 표시하지 않는다)
		if(p.getEndPage() > p.getTotalPage()) {
			p.setEndPage(p.getTotalPage());
		}
		
		System.out.println(">> 시작페이지(beginPage) : " + p.getBeginPage());
		System.out.println(">> 끝페이지(endPage) : " + p.getEndPage());
		//==========================
		int memberIdx = loginUser.getMemberIdx();
		
		//현재 페이지 기준으로 DB데이터(게시글) 가져오기
		//시작번호(begin), 끝번호(end) 사용해서 
		int begin = p.getBegin();
		int end = p.getEnd();
		System.out.println(">> 시작값 begin : " + begin);
		System.out.println(">> 끝나는값 end : " + end);
		
		List<FriendListVO> list = FollowDAO.getFollowerPagingList(begin, end, memberIdx);
		System.out.println(">> 현재페이지 글목록(list) : " + list);
		
		//-----------------
		//JSTL, EL 사용을 위해 scope에 데이터 등록(page 영역)
		session.setAttribute("list", list);
		session.setAttribute("pvo", p);
		
		System.out.println(">> cPage : " + cPage);
		request.setAttribute("cPage", cPage);

		return "followerList.jsp";
	}

}
