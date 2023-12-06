package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	// 리턴 받는 문자열은 최종 응답페이지.....
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
