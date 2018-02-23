package com.mystudy.board.view;

import com.mystudy.board.vo.MemberVO;

public class MemberView  {
	// 로그인
	public MemberVO selectOne() {
		System.out.println("로그인");
		return new MemberVO("hong", "2348", "홍길동", "010-7744-5679");
		
	}
	
	// 회원가입
	public void insert() {
		System.out.println("회원가입");
	}
}
