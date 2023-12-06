package com.postlounge.model.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.MemberDAO;
import com.postlounge.model.vo.MemberVO;

public class JoinCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("JoinCommand _ exec() 실행");
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String nickname = request.getParameter("nickname");

		// 필드 입력 유효성 검사
		if (name.isEmpty()) {
		    request.setAttribute("errorMessage", "이름을 입력해주세요.");
		    return "error.jsp";
		} else if (id.isEmpty()) {
		    request.setAttribute("errorMessage", "아이디를 입력해주세요.");
		    return "error.jsp";
		} else if (pwd.length() < 6 || pwd.length() > 12) {
			request.setAttribute("errorMessage", "비밀번호 입력이 잘못되었습니다.");
			System.out.println("비번 조건 안맞음");
			return "error.jsp"; 
		} else if (nickname.isEmpty()) {
		    request.setAttribute("errorMessage", "별명을 입력해주세요.");
		    return "error.jsp";
		}
		
		try {
	        List<MemberVO> memberList = MemberDAO.memberInfos();

	        System.out.println("memberList: " + memberList);

	        boolean idDupli = false;
	        for (MemberVO member : memberList) {
	            if (member.getId().equals(id)) {
	                idDupli = true;
	                break;
	            }
	        }
	        if (idDupli) {
	        	request.setAttribute("errorMessage", "아이디가 이미 사용 중입니다. 다른 아이디를 입력해주세요.");
	            System.out.println("아이디 중복");
	            return "error.jsp"; 
	        } else {
	            boolean nickExists = false;
	            for (MemberVO member : memberList) {
	                if (member.getNickname().equals(nickname)) {
	                    nickExists = true;
	                    break;
	                }
	            }
	            if (nickExists) {
	                request.setAttribute("errorMessage", "닉네임이 이미 사용 중입니다. 다른 닉네임을 입력해주세요.");
	                System.out.println("닉네임 중복");
	                return "error.jsp"; 
	            } else {
	                MemberVO user = new MemberVO();
	                user.setId(id);
	                user.setPwd(pwd);
	                user.setNickname(nickname);
	                user.setName(name);
	                
	                MemberDAO.insert(user);

	                request.setAttribute("success", "회원이 되신 것을 환영합니다.");
	                
	                System.out.println("회원가입 성공");
	                System.out.println("생성된 회원 객체 : " + user);
	                return "index.jsp"; 
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "[가입 실패] 입력 정보를 다시 한번 확인해주세요.");
	        return "error.jsp"; 
	    }
	}
}
