package com.postlounge.model.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.MemberDAO;
import com.postlounge.model.vo.MemberVO;

public class FindMyIdCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
		String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");

        MemberVO member = MemberDAO.findMyId(name, nickname);
        
        if (member != null) {
            // 아이디를 찾은 경우
            request.setAttribute("foundId", member.getId());
            return "foundId.jsp"; // 아이디가 표시되는 JSP 페이지로 리다이렉트
        } else {
            // 아이디를 찾지 못한 경우
            request.setAttribute("errorMessage", "입력하신 정보가 틀렸습니다. 다시 입력하세요.");
            return "error.jsp"; // 다시 아이디 찾기 페이지로 리다이렉트
        }
    }
}