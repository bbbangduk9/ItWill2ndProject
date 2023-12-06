package com.postlounge.model.vo;

import java.sql.Date;

public class AllMixVO {
	private int commentIdx;
	private int memberIdx;
	private int postIdx;
	private String commContent;
	private Date commDate;

	private int friendNum;

	private int likeNum;

	private String name;
	private String id;
	private String pwd;
	private String nickname;

	private String title;
	private String postContent;
	private int hit;
	private Date postDate;
	private String postType;
	private String postFileName;
	private String postOriName;

	public AllMixVO() {
	}

	public int getCommentIdx() {
		return commentIdx;
	}

	public void setCommentIdx(int commentIdx) {
		this.commentIdx = commentIdx;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public int getPostIdx() {
		return postIdx;
	}

	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public Date getCommDate() {
		return commDate;
	}

	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}

	public int getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(int friendNum) {
		this.friendNum = friendNum;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
		return "AllMixVO [commentIdx=" + commentIdx + ", memberIdx=" + memberIdx + ", postIdx=" + postIdx
				+ ", commContent=" + commContent + ", commDate=" + commDate + ", friendNum=" + friendNum + ", likeNum="
				+ likeNum + ", name=" + name + ", id=" + id + ", pwd=" + pwd + ", nickname=" + nickname + ", title="
				+ title + ", postContent=" + postContent + ", hit=" + hit + ", postDate=" + postDate + ", postType="
				+ postType + ", postFileName=" + postFileName + ", postOriName=" + postOriName + "]";
	}
}
