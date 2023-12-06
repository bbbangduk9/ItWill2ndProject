package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.dao.CommentDAO;


@WebServlet("/POSTLOUNGE/JsonDeleteCommentController")
public class JsonDeleteCommentController extends HttpServlet {

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("JsonDeleteCommentController _ doPost() 실행 ");
        response.setContentType("text/html; charset=UTF-8");

        try {
            int commentIdx = Integer.parseInt(request.getParameter("commentIdx"));
            System.out.println("commentIDx " + commentIdx);
            int result = CommentDAO.deleteComment(commentIdx);
            System.out.println("result " + result);
            
            
            if (result > 0) {
                response.getWriter().write("댓글이 잘 삭제됨"); //ajax와 맞아야함
            } else {
                response.getWriter().write("댓글 삭제에 실~패했음");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("댓글 삭제 중 오류가 발생");
        }
    }
}