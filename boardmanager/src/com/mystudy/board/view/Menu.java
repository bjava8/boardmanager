package com.mystudy.board.view;

import com.mystudy.board.common.Util;
import com.mystudy.board.vo.BoardVO;
import com.mystudy.board.vo.MemberVO;

public class Menu {

	public static MemberVO session;
	BoardView bv;
	ReplyView rv;
	MemberView mv;
	
	public Menu() {
		session = null;
		
		mv = new MemberView();
		bv = new BoardView();
		rv = new ReplyView();
	}
	
	public void start() {
		Util.printLine();
		System.out.println("프로그램 시작");
		Util.printLine();
		
		// 1. 회원 선택
		while (true) {
			System.out.println("1. 로그인 2. 회원가입 3. 비회원");

			String sel = Util.input("선택>"); // 입력
			Util.printLine();
			
			if (sel.equals("1")) {			// 1. 로그인
				// 로그인
				
				MemberVO vo = mv.selectOne();
				// 로그인 성공 true = session 값 넣어줌
				
				session = vo;				
				
				// 로그인 성공
				if (session != null) {
					break;
				}
				
				// 로그인 실패 
				
			} else if (sel.equals("2")) {	// 2. 회원가입
				// 회원가입
				mv.insert();
				
			} else if (sel.equals("3")) {	// 3. 비회원
				break;
			}
		}
		
		// 2. 게시판 선택
		while (true) {
			
			if (session != null) {
				Util.printLine();
				System.out.println("회원정보 ID : " + session.getId() + " | 이름 : " + session.getName());
				Util.printLine();
				System.out.println("1. 목록 2. 글검색 3. 댓글검색 4. 상세 5. 글쓰기 0. 종료");
			} else {
				System.out.println("1. 목록 2. 글검색 3. 댓글검색 0. 종료");
			}

			String bsel = Util.input("선택>"); // 입력
			
			if (bsel.equals("1")) {	// 1. 목록
				bv.selectAll();
			} else if (bsel.equals("2")) { // 2. 글검색	
				bv.search();
			} else if (bsel.equals("3")) { // 3. 댓글검색
				rv.search();
			} else if (bsel.equals("4")) { // 4. 상세
				int bno = bv.selectCount();
				if (bno != 0) { // 글이 있으면 상세 메뉴로 들어가고
					String dsel = detail(bno);
					if (dsel.equals("0")) {
						break;
					}
				} 
				
			} else if (bsel.equals("5")) { // 5. 글쓰기
				bv.insert();
				System.out.println("글쓰기 종료");
			} else if (bsel.equals("0")) { // 0. 종료
				break;
			}
		}
		Util.printLine();
		System.out.println("프로그램 종료");
		Util.printLine();
	} // start()
	
	// 3. 상세 선택
	public String detail(int bno) {
		
		String sel = ""; 
		
		while (true) {
			Util.printLine();
			System.out.println("회원정보 ID : " + session.getId() + " | 이름 : " + session.getName());
			Util.printLine();
			
			//bno = bv.selectOne(bno);
			BoardVO vo = bv.selectDetail(bno);
			//if (bno == 0) break;
			if (vo == null) break;
			
			System.out.println("1. 글수정 2. 글삭제 3. 댓글쓰기 4. 댓글수정 5. 댓글삭제 6.목록 0. 종료");
			
			sel = Util.input("선택>"); // 입력
			
			if (sel.equals("1")) {	// 1. 글수정
				bv.update(bno);
			} else if (sel.equals("2")) {	// 2. 글삭제
				bv.delete(vo);
				break;
			} else if (sel.equals("3")) { // 3. 댓글쓰기
				rv.insert();
			} else if (sel.equals("4")) { // 4. 댓글수정
				rv.update();
			} else if (sel.equals("5")) { // 5. 댓글삭제
				rv.delete();
			} else if (sel.equals("6")) { // 6. 목록
				break;
			} else if (sel.equals("0")) { // 0. 종료
				break;
			}
		}
		
		return sel;
	}
}
