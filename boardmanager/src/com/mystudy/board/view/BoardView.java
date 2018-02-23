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
		
		ArrayList<BoardVO> list = cont.selectAll();
		
		if (!list.isEmpty()) {
			Util.printList(list);
		} else {
			System.out.println("글 없음");
		}
		
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

			int allCnt = list.size();
			if (0 < allCnt) {
				System.out.println(allCnt + "개의 댓글이 조회되었습니다.");
				//System.out.println(list);
			}
			else {
				System.out.println("조회된 댓글이 없습니다.");
			}
			
			for (ReplyVO replyVO : list) {
				System.out.println(replyVO);	// 댓글 출력
			}

		} else {	// 없는 글번호면 0으로 초기화
			System.out.println("해당 번호의 글이 존재하지 않습니다.");
			bno = 0;
		}
		
		return bno;
	}
	
	// 상세2
	public BoardVO selectDetail(int bno) {

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
		
		return bvo;
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
		// 제목 입력
		String subject = Util.input("제목>");
		// 내용 입력
		String content = Util.input("내용>");
		
		BoardVO vo = new BoardVO();
		
		
		int result = cont.update(vo);
		//수정 성공
		//실패
				
		
	}
	
	// 글삭제
	/*
	public void delete(int bno) {
		System.out.println("글삭제");
		
		String id = Menu.session.getId();
		System.out.println("Menu.session.getId() = " + id);
		
		// 삭제하려는 사람 아이디가 작성자 아이디랑 같으면 삭제
				// 아니면 안삭제
		
		if (id.equals(vo.getId())) {
			result = dao.remove(bno);
		}
		
		int result = cont.delete(bno);
		
		if (result == 1) {
			System.out.println(bno + "번 글이 삭제되었습니다.");
		} else {
			System.out.println(bno + "번 글이 삭제 실패");
		}
		
	}
	*/
	
	public void delete(BoardVO vo) {
		System.out.println("글삭제");
		
		String id = Menu.session.getId();
		System.out.println("Menu.session.getId() = " + id);
		
		// 삭제하려는 사람 아이디가 작성자 아이디랑 같으면 삭제
		// 아니면 안삭제
		/*
		if (id.equals(vo.getId())) {
			result = dao.remove(bno);
		}
		*/
		vo.setId(id); // 로그인한 사람 아이디로 셋
		
		int result = cont.delete(vo);
		
		if (result == 1) {
			System.out.println(vo.getBbs_no() + "번 글이 삭제되었습니다.");
		} else {
			System.out.println(vo.getBbs_no() + "번 글이 삭제 실패");
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
