package com.mystudy.board.controller;

import java.util.ArrayList;

import com.mystudy.board.dao.ReplyDAO;
import com.mystudy.board.vo.ReplyVO;

public class ReplyController {
	
	ReplyDAO dao;
	
	public ReplyController() {
		dao = new ReplyDAO();
	}
	
	// 댓글 목록보기
	public ArrayList<ReplyVO> repAll(int bno) {
		ArrayList<ReplyVO> list = dao.repAll(bno);
		
		return list;
	}
	
	// 댓글쓰기
	public int repInsert(ReplyVO vo) {
		int result = dao.repInsert(vo);
		return result;
	}
	
	// 댓글수정
	public int repUpdate(ReplyVO vo) {
		// 댓글 ID랑 로그인 ID 비교 일치할 때만 update
		
		int result = dao.repUpdate(vo);
		return result;
	}
	// 댓글삭제
	public void repDelete() {
		// TODO Auto-generated method stub
		
	}
	
	// 댓글 검색
	public ArrayList<ReplyVO> repSearch(String content) {
		ArrayList<ReplyVO> list = dao.repSearch(content);
		return list;
	}
}
