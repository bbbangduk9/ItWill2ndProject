package com.postlounge.model.vo;

import java.sql.Date;

public class PostVO {
	private int postIdx;
	private String title;
	private String postContent;
	private int hit;
	private Date postDate;
	private String postType;
	private int memberIdx;
	private int commentIdx;
	private String postFileName;
	private String postOriName;
	
	//이하영 추가
	private int newPostAll; // 오늘 업로드된 전체 게시글 수를 받기 위한 변수
	private int newFoodAll; // 오늘 업로드된 푸드 카테고리의 게시글 수를 받기 위한 변수
	private int newHealthAll; // 오늘 업로드된 건강 카테고리의 게시글 수를 받기 위한 변수
	private int newMusicAll; // 오늘 업로드된 음악 카테고리의 게시글 수를 받기 위한 변수
	private int newSportAll; // 오늘 업로드된 스포츠 카테고리의 게시글 수를 받기 위한 변수
	private int newMovieAll; // 오늘 업로드된 무비 카테고리의 게시글 수를 받기 위한 변수
	
	private String nickname; // 게시글 작성자의 별명을 받기 위한 변수
	
	public PostVO() {}

	public PostVO(int postIdx, String title, String postContent, int hit, Date postDate, String postType, int memberIdx,
			int commentIdx, String postFileName, String postOriName) {
		this.postIdx = postIdx;
		this.title = title;
		this.postContent = postContent;
		this.hit = hit;
		this.postDate = postDate;
		this.postType = postType;
		this.memberIdx = memberIdx;
		this.commentIdx = commentIdx;
		this.postFileName = postFileName;
		this.postOriName = postOriName;
	}

	public int getNewPostAll() {
		return newPostAll;
	}

	public void setNewPostAll(int newPostAll) {
		this.newPostAll = newPostAll;
	}

	public int getNewFoodAll() {
		return newFoodAll;
	}

	public void setNewFoodAll(int newFoodAll) {
		this.newFoodAll = newFoodAll;
	}

	public int getNewHealthAll() {
		return newHealthAll;
	}

	public void setNewHealthAll(int newHealthAll) {
		this.newHealthAll = newHealthAll;
	}

	public int getNewMusicAll() {
		return newMusicAll;
	}

	public void setNewMusicAll(int newMusicAll) {
		this.newMusicAll = newMusicAll;
	}

	public int getNewSportAll() {
		return newSportAll;
	}

	public void setNewSportAll(int newSportAll) {
		this.newSportAll = newSportAll;
	}

	public int getNewMovieAll() {
		return newMovieAll;
	}

	public void setNewMovieAll(int newMovieAll) {
		this.newMovieAll = newMovieAll;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPostIdx() {
		return postIdx;
	}

	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public int getCommentIdx() {
		return commentIdx;
	}

	public void setCommentIdx(int commentIdx) {
		this.commentIdx = commentIdx;
	}

	public String getPostFileName() {
		return postFileName;
	}

	public void setPostFileName(String postFileName) {
		this.postFileName = postFileName;
	}

	public String getPostOriName() {
		return postOriName;
	}

	public void setPostOriName(String postOriName) {
		this.postOriName = postOriName;
	}

	@Override
	public String toString() {
		return "PostVO [postIdx=" + postIdx + ", title=" + title
				+ ", postContent=" + postContent + ", hit=" + hit + ", postDate=" + postDate + ", postType=" + postType
				+ ", memberIdx=" + memberIdx + ", commentIdx=" + commentIdx + ", postFileName=" + postFileName
				+ ", postOriName=" + postOriName + ", newPostAll=" + newPostAll + ", newFoodAll=" + newFoodAll
				+ ", newHealthAll=" + newHealthAll + ", newMusicAll=" + newMusicAll + ", newSportAll=" + newSportAll
				+ ", newMovieAll=" + newMovieAll + ", nickname=" + nickname +  "]";
	}

}
