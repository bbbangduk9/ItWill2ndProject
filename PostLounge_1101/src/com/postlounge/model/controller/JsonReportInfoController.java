package com.postlounge.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.CommentDAO;
import com.postlounge.model.vo.AllMixVO;

@WebServlet("/POSTLOUNGE/reportInfo")
public class JsonReportInfoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("JsonReportInfoController _ doPost() 실행 ");
        response.setContentType("text/html; charset=UTF-8");
        int commentIdx = 0;
        
        
        try {
        	commentIdx = Integer.parseInt( request.getParameter("commentIdx"));
        	System.out.println("commentIdx : " + commentIdx);
        	
        	AllMixVO commentInfo = CommentDAO.commInfo(commentIdx);
        	System.out.println("commentInfo : " + commentInfo);
        	
        	String commContent = commentInfo.getCommContent();
        	String nickname = commentInfo.getNickname();
        	Date commDate = commentInfo.getCommDate();
        	String name = commentInfo.getName();
            // 텍스트 응답 생성
            String responseText = commContent + '|' + nickname + '|' + commDate + '|' + name;
            
            // 텍스트 응답을 클라이언트로 전송
            PrintWriter out = response.getWriter();
            out.println(responseText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





