package com.postlounge.model.vo;

public class BbsVO {
	private String bbsIdx, subject, writer, content, fileName,
			oriName, pwd, writeDate, ip;
	private int hit;
	
	
	public BbsVO() {}

	public BbsVO(String bbsIdx, String subject, String writer, String content, String fileName, String oriName,
			String pwd, String writeDate, String ip, int hit) {
		super();
		this.bbsIdx = bbsIdx;
		this.subject = subject;
		this.writer = writer;
		this.content = content;
		this.fileName = fileName;
		this.oriName = oriName;
		this.pwd = pwd;
		this.writeDate = writeDate;
		this.ip = ip;
		this.hit = hit;
	}

	public String getBbsIdx() {
		return bbsIdx;
	}

	public void setBbsIdx(String bbsIdx) {
		this.bbsIdx = bbsIdx;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName) {
		this.oriName = oriName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "BbsVO [bbsIdx=" + bbsIdx + ", subject=" + subject + ", writer=" + writer + ", content=" + content
				+ ", fileName=" + fileName + ", oriName=" + oriName + ", pwd=" + pwd + ", writeDate=" + writeDate
				+ ", ip=" + ip + ", hit=" + hit + "]";
	}

}

