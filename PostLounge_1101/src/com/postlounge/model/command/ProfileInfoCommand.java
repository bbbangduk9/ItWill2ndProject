package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postlounge.model.dao.MemberDAO;
import com.postlounge.model.vo.MemberVO;

public class ProfileInfoCommand implements Command {
    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // "loginUser" 속성에서 id 가져오기
            String id = (String) request.getSession().getAttribute("loginUser");
            
            // MemberDAO를 사용하여 id에 해당하는 회원 정보 가져오기
            MemberVO member = MemberDAO.profileInfo(id);
            
            if (member != null) {
                request.setAttribute("member", member);
                return "profileInfo.jsp";
            } else {
                // 회원 정보를 찾을 수 없을 때 처리
                return "error.jsp";
            }
    }
}
