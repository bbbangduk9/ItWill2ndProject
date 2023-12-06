package com.postlounge.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.PostDAO;
import com.postlounge.model.vo.PostVO;

//2 URL 쓴다 ex: "/fgasdfjsd"
@WebServlet("/office/getSportAll")
//1. extends HttpServlet !!
public class GetSportAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//3. doGet 메소드를 만든다. 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: GetMusicAll doGet() 실행됨 ");
		//한글깨짐 방지를 위한 문자타입(UTF-8) 처리
		response.setContentType("text/html;charset=UTF-8");
		
		//DB 데이터 가져오기 (DAO에 있는 메소드를 VO에 저장한다)
		List<PostVO> list = PostDAO.getPostSport();
		System.out.println("list : " + list);
		
		//JSON 형식 문자열 만들고 응답처리
		// {"postList" : [{}, {}, ... {}] }
		String result = makeJson(list);
		
		//클라이언트에게 응답처리
		PrintWriter out = response.getWriter();
		out.print(result);

	}

	private String makeJson(List<PostVO> list) {
		/* {"postList" :[{}, {}, ..., {}] }
		{ "postList" : [
			{
				"postIdx" : 1,
				"title" : "제목샘플",
				"nickName" : "홍길"
				"postDate" : 2023-10-19,
				"hit" : 23,
				"likeNum" : 12
			},
			{}, {}, ..., {}
		]}
		**************************************/
		
		//JSON 형식 문자열 만들기
		StringBuilder result = new StringBuilder();
		result.append("{ \"list\" : [");
		
		for (PostVO post : list) {
			result.append("{");
			result.append("\"postIdx\": " + post.getPostIdx() + ",");
			result.append("\"title\": \"" + post.getTitle() + "\",");
			result.append("\"nickname\": \"" + post.getNickname() + "\",");
			result.append("\"postDate\": \"" + post.getPostDate() + "\",");
			result.append("\"hit\": " + post.getHit());
			result.append("},");
		}
		
	    if (!list.isEmpty()) {
	        result.deleteCharAt(result.length() - 1);
	    }
	    
		result.append("]}");
		
		System.out.println(result.toString());
		
		return result.toString();
	}
	
	

}
