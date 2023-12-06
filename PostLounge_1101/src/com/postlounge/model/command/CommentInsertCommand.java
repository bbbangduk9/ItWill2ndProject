package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.CommentDAO;
import com.postlounge.model.vo.CommentVO;
import com.postlounge.model.vo.MemberVO;

public class CommentInsertCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommentInsertCommand_exec() 실행 ~");
		//로그인 유저 정보 뽑고
		HttpSession ss = request.getSession();
		System.out.println("request.getSession() : " + ss);
		MemberVO writer = (MemberVO) ss.getAttribute("loginUser");
		System.out.println("writer : " + writer);
		
		//postIdx 가져오고
		int postIdx = Integer.parseInt(request.getParameter("postIdx"));
		System.out.println("exec _ postIdx : " + postIdx);

		//내용 가져와서	
		String commContent = request.getParameter("content");
		System.out.println("commContent : " + commContent);

		//데이터 작업 + 예외처리
		if (writer != null) { // 세션 만료됐을 경우가 아니라면~
			int memberIdx = writer.getMemberIdx(); //로그인된 계정의 IDX 가져와
			System.out.println("memberIdx : " + memberIdx);

			try {
				CommentVO vo = new CommentVO(); //댓글 객체 생성해주자, 컬럼값 집어넣어
				vo.setMemberIdx(memberIdx);
				vo.setPostIdx(postIdx);
				vo.setCommContent(commContent);

				int result = CommentDAO.insertComment(vo); //객체 생성 성공 혹은 실패 

				if (result > 0) {
					request.setAttribute("success", "작업 성공 ^-^");
					return "index.jsp";
				} else {
					request.setAttribute("errorMessage", "작업 실 ~~~~~~~ 패");
					return "error.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "작업 실 ~~~~~~~ 패");
				return "error.jsp";
			}
		} else {
			request.setAttribute("errorMessage", "작업 실 ~~~~~~~ 패");
			return "error.jsp";
		}
	}

}