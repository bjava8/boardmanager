package com.mystudy.board.vo;


public class ReplyVO {
	private int rep_no; //댓글번호
	private String id; //댓글을 쓴 유저 아이디
	private String content; //댓글 내용
	private String regdate; //날짜
	private int bbs_no; //댓글이 있는 글 번호
	
	public ReplyVO() {
		super();
	}
	
	public ReplyVO(int rep_no, String id, String content, String regdate, int bbs_no) { //입력 생성자
		super();
		this.rep_no = rep_no;
		this.id = id;
		this.content = content;
		this.regdate = regdate;
		this.bbs_no = bbs_no;
	}
	
	public ReplyVO(int rep_no, String id, String content, int bbs_no) {//검색 생성자
		super();
		this.rep_no = rep_no;
		this.id = id;
		this.content = content;
		this.bbs_no = bbs_no;
	}
	
	public ReplyVO(String content, int rep_no, String id, int bbs_no) {//수정 생성자
		super();
		this.content = content;
		this.rep_no = rep_no;
		this.id = id;
		this.bbs_no = bbs_no;
	}
	
	public ReplyVO(int rep_no, String id, int bbs_no) {//삭제 생성자
		super();
		this.rep_no = rep_no;
		this.id = id;
		this.bbs_no = bbs_no;
	}

	public int getRep_no() {
		return rep_no;
	}
	public void setRep_no(int rep_no) {
		this.rep_no = rep_no;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public int getBbs_no() {
		return bbs_no;
	}
	public void setBbs_no(int bbs_no) {
		this.bbs_no = bbs_no;
	}

	@Override
	public String toString() {
		return rep_no + " | " + id + " | " + content + " | " + regdate;
	}
	
	

}
