package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.MemberDAO;
import com.postlounge.model.vo.MemberVO;

public class JoinCommand_ori extends HttpServlet implements Command {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		System.out.println(":: DeptListController doGet() 시작------");
		String userId = request.getParameter("id");
		String userName = request.getParameter("name");
		String password = request.getParameter("pwd");
		String userNickname = request.getParameter("nickname");

		try {
			MemberVO userVO = new MemberVO();
			userVO.setId(userId);
			userVO.setName(userName);
			userVO.setPwd(password);
			userVO.setNickname(userNickname);

			int result = MemberDAO.insert(userVO);

			if (result > 0) {
				// 회원가입 성공 시
				String successMessage = "[회원가입 성공] 로그인 페이지로 이동합니다.";
				response.getWriter().write(successMessage);
				System.out.println("[콘솔]회원가입 성공");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);

			} else {
				// 회원가입 실패 시
				String errorMessage = "[회왼가입 실패] 오류가 발생했습니다. 담당자(8282)에게 연락하세요";
				String jsCode = "<script>alert('" + errorMessage + "');history.back();</script>";

				response.setContentType("text/html");
				response.getWriter().write(jsCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "[예외발생] 입력 작업 중 오류발생!!!\n담당자(8282)에게 연락하세요";
			String jsCode = "<script>alert('" + errorMessage + "');history.back();</script>";

			response.setContentType("text/html");
			response.getWriter().write(jsCode);
		}

		System.out.println(":: DeptListController doGet() 끝------");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println(":: DeptListController doPost() 시작------");
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
		System.out.println(":: DeptListController doPost() 끝------");
	}

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}
}
