package boardmanager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class View {

	public void start() {
		
		// 1-ÀÔ·Â
		// ¸ñ·Ï º¸±â ÀÔ·ÂÇÔ.
		// ±âº»°ªÀÌ 1-¸ñ·Ïº¸±â
		String sel = "1";
		
		while (true) {
			
			if (sel.equals("1")) {	// 1-¸ñ·Ïº¸±â
				viewList();	// Ãâ·Â
				// ¸Þ´º
				//ListMenu();
			} else if (sel.equals("2")) { // 2-»ó¼¼º¸±â
				// ¹øÈ£ ÀÔ·Â ¹Þ±â
				int bno = inputNum();
				viewDetail(bno);
				//DetailMenu();
			} else if (sel.equals("3")) { // 3-°Ë»ö
				// Á¦¸ñ, ³»¿ë, ÀÛ¼ºÀÚ ¼±ÅÃ
				String col = input("0-Á¦¸ñ, 1-³»¿ë, 2-ÀÛ¼ºÀÚ ¹øÈ£ ÀÔ·Â>");
				// °Ë»ö¾î ÀÔ·Â
				String text = input("°Ë»ö¾î ÀÔ·Â>"); 
				search(col, text);
				//ListMenu();
			} else if (sel.equals("4")) { // 4-¾²±â
				System.out.println("4-¾²±â");
			} else if (sel.equals("5")) { // 5-¼öÁ¤
				System.out.println("5-¼öÁ¤");
			} else if (sel.equals("6")) { // 6-»èÁ¦
				// ¹øÈ£ ÀÔ·Â ¹Þ±â
				int bno = inputNum();
				remove(bno);
			} else if (sel.equals("9")) { // 9-Á¾·á
				break;
			} else {
				System.out.println("[ERROR] Àß¸øµÈ ¹øÈ£");
			}
			
			// 2-¿äÃ»
			// ¸ñ·Ï º¸±â ¿äÃ»ÇÔ. viewList1();	
			
			
			
			
			// 3-µðºñ°®´Ù¿À°í
			
			// 4-ÀÀ´ä
			// 5-ÀÀ´ä ¹ÞÀº°Å Ãâ·Â
			
			// 6-´Ù½Ã ÀÔ·Â 1
			showMenu();
			sel = input();

		} // END while
		
		
		System.out.println("Á¾·á");
		
		// ¸Þ´º ÀÔ·Â
		/*
		do {
			
		
			
			// ¸ñ·Ï º¸±â
			if (sel == "1") {
				viewList();
				
				// ¸Þ´ºº¸¿©ÁÖ±â
			}
			
			sel = input();
			
			
			
		} while (sel.equals("9"));
			*/
	} // END start()
	

/*
 * System.out.println("1. ¸ñ·Ï");
		System.out.println("2. ÀÐ±â");
		System.out.println("3. ±Û¾²±â");
		System.out.println("4. °Ë»ö");
		System.out.println("5. ¼öÁ¤");
		System.out.println("6. »èÁ¦");
		
		System.out.println("9. Á¾·á");
 * 
 */
	public void ListMenu() {
		System.out.println("2. »ó¼¼º¸±â");
		System.out.println("3. °Ë»ö");
		System.out.println("4. ¾²±â");
		System.out.println("9. Á¾·á");
	}
	// »ó¼¼º¸±â ¸Þ´º
	private void DetailMenu() {
		System.out.println("1. ¸ñ·Ï");
		System.out.println("4. ¾²±â");
		System.out.println("5. ¼öÁ¤");
		System.out.println("6. »èÁ¦");
		System.out.println("9. Á¾·á");
	}
	
	private void showMenu() {
		System.out.println("1. ¸ñ·Ï 2. »ó¼¼ 3. °Ë»ö 4. ¾²±â 5. ¼öÁ¤ 6. »èÁ¦ 9. Á¾·á");
	}


	// È­¸é ¸ñ·Ï º¸±â
	public void viewList() {
		Controller cont = new Controller();
		// 3-µðºñ°®´Ù¿À°í
		// 4-ÀÀ´ä
		
		// ¸®½ºÆ® °¡Á®¿À±â
		ArrayList<BoardVO> list = cont.viewList(); 
		
		// 5-ÀÀ´ä ¹ÞÀº°Å Ãâ·Â
		printList(list); // ¸®½ºÆ® Ãâ·Â
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
		
		printList(list); // ¸®½ºÆ® Ãâ·Â
	}
	
	// ÀÔ·Â
	public String input() {
		System.out.print(">");
		Scanner scanner = new Scanner(System.in);
		
		String menu = scanner.nextLine();
		
		return menu;
	}
	
	// ÀÔ·Â
	public String input(String str) {
		System.out.print(str);
		Scanner scanner = new Scanner(System.in);
		
		String menu = scanner.nextLine();
		
		return menu;
	}
	
	// ¹øÈ£ ÀÔ·Â
	public int inputNum() {
		System.out.print("¹øÈ£ ÀÔ·Â>");
		Scanner scanner = new Scanner(System.in);
		
		
		int num = Integer.parseInt(scanner.nextLine());
		
		
		
		return num;
	}
	
	// 6-»èÁ¦
	public void remove(int bno) {
		Controller cont = new Controller();
		
		int result = cont.remove(bno);
		
		if (result == 1) {
			System.out.println("»èÁ¦ ¿Ï·á");
		} else {
			System.out.println("»èÁ¦ ½ÇÆÐ : result="+result);
		}
		//return result;
	}
	
	public void printList(ArrayList<BoardVO> list) {
		
		System.out.printf("%-3s\t%-10s\t%-90s\t%-10s\t%-3s\t%-3s \n", "¹øÈ£", "¾ÆÀÌµð", "Á¦¸ñ", "³¯Â¥", "Á¶È¸", "ÃßÃµ");
		
		for (BoardVO bvo : list) {
			
			int sbj = 90;
			int han = 0;
			
			for (int i = 0; i < bvo.getSubject().length(); i++) {
				boolean b = Pattern.matches("^[¤¡-¤¾°¡-ÆR]*$", String.valueOf(bvo.getSubject().charAt(i)));
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
	
}
