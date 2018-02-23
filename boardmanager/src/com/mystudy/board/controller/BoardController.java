package com.mystudy.board.controller;

import java.util.ArrayList;

import com.mystudy.board.dao.BoardDAO;
import com.mystudy.board.dao.ReplyDAO;
import com.mystudy.board.view.Menu;
import com.mystudy.board.vo.BoardVO;
import com.mystudy.board.vo.ReplyVO;

public class BoardController {
	BoardDAO dao;
	
	public BoardController() {
		dao = new BoardDAO();
	}
	
	// 목록보기
	public ArrayList<BoardVO> selectAll() {
		
		ArrayList<BoardVO> list = dao.selectAll();
		/*
		if (list == null) {
			list = new ArrayList<BoardVO>();
		}
		*/
		return list;
	}
	
	// 글 존재 확인
	public BoardVO selectCount(int bno) {
		BoardVO bvo = dao.selectOne(bno); // 1. 상세보기

		return bvo;
	}
	
	// 상세보기
	public BoardVO selectOne(int bno) {
		BoardVO bvo = dao.selectOne(bno); // 1. 상세보기 - 글 읽고
		
		if (bvo != null) {	// 2. 조회수+1
			int hits = dao.selectHits(bno); // 조회수 가져오기
			dao.updateHits(++hits, bno); // 조회수 증가
			bvo.setHits(hits); // 현재글에 증가된 조회수 넣기 
		}
		
		return bvo;
	}
	
	// 검색
	public ArrayList<BoardVO> search(String col, String text) {
		
		if (col.equals("1")) {
			col = "subject";
		} else if (col.equals("2")) {
			col = "content";
		} else if (col.equals("3")) {
			col = "id";
		}
		
		ArrayList<BoardVO> list = dao.search(col, text);
		/*
		if (list == null) {
			list = new ArrayList<BoardVO>();
		}
		*/
		return list;
	}

	// 삭제
	public int delete(int bno) {
		// id 비교
		
		int result = 0;

		BoardVO vo = dao.selectOne(bno);
		System.out.println(vo);

		return result;
	}

	// 입력
	public int insert(BoardVO vo) {
		int result = dao.write(vo);
		return result;
	}
	
	// 글추천
	public int recommend(int bno) {
		int rcm = dao.selectRecommend(bno); 
		
		int result = dao.updateRecommend(++rcm, bno);
		return result;
	}
	
	public int update(BoardVO vo) {
		
		//int dao.update(vo);
		return 0;
		
	}

	// 삭제 - id비교추가
	public int delete(BoardVO vo) {
		int result = dao.delete(vo);

		return result;
	}
	
}