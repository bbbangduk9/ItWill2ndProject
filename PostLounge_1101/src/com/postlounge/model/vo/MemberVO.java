package com.postlounge.model.vo;

import java.sql.Date;

public class MemberVO {
	private int memberIdx;
	private String name;
	private String id;
	private String pwd;
	private String nickname;
	private String memberOriName;
	private String memberFileName;
	private int isDel; //관리자페이지 로그인과 일반 로그인 페이지 달라서 isAdmin 변수 삭제 (이하영)
	private Date joinDate;
	private int isAdmin;
	
	public MemberVO() {}

	public MemberVO(int memberIdx, String name, String id, String pwd, String nickname, String memberFileName,
			String memberOriName, int isDel) {
		this.memberIdx = memberIdx;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.nickname = nickname;
		this.memberFileName = memberFileName;
		this.memberOriName = memberOriName;
		this.isDel = isDel;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
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

	public String getMemberFileName() {
		return memberFileName;
	}

	public void setMemberFileName(String memberFileName) {
		this.memberFileName = memberFileName;
	}

	public String getMemberOriName() {
		return memberOriName;
	}

	public void setMemberOriName(String memberOriName) {
		this.memberOriName = memberOriName;
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

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "MemberVO [memberIdx=" + memberIdx + ", name=" + name + ", id=" + id + ", pwd=" + pwd + ", nickname="
				+ nickname + ", memberOriName=" + memberOriName + ", memberFileName=" + memberFileName + ", isDel="
				+ isDel + ", joinDate=" + joinDate + ", isAdmin=" + isAdmin + "]";
	}
	
	

}