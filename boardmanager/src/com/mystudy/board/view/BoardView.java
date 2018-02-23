package com.mystudy.board.view;

import java.util.ArrayList;

import com.mystudy.board.common.Util;
import com.mystudy.board.controller.BoardController;
import com.mystudy.board.controller.ReplyController;
import com.mystudy.board.vo.BoardVO;
import com.mystudy.board.vo.ReplyVO;


public class BoardView {
	
	BoardController cont;
	ReplyController replyCont;
	
	public BoardView() {
		cont = new BoardController();
		replyCont = new ReplyController();
	}
	
	// 목록
	public void selectAll() {
		System.out.println("목록");
	}
	
	// 글번호 입력, 글존재확인
	public int selectCount() {
		System.out.println("상세");
		// 글번호(bbs_no) 입력 받기
		int bno = Util.inputNum();
		
		BoardVO bvo = cont.selectCount(bno);

		/*
		if (bvo != null) {
			System.out.println(bvo);	// 출력
		} else {	// 없는 글번호면 0으로 초기화
			System.out.println("해당 번호의 글이 존재하지 않습니다.");
			bno = 0;
		}
		*/
		if (bvo == null) {	// 없는 글번호면 0으로 초기화
			System.out.println("해당 번호의 글이 존재하지 않습니다.");
			bno = 0;
		}
		return bno;
	}
	
	// 상세
	public int selectOne(int bno) {

		BoardVO bvo = cont.selectOne(bno);

		if (bvo != null) {
			
			//System.out.println(bvo);	// 출력
			Util.printBoardVO(bvo); // 글 출력
			
			// 댓글
			System.out.println("댓글");
			ArrayList<ReplyVO> list = replyCont.repAll(bno); // 댓글 목록 

			for (ReplyVO replyVO : list) {
				System.out.println(replyVO);
			}

		} else {	// 없는 글번호면 0으로 초기화
			System.out.println("해당 번호의 글이 존재하지 않습니다.");
			bno = 0;
		}
		
		return bno;
	}
	
	// 게시판 검색
	public void search() {
		Util.printLine();
		System.out.println("게시판 검색");
		Util.printLine();
		
		// 제목, 내용, 작성자 선택(입력)
		String col = Util.input("1-제목, 2-내용, 3-작성자 번호 입력>");
		
		// 검색 메뉴 선택지 입력값 체크
		if (!col.equals("1") && !col.equals("2") && !col.equals("3")) {
			System.out.println("[입력오류] 없는 메뉴 - 검색 강제종료");
			return;
		}
		
		// 검색어 입력
		String text = Util.input("검색어 입력>"); 
		
		// 검색어값 체크
		if (text.equals("")) {
			System.out.println("[입력오류] 검색어 미입력 - 검색 강제종료");
			return;
		}
		
		ArrayList<BoardVO> list = cont.search(col, text);
		
		if (!list.isEmpty()) {
			Util.printList(list);
		} else {
			System.out.println("검색 결과 없음");
		}
	}

	
	// 글쓰기
	public void insert() {
		System.out.println(Menu.session.getId());
		System.out.println("Start : 글쓰기");
		//제목 입력
		String subject = Util.input("제목 입력>");
		//내용 입력
		String content = Util.input("내용 입력>");
		//boolean check = 
		// 1. 입력값 체크
		if (subject == null ) {
			System.out.println("1제목이 입력되지 않았습니다.");
		} 
		
		if (subject.equals("")) {
			System.out.println("2제목이 입력되지 않았습니다.");
			return;
		} 
		
		if ("".equals(subject)) {
			System.out.println("3제목이 입력되지 않았습니다.");
			
		} 
		/*
		else if (con == null) {
			System.out.println("내용이 입력되지 않았습니다.");
		} else {
			//System.out.println(bvo);
		}
		*/
		
		BoardVO bvo = new BoardVO();
		
		bvo.setId(Menu.session.getId());
		bvo.setSubject(subject);
		bvo.setContent(content);
		
		int result = cont.insert(bvo);
		
		if (result == 1) {
			System.out.println("result = "+ result + " : 입력 완료");
		} else {
			System.out.println("result = "+ result + " : 입력 실패");
		}
		
		System.out.println("BoardView : End 글쓰기");
	}
	
	// 글수정
	public void update(int bno) {
		System.out.println("글수정");
	}
	
	// 글삭제
	public void delete(int bno) {
		System.out.println("글삭제");
		int result = cont.delete(bno);
		
		if (result == 1) {
			System.out.println(bno + "번 글이 삭제되었습니다.");
		} else {
			System.out.println(bno + "번 글이 삭제 실패");
		}
		
	}
	
	// 글추천
	public void recommend(int bno) {
		int result = cont.recommend(bno);
		
		if (result == 1) {
			System.out.println("글이 추천되었습니다.");
		} else {
			System.out.println("추천 실패.");
		}
	}
	
}
