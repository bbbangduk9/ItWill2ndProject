package com.postlounge.model.vo;

import java.sql.Date;

public class PostListVO {
	private int postIdx;
	private String title;
	private String postContent;
	private int hit;
	private Date postDate;
	private String postType;
	private int memberIdx;
	private int commentIdx;
	private String commContent;
	private String nickname;
	private int rnum; //페이징 처리를 위해 추가 (이초희)

	private String id;
	private int isDel;
	
	private Date joinDate;
	private Date commDate;
	
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
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

	public String getnickname() {
		return nickname;
	}

	public void setnickname(String name) {
		this.nickname = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getCommDate() {
		return commDate;
	}

	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}

	@Override
	public String toString() {
		return "PostListVO [postIdx=" + postIdx + ", title=" + title + ", postContent=" + postContent + ", hit=" + hit
				+ ", postDate=" + postDate + ", postType=" + postType + ", memberIdx=" + memberIdx + ", commentIdx="
				+ commentIdx + ", commContent=" + commContent + ", nickname=" + nickname + ", rnum=" + rnum + ", id="
				+ id + ", isDel=" + isDel + ", joinDate=" + joinDate + ", commDate=" + commDate + "]";
	}

	
}
