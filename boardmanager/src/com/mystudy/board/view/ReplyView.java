package com.mystudy.board.view;

import java.util.Scanner;

import com.mystudy.board.controller.ReplyController;
import com.mystudy.board.vo.ReplyVO;

public class ReplyView {
	
	ReplyController replyCont;
	
	public ReplyView() {
		replyCont = new ReplyController();
	}

	// 댓글 검색
	public void search() {
		Scanner scan = new Scanner(System.in);
		System.out.println("댓글 검색");
		System.out.print("댓글을 검색할 단어를 입력해 주세요 : ");
		String content = scan.next();
		replyCont.repSearch(content);
	}

	// 댓글 쓰기
	public void insert() {
		
		System.out.println("댓글 쓰기");	
		Scanner scan = new Scanner(System.in);

		System.out.print("작성자 아이디를 입력해주세요 : ");
		String id = scan.next();
		System.out.print("댓글 내용을 입력해 주세요 : ");
		String content = scan.next();
		System.out.print("댓글이 달릴 글 번호를 입력해 주세요 : ");
		int bbs_no = scan.nextInt();		
		ReplyVO vo = new ReplyVO();
		vo.setBbs_no(bbs_no);
		vo.setContent(content);
		
		replyCont.repInsert(vo);
		
	}
	
	// 댓글수정
	public void update() {
		System.out.println("댓글수정");
		replyCont.repUpdate();
	}
	
	// 댓글삭제
	public void delete() {
		System.out.println("댓글삭제");
		replyCont.repDelete();
	}
}
