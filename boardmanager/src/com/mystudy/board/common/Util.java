package com.mystudy.board.common;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.mystudy.board.vo.BoardVO;

public class Util {
	// 입력
	public static String input(String str) {
		System.out.print(str);
		Scanner scanner = new Scanner(System.in);
		
		
		String menu = scanner.nextLine();
		//scanner.skip("[\\r\\n]+");
		
		System.out.println("입력값 : " + menu);
		/*
		System.out.println("menu.length()="+menu.length());
		
		
		char stArray[] = menu.toCharArray();
		System.out.println("stArray.length="+stArray.length);
		for (char c : stArray) {
			System.out.println("(int)stArray[i]="+(int)c);
		}
		*/
		
		
		return menu;
	}
	
	// 번호 입력
	public static int inputNum() {
		System.out.print("번호 입력>");
		Scanner scanner = new Scanner(System.in);

		int num = Integer.parseInt(scanner.nextLine());

		return num;
	}
	
	public static void printList(ArrayList<BoardVO> list) {
		
		printLongline();
		//System.out.printf("%-3s\t%-10s\t%-90s\t%-10s\t%-3s\t%-3s \n", "번호", "아이디", "제목", "날짜", "조회", "추천");
		System.out.printf("%-3s\t%-10s\t%-90s\t%-10s\t%-3s \n", "번호", "아이디", "제목", "날짜", "조회");
		printLongline();
		for (BoardVO bvo : list) {
			
			int sbj = 90;
			int han = 0;
			
			for (int i = 0; i < bvo.getSubject().length(); i++) {
				boolean b = Pattern.matches("^[ㄱ-ㅎ가-힣]*$", String.valueOf(bvo.getSubject().charAt(i)));
				if (b) han++;
			}
			sbj -= han;
			
			System.out.printf("%3s\t%-10s\t%-"+sbj+"s\t%10s\t%3s \n", 
				bvo.getBbs_no(), 
				bvo.getId(), 
				bvo.getSubject(), 
				bvo.getRegdate(),
				bvo.getHits());
		}
		printLongline();
	}
	
	public static void printLine() {
		System.out.println("--------------------------------------------------");
	}
	
	public static void printLongline() {
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void printBoardVO(BoardVO vo) {
		printLongline();
		System.out.println("번호 : " + vo.getBbs_no() + " | ID : " + vo.getId() + " | 조회수 : " + vo.getHits());
		System.out.println("제목 : " + vo.getSubject());
		System.out.println("내용 : " + vo.getContent());
		printLongline();
	}
}
