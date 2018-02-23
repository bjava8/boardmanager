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
	public ArrayList<BoardVO> viewList() {
		
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
		String id = Menu.session.getId();
		int result = 0;
		
		System.out.println("Menu.session.getId() = " + id);
		
		
		BoardVO vo = dao.selectOne(bno);
		System.out.println(vo);
		// 삭제하려는 사람 아이디가 작성자 아이디랑 같으면 삭제
		// 아니면 안삭제
		if (id.equals(vo.getId())) {
			result = dao.remove(bno);
		}
		
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
	
}