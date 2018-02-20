package boardmanager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class View {

	public void start() {
		
		// 1-�Է�
		// ��� ���� �Է���.
		// �⺻���� 1-��Ϻ���
		String sel = "1";
		
		while (true) {
			
			if (sel.equals("1")) {	// 1-��Ϻ���
				viewList();	// ���
				// �޴�
				//ListMenu();
			} else if (sel.equals("2")) { // 2-�󼼺���
				// ��ȣ �Է� �ޱ�
				int bno = inputNum();
				viewDetail(bno);
				//DetailMenu();
			} else if (sel.equals("3")) { // 3-�˻�
				// ����, ����, �ۼ��� ����
				String col = input("0-����, 1-����, 2-�ۼ��� ��ȣ �Է�>");
				// �˻��� �Է�
				String text = input("�˻��� �Է�>"); 
				search(col, text);
				//ListMenu();
			} else if (sel.equals("4")) { // 4-����
				System.out.println("4-����");
			} else if (sel.equals("5")) { // 5-����
				System.out.println("5-����");
			} else if (sel.equals("6")) { // 6-����
				// ��ȣ �Է� �ޱ�
				int bno = inputNum();
				remove(bno);
			} else if (sel.equals("9")) { // 9-����
				break;
			} else {
				System.out.println("[ERROR] �߸��� ��ȣ");
			}
			
			// 2-��û
			// ��� ���� ��û��. viewList1();	
			
			
			
			
			// 3-��񰮴ٿ���
			
			// 4-����
			// 5-���� ������ ���
			
			// 6-�ٽ� �Է� 1
			showMenu();
			sel = input();

		} // END while
		
		
		System.out.println("����");
		
		// �޴� �Է�
		/*
		do {
			
		
			
			// ��� ����
			if (sel == "1") {
				viewList();
				
				// �޴������ֱ�
			}
			
			sel = input();
			
			
			
		} while (sel.equals("9"));
			*/
	} // END start()
	

/*
 * System.out.println("1. ���");
		System.out.println("2. �б�");
		System.out.println("3. �۾���");
		System.out.println("4. �˻�");
		System.out.println("5. ����");
		System.out.println("6. ����");
		
		System.out.println("9. ����");
 * 
 */
	public void ListMenu() {
		System.out.println("2. �󼼺���");
		System.out.println("3. �˻�");
		System.out.println("4. ����");
		System.out.println("9. ����");
	}
	// �󼼺��� �޴�
	private void DetailMenu() {
		System.out.println("1. ���");
		System.out.println("4. ����");
		System.out.println("5. ����");
		System.out.println("6. ����");
		System.out.println("9. ����");
	}
	
	private void showMenu() {
		System.out.println("1. ��� 2. �� 3. �˻� 4. ���� 5. ���� 6. ���� 9. ����");
	}


	// ȭ�� ��� ����
	public void viewList() {
		Controller cont = new Controller();
		// 3-��񰮴ٿ���
		// 4-����
		
		// ����Ʈ ��������
		ArrayList<BoardVO> list = cont.viewList(); 
		
		// 5-���� ������ ���
		printList(list); // ����Ʈ ���
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
		
		printList(list); // ����Ʈ ���
	}
	
	// �Է�
	public String input() {
		System.out.print(">");
		Scanner scanner = new Scanner(System.in);
		
		String menu = scanner.nextLine();
		
		return menu;
	}
	
	// �Է�
	public String input(String str) {
		System.out.print(str);
		Scanner scanner = new Scanner(System.in);
		
		String menu = scanner.nextLine();
		
		return menu;
	}
	
	// ��ȣ �Է�
	public int inputNum() {
		System.out.print("��ȣ �Է�>");
		Scanner scanner = new Scanner(System.in);
		
		
		int num = Integer.parseInt(scanner.nextLine());
		
		
		
		return num;
	}
	
	// 6-����
	public void remove(int bno) {
		Controller cont = new Controller();
		
		int result = cont.remove(bno);
		
		if (result == 1) {
			System.out.println("���� �Ϸ�");
		} else {
			System.out.println("���� ���� : result="+result);
		}
		//return result;
	}
	
	public void printList(ArrayList<BoardVO> list) {
		
		System.out.printf("%-3s\t%-10s\t%-90s\t%-10s\t%-3s\t%-3s \n", "��ȣ", "���̵�", "����", "��¥", "��ȸ", "��õ");
		
		for (BoardVO bvo : list) {
			
			int sbj = 90;
			int han = 0;
			
			for (int i = 0; i < bvo.getSubject().length(); i++) {
				boolean b = Pattern.matches("^[��-����-�R]*$", String.valueOf(bvo.getSubject().charAt(i)));
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
