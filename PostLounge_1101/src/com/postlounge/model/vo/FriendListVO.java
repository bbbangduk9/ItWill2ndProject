package com.postlounge.model.vo;

public class FriendListVO {
	private int friendNum;
	private int memberIdx;
	private int friendIdx;
	private String name;
	private String id;
	private String pwd;
	private String nickname;
	private String memberFileName;
	private String memberOriName;
	private int rnum; //페이징 처리를 위해 추가 (이초희)

	public FriendListVO() {}

	public FriendListVO(int friendNum, int memberIdx, int friendIdx, String name, String id, String pwd,
			String nickname, String memberFileName, String memberOriName, int rnum) {
		this.friendNum = friendNum;
		this.memberIdx = memberIdx;
		this.friendIdx = friendIdx;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.nickname = nickname;
		this.memberFileName = memberFileName;
		this.memberOriName = memberOriName;
		this.rnum = rnum;
	}
	
	public int getRnum() {
		return rnum;
	}
	
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(int friendNum) {
		this.friendNum = friendNum;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public int getFriendIdx() {
		return friendIdx;
	}

	public void setFriendIdx(int friendIdx) {
		this.friendIdx = friendIdx;
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

	@Override
	public String toString() {
		return "FriendListVO [friendNum=" + friendNum + ", memberIdx=" + memberIdx + ", friendIdx=" + friendIdx
				+ ", name=" + name + ", id=" + id + ", pwd=" + pwd + ", nickname=" + nickname + ", memberFileName="
				+ memberFileName + ", memberOriName=" + memberOriName + ", rnum=" + rnum + "]";
	}

}
