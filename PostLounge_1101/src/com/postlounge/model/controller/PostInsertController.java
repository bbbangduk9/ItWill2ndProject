package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.postlounge.model.dao.PostDAO;
import com.postlounge.model.vo.MemberVO;
import com.postlounge.model.vo.PostVO;

/*
 * Command 없이 Controller 하나로 구현함 (게시물 업로드 및 파일 업로드)
 * */

@WebServlet("/POSTLOUNGE/insertPost")
public class PostInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		/*
		 * 파라미터 가져오기 (MultipartRequest 써야 가져오기 가능)-> 아래에서 서술 String title =
		 * request.getParameter("title"); String postType =
		 * request.getParameter("postType"); String postContent =
		 * request.getParameter("content");
		 */

		// 로그인된 세션에서 작성자 정보 가져오기
		HttpSession session = request.getSession();
		MemberVO writer = (MemberVO) session.getAttribute("loginUser");
		System.out.println("writer: " + writer);

		if (writer != null) {
			int memberIdx = writer.getMemberIdx();

			try {
				// 업로드 파일 처리 코드
				/*** 글쓰기 test시 path값 각자 세팅에 맞게 수정해야됨 ***/
				String path = "C:/MyStudy/60_web/PostLounge_pro/WebContent/postImage";
				MultipartRequest mr = new MultipartRequest(request, path, (10 * 1024 * 1024), "UTF-8",
						new DefaultFileRenamePolicy());

				PostVO post = new PostVO();
				post.setTitle(mr.getParameter("title"));
				post.setPostType(mr.getParameter("postType"));
				post.setPostContent(mr.getParameter("content"));
				post.setMemberIdx(memberIdx);

				System.out.println("title : " + post.getTitle());
				System.out.println("postContent : " + post.getPostContent());
				System.out.println("postType : " + post.getPostType());
				System.out.println("memberIdx : " + memberIdx);

				// 첨부파일 데이터 처리
				if (mr.getFile("fileName") != null) { // 첨부파일이 있으면
					post.setPostFileName(mr.getFilesystemName("fileName"));
					post.setPostOriName(mr.getOriginalFileName("fileName"));
				} else { // 첨부파일이 없으면
					post.setPostFileName(null);
					post.setPostOriName(null);
				}
				System.out.println("PostFileName : " + post.getPostFileName());
				System.out.println("PostOriName : " + post.getPostOriName());

				// 파라미터 전달받은 값으로 만든 객체 저장
				int result = PostDAO.writePost(post);
				System.out.println(">> 게시물 업로드 result : " + result);
				
				if (result > 0) {
					// 게시물 업로드 성공
					String successMessage = "[게시물 작성 성공] 메인 페이지로 이동합니다.";
					request.setAttribute("successMessage", successMessage);
					RequestDispatcher dispatcher = request.getRequestDispatcher("filter.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", "[작성 실패] 오류가 발생했습니다. 담당자(8282)에게 연락하세요");
					RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "[작성 실패] 오류가 발생했습니다. 담당자(8282)에게 연락하세요");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "세션이 만료되었거나 로그인되지 않았습니다.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}

}