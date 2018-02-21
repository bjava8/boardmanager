package boardmanager;

import java.sql.Connection;
import java.util.ArrayList;

public interface IBoard {
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USER = "boardmanager";
	final String PASSWORD = "boardmanagerpw";
	
	Connection getConnection();
	public BoardVO selectOne(int bbs_no);
	
	// °Ë»ö
	public ArrayList<BoardVO> searchBySubject(String subject);
	//public ArrayList<BoardVO> searchByContent(String content);
	public ArrayList<BoardVO> searchById(String id);
	ArrayList<BoardVO> selectAll();
	ArrayList<BoardVO> search(String s, String q);
}