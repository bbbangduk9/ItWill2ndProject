package com.postlounge.model.vo;

public class FriendVO {
	private int friendNum;
	private int memberIdx;
	private int friendIdx;

	public FriendVO() {}

	public FriendVO(int memberIdx, int friendIdx) {
		super();
		this.memberIdx = memberIdx;
		this.friendIdx = friendIdx;
	}

	public FriendVO(int friendNum, int memberIdx, int friendIdx) {
		super();
		this.friendNum = friendNum;
		this.memberIdx = memberIdx;
		this.friendIdx = friendIdx;
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

	@Override
	public String toString() {
		return "FriendVO [friendNum=" + friendNum + ", memberIdx=" + memberIdx + ", friendIdx=" + friendIdx + "]";
	}

}
