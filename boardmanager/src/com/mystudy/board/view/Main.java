package com.mystudy.board.view;

public class Main {

	public static void main(String[] args) {

		/*
		View view = new View();
		
		view.start();
*/
		
		Menu menu = new Menu();
		menu.start();
		
		/*
		BoardDAO crud = new BoardDAO();
		ArrayList<BoardVO> list = null;
		*/
		/*
		BoardVO bvo = crud.selectOne(1);
		System.out.println("Main.java:"+bvo);
*/
		// 제목 검색
		// 내용 검색
		// id 검색
		/*E
		list = crud.selectAll();
		for (BoardVO vo : list) {
			System.out.println(vo);
		}
		*/
		
		/*
		list = crud.searchBySubject("제");
		for (BoardVO vo : list) {
			System.out.println(vo);
		}
		*/
		/*
		list = crud.search("subject", "제");
		System.out.println(list.size());
		for (BoardVO vo : list) {
			System.out.println(vo);
		}
		*/
	}

}