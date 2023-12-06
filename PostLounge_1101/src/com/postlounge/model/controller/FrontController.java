package com.postlounge.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postlounge.model.command.BestCommand;
import com.postlounge.model.command.Command;
import com.postlounge.model.command.CommentDeleteCommand;
import com.postlounge.model.command.CommentInsertCommand;
import com.postlounge.model.command.CommentSelectCommand;
import com.postlounge.model.command.CommentUpdateCommand;
import com.postlounge.model.command.FeedCommand;
import com.postlounge.model.command.FeedPostDetailCommand;
import com.postlounge.model.command.FindMyIdCommand;
import com.postlounge.model.command.FindMyPwdCommand;
import com.postlounge.model.command.FollowCommand;
import com.postlounge.model.command.FollowDeleteCommand;
import com.postlounge.model.command.FollowerListCommand;
import com.postlounge.model.command.FollowingListCommand;
import com.postlounge.model.command.JoinCommand;
import com.postlounge.model.command.JoinCommand_ori;
import com.postlounge.model.command.LoginCommand;
import com.postlounge.model.command.MainPostDetailCommand;
import com.postlounge.model.command.MyCommand;
import com.postlounge.model.command.MyPostCommand;
import com.postlounge.model.command.ProfileInfoCommand;
import com.postlounge.model.command.SearchCommand;
import com.postlounge.model.command.UploadProfileCommand;

@WebServlet("/POSTLOUNGE/controller")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println("받은 type : " + type);

		Command command = null;

		if ("search".equals(type)) {
			command = new SearchCommand();
		}
		if ("join".equals(type)) {
			command = new JoinCommand();
		}
		if ("login".equals(type)) {
			command = new LoginCommand();
		}
		if ("best".equals(type)) {
			command = new BestCommand();
		}
		if ("feed".equals(type)) {
			command = new FeedCommand(); // 메인에서 피드 클릭시
		}
		if ("comment".equals(type)) {
			command = new CommentInsertCommand(); // 댓글 생성
		}
		if ("selectComment".equals(type)) {
			command = new CommentSelectCommand(); // 댓글 조회
		}
		if ("updateComment".equals(type)) {
			command = new CommentUpdateCommand();
		}
		if ("deleteComment".equals(type)) {
			command = new CommentDeleteCommand();
		}
		if ("detail".equals(type)) { //피드에서 게시글 상세보기한 경우
			command = new FeedPostDetailCommand();
		}
		if ("detailMain".equals(type)) { //메인에서 게시글 상세보기한 경우
			command = new MainPostDetailCommand();
		}
		if ("follow".equals(type)) { //팔로우 추가
			command = new FollowCommand();
		}
		if ("deleteFollow".equals(type)) {
			command = new FollowDeleteCommand(); //팔로우 삭제
		}
		if ("followingList".equals(type)) { //피드에서 팔로잉 옆에 숫자 클릭한 경우(내 팔로우 목록 조회)
			command = new FollowingListCommand();
		}
		if ("followerList".equals(type)) { //(추가)피드에서 팔로워 옆에 숫자 클릭한 경우
			command = new FollowerListCommand();
		}
		if ("myPostList".equals(type)) {
			command = new MyPostCommand();
		}
		if ("profileInfo".equals(type)) {
			command = new ProfileInfoCommand(); // 프로필 정보 조회
		}
		if ("profileEdit".equals(type)) {
			command = new UploadProfileCommand(); // 프로필 정보 조회
		}
		if ("findMyId".equals(type)) {
			command = new FindMyIdCommand();
		}
		if ("findMyPwd".equals(type)) {
			command = new FindMyPwdCommand();
		}

		String path = command.exec(request, response);
		request.getRequestDispatcher(path).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(">> FrontControllerCommand doPost() 실행~~~~");
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);

	}
}
