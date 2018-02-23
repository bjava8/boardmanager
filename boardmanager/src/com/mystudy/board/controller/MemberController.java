package com.mystudy.board.controller;

import com.mystudy.board.dao.MemberDAO;
import com.mystudy.board.vo.MemberVO;

public class MemberController {

	// 로그인
	public MemberVO login(String id, String pw) {
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.login(id, pw);
		
		return vo;
	}
}
