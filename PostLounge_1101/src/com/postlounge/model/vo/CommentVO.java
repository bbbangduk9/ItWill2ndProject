package com.postlounge.model.vo;

import java.sql.Date;

public class CommentVO {
	private int commentIdx;
	private int memberIdx;
	private int postIdx;
	private String commContent;
	private Date commDate;
	
	private String nickname; // 댓글 작성자의 닉네임을 위함 (이하영 추가)

	public CommentVO() {
	}

	public CommentVO(int commentIdx, int memberIdx, int postIdx, String commContent, Date commDate) {
		super();
		this.commentIdx = commentIdx;
		this.memberIdx = memberIdx;
		this.postIdx = postIdx;
		this.commContent = commContent;
		this.commDate = commDate;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "CommentVO [commentIdx=" + commentIdx + ", memberIdx=" + memberIdx + ", postIdx=" + postIdx
				+ ", commContent=" + commContent + ", commDate=" + commDate + ", nickname=" + nickname + "]";
	}
	
	

}