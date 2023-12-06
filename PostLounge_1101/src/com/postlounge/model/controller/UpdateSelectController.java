package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.command.Command;
import com.postlounge.model.command.UpdateSelectCommand;

@WebServlet("/POSTLOUNGE/updateSelect")
public class UpdateSelectController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Command command = new UpdateSelectCommand();
		//System.out.println("UpdateSelectCommand : ");
        String view = command.exec(req, resp);
        System.out.println("updateSelect view : " + view);
        
        if (view != null) {
        	req.getRequestDispatcher(view).forward(req, resp);
        } else {
            // 에러 처리
        	resp.sendRedirect("error.jsp");
        }

	}
	
	
	
}
