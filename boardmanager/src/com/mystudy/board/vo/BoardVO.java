package com.mystudy.board.vo;

public class BoardVO {
	private int bbs_no;
	private String id;
	private String subject;
	private String content;
	private String regdate;
	private int hits;
	private int recommend;
	
	public int getBbs_no() {
		return bbs_no;
	}
	public void setBbs_no(int bbs_no) {
		this.bbs_no = bbs_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bbs_no=" + bbs_no + ", id=" + id + ", subject=" + subject + ", content=" + content
				+ ", regdate=" + regdate + ", hits=" + hits + ", recommend=" + recommend + "]";
	}
}