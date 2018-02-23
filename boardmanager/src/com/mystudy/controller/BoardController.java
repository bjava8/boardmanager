package boardmanager;

import java.util.ArrayList;

public class Controller {
	BoardDAO dao = new BoardDAO();
	
	// 1-목록보기
	public ArrayList<BoardVO> viewList() {
		
		ArrayList<BoardVO> list = dao.selectAll();
		/*
		if (list == null) {
			list = new ArrayList<BoardVO>();
		}
		*/
		return list;
	}
	
	// 2-상세보기
	public BoardVO viewDetail(int bno) {
		BoardVO bvo = dao.selectOne(bno);
		return bvo;
	}
	
	// 3-검색
	public ArrayList<BoardVO> search(String col, String text) {
		
		if (col.equals("1")) {
			col = "subject";
		} else if (col.equals("2")) {
			col = "content";
		} else if (col.equals("3")) {
			col = "id";
		}
		
		ArrayList<BoardVO> list = dao.search(col, text);
		/*
		if (list == null) {
			list = new ArrayList<BoardVO>();
		}
		*/
		return list;
	}

	// 6-삭제
	public int remove(int bno) {
		int result = dao.remove(bno);
		return result;
	}

	// 입력
	public int write(BoardVO vo) {
		int result = dao.write(vo);
		return result;
	}
}