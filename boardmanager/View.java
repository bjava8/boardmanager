package project1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class View {
	static MemberVO ss;
	MemberVO ss2;
	
	public View() {
		ss = null;
		ss2 = null;
	}

	public void start() {
		
		// 1-입력
		// 목록 보기 입력함.
		// 기본값이 1-목록보기
		String sel = "1";
		
		while (true) {
			
			if (sel.equals("1")) {	// 1-목록보기
				viewList();	// 출력
				// 메뉴
				//ListMenu();
			} else if (sel.equals("2")) { // 2-상세보기
				// 번호 입력 받기
				int bno = inputNum();
				viewDetail(bno);
				//DetailMenu();
			} else if (sel.equals("3")) { // 3-검색
				// 제목, 내용, 작성자 선택
				String col = input("0-제목, 1-내용, 2-작성자 번호 입력>");
				// 검색어 입력
				String text = input("검색어 입력>"); 
				search(col, text);
				//ListMenu();
			} else if (sel.equals("4")) { // 4-쓰기
				
				if (ss != null) {
					write(ss.getId());
				} else	{
					System.out.println("로그인하세요.");
				}
				
			} else if (sel.equals("5")) { // 5-수정
				System.out.println("5-수정");
			} else if (sel.equals("6")) { // 6-삭제
				// 번호 입력 받기
				int bno = inputNum();
				remove(bno);
			} else if (sel.equals("7")) { // 7-가입
				
			} else if (sel.equals("8")) { // 8-로그인
				// 아이디 입력
				// 비밀번호 입력
				String id = input("아이디 입력>");
				String pw = input("비밀번호 입력>");
				login(id, pw);
			} else if (sel.equals("99")) { // 99-종료
				break;
			} else {
				System.out.println("[ERROR] 잘못된 번호");
			}
			
			// 2-요청
			// 목록 보기 요청함. viewList1();	
			
			
			
			
			// 3-디비갖다오고
			
			// 4-응답
			// 5-응답 받은거 출력
			
			// 6-다시 입력 1
			showMenu(ss);
			sel = input();

		} // END while
		
		
		System.out.println("종료");
		
		// 메뉴 입력
		/*
		do {
			
		
			
			// 목록 보기
			if (sel == "1") {
				viewList();
				
				// 메뉴보여주기
			}
			
			sel = input();
			
			
			
		} while (sel.equals("9"));
			*/
	} // END start()
	

	private void write(String id) {

		Controller cont = new Controller();
			
		String subject = input("제목:");
		String content = input("내용:");
		
		BoardVO vo = new BoardVO();
			
		vo.setId(id);
		vo.setSubject(subject);
		vo.setContent(content);
		
		int result = cont.write(vo);
			
		if (result == 1) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패 : result="+result);
		}
	}

	/*
 * System.out.println("1. 목록");
		System.out.println("2. 읽기");
		System.out.println("3. 글쓰기");
		System.out.println("4. 검색");
		System.out.println("5. 수정");
		System.out.println("6. 삭제");
		
		System.out.println("9. 종료");
 * 
 */
	public void ListMenu() {
		System.out.println("2. 상세보기");
		System.out.println("3. 검색");
		System.out.println("4. 쓰기");
		System.out.println("9. 종료");
	}
	// 상세보기 메뉴
	private void DetailMenu() {
		System.out.println("1. 목록");
		System.out.println("4. 쓰기");
		System.out.println("5. 수정");
		System.out.println("6. 삭제");
		System.out.println("9. 종료");
	}
	
	private void showMenu() {
		System.out.println("1. 목록 2. 상세 3. 검색 4. 쓰기 5. 수정 6. 삭제 7. 가입 8-로그인 99. 종료");
	}
	
	private void showMenu(MemberVO s) {
		if (s != null) {
			System.out.println(s.getName()+"님이 로그인한 상태");
			System.out.println("1. 목록 2. 상세 3. 검색 4. 쓰기 5. 수정 6. 삭제 9-로그아웃 99. 종료");
		} else {
			System.out.println("1. 목록 2. 상세 3. 검색 4. 쓰기 5. 수정 6. 삭제 7. 가입 8-로그인 99. 종료");
		}
	}


	// 화면 목록 보기
	public void viewList() {
		Controller cont = new Controller();
		// 3-디비갖다오고
		// 4-응답
		
		// 리스트 가져오기
		ArrayList<BoardVO> list = cont.viewList(); 
		
		// 5-응답 받은거 출력
		printList(list); // 리스트 출력
		/*
		for (BoardVO bvo : list) {
			System.out.println(bvo);
		}
	*/
	}
	
	public void viewDetail(int bno) {
		Controller cont = new Controller();
		
		BoardVO bvo = cont.viewDetail(bno);
		
		System.out.println(bvo);
	}
	
	private void search(String col, String text) {
		Controller cont = new Controller();
		
		ArrayList<BoardVO> list = cont.search(col, text);
		
		printList(list); // 리스트 출력
	}
	
	// 입력
	public String input() {
		System.out.print(">");
		Scanner scanner = new Scanner(System.in);
		
		String menu = scanner.nextLine();
		
		return menu;
	}
	
	// 입력
	public String input(String str) {
		System.out.print(str);
		Scanner scanner = new Scanner(System.in);
		
		String menu = scanner.nextLine();
		
		return menu;
	}
	
	// 번호 입력
	public int inputNum() {
		System.out.print("번호 입력>");
		Scanner scanner = new Scanner(System.in);
		
		
		int num = Integer.parseInt(scanner.nextLine());
		
		
		
		return num;
	}
	
	// 6-삭제
	public void remove(int bno) {
		Controller cont = new Controller();
		
		int result = cont.remove(bno);
		
		if (result == 1) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패 : result="+result);
		}
		//return result;
	}
	
	public void printList(ArrayList<BoardVO> list) {
		
		System.out.printf("%-3s\t%-10s\t%-90s\t%-10s\t%-3s\t%-3s \n", "번호", "아이디", "제목", "날짜", "조회", "추천");
		
		for (BoardVO bvo : list) {
			
			int sbj = 90;
			int han = 0;
			
			for (int i = 0; i < bvo.getSubject().length(); i++) {
				boolean b = Pattern.matches("^[ㄱ-ㅎ가-힣]*$", String.valueOf(bvo.getSubject().charAt(i)));
				if (b) han++;
			}
			sbj -= han;
			
			System.out.printf("%3s\t%-10s\t%-"+sbj+"s\t%10s\t%3s\t%3s\n", 
				bvo.getBbs_no(), 
				bvo.getId(), 
				bvo.getSubject(), 
				bvo.getRegdate(),
				bvo.getHits(),
				bvo.getRecommend());
		}
		//System.out.println(bvo.getBbs_no()+"\t"+bvo.getId()+"\t"+bvo.getSubject()+"\t"+bvo.getRegdate()+"\t"+bvo.getHits()+"\t"+bvo.getRecommend());

	}
	
	// 8-로그인
	public void login(String id, String pw) {
		MemberController cont = new MemberController();
		MemberVO vo = cont.login(id, pw);
		if (vo != null) {
			System.out.println(vo);
			ss = vo;
			ss2 = vo;
		}
		
		System.out.println("아이디/비밀번호가 틀렸습니다");
	}
	
}