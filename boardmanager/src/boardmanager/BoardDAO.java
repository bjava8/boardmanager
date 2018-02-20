package boardmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO implements IBoard {
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDAO() {
		//step1 load the driver class
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection() {
		//step2 create  the connection object
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // url, user, password
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	@Override
	public ArrayList<BoardVO> selectAll() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		
		
		String sql = "";
		sql += "select bbs_no, id, subject, to_char(regdate,'yyyy-mm-dd') regdate, hits, recommend ";
		sql += " from bbs";
		
		try {
			conn = this.getConnection();
			if (conn == null) {
				System.out.println("DB 연결 실패");
				return list;
			}
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBbs_no(rs.getInt("bbs_no"));
				bvo.setId(rs.getString("id"));
				bvo.setSubject(rs.getString("subject"));
				//bvo.setContent(rs.getString("content"));
				bvo.setRegdate(rs.getString("regdate"));
				bvo.setHits(rs.getInt("hits"));
				bvo.setRecommend(rs.getInt("recommend"));	
				
				list.add(bvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil_JDBC.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	@Override
	public BoardVO selectOne(int bbs_no) {
		Connection conn = null;
		BoardVO bvo = new BoardVO();
		
		String sql = "";
		sql += "select bbs_no, id, subject, content, regdate, hits, recommend ";
		sql += " from bbs";
		sql += " where bbs_no = ?";
		
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bbs_no); 
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bvo.setBbs_no(rs.getInt("bbs_no"));
				bvo.setId(rs.getString("id"));
				bvo.setSubject(rs.getString("subject"));
				bvo.setContent(rs.getString("content"));
				bvo.setRegdate(rs.getString("regdate"));
				bvo.setHits(rs.getInt("hits"));
				bvo.setRecommend(rs.getInt("recommend"));				
			}
			
			System.out.println(bvo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil_JDBC.close(conn, pstmt, rs);
		}
		
		return bvo;	
	}

	@Override
	public ArrayList<BoardVO> searchBySubject(String subject) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		BoardVO bvo = new BoardVO();
		
		String sql = "";
		sql += "select bbs_no, id, subject, content, regdate, hits, recommend ";
		sql += " from bbs";
		sql += " where subject like '%' || ? || '%'";
		
		try {
			conn = this.getConnection();
			if (conn == null) {
				System.out.println("DB 연결 실패");
				return list;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject); 
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getInt("bbs_no"));
				bvo.setBbs_no(rs.getInt("bbs_no"));
				bvo.setId(rs.getString("id"));
				bvo.setSubject(rs.getString("subject"));
				//bvo.setContent(rs.getString("content"));
				bvo.setRegdate(rs.getString("regdate"));
				bvo.setHits(rs.getInt("hits"));
				bvo.setRecommend(rs.getInt("recommend"));	
				
				list.add(bvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil_JDBC.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public ArrayList<BoardVO> search(String col, String q) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		
		String sql = "";
		sql += "select bbs_no, id, subject, content, to_char(regdate,'yyyy-mm-dd') regdate, hits, recommend ";
		sql += " from bbs";
		sql += " where " + col + " like '%' || ? || '%'";
		
		try {
			conn = this.getConnection();
			if (conn == null) {
				System.out.println("DB 연결 실패");
				return list;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, q); 
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVO bvo = new BoardVO();
				
				bvo.setBbs_no(rs.getInt("bbs_no"));
				bvo.setId(rs.getString("id"));
				bvo.setSubject(rs.getString("subject"));
				bvo.setContent(rs.getString("content"));
				bvo.setRegdate(rs.getString("regdate"));
				bvo.setHits(rs.getInt("hits"));
				bvo.setRecommend(rs.getInt("recommend"));	
				
				list.add(bvo);
	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil_JDBC.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public ArrayList<BoardVO> searchById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	


	// 6-삭제
	public int remove(int bno) {
		
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "DELETE FROM bbs ";
		sql	+= "WHERE bbs_no = ?";
	
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil_JDBC.close(conn, pstmt, null);
		}
		
		return result;
	}
	
}
