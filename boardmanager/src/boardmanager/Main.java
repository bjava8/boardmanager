package boardmanager;

public class Main {

	public static void main(String[] args) {
		
		View view = new View();
		//view.viewList();
		
		view.start();

		/*
		BoardDAO crud = new BoardDAO();
		ArrayList<BoardVO> list = null;
		*/
		/*
		BoardVO bvo = crud.selectOne(1);
		System.out.println("Main.java:"+bvo);
*/
		// ���� �˻�
		// ���� �˻�
		// id �˻�
		/*E
		list = crud.selectAll();
		for (BoardVO vo : list) {
			System.out.println(vo);
		}
		*/
		
		/*
		list = crud.searchBySubject("��");
		for (BoardVO vo : list) {
			System.out.println(vo);
		}
		*/
		/*
		list = crud.search("subject", "��");
		System.out.println(list.size());

		for (BoardVO vo : list) {
			System.out.println(vo);
		}
		*/
	}

}
