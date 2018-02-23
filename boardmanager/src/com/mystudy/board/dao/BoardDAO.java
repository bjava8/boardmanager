package com.mystudy.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mystudy.board.common.DBConn;
import com.mystudy.board.vo.BoardVO;

public class BoardDAO {
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	public ArrayList<BoardVO> selectAll() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		
		String sql = "";
		sql += "select bbs_no, id, subject, to_char(regdate,'yyyy-mm-dd') regdate, hits, recommend ";
		sql += " from bbs";
		sql += " ORDER BY bbs_no DESC";
		
		try {
			conn = DBConn.getConnection();
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
				bvo.setRegdate(rs.getString("regdate"));
				bvo.setHits(rs.getInt("hits"));
				bvo.setRecommend(rs.getInt("recommend"));	
				
				list.add(bvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public BoardVO selectOne(int bbs_no) {
		Connection conn = null;
		BoardVO bvo = null;
		
		String sql = "";
		sql += "select bbs_no, id, subject, content, regdate, hits, recommend ";
		sql += " from bbs";
		sql += " where bbs_no = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bbs_no); 
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bvo = new BoardVO();
				bvo.setBbs_no(rs.getInt("bbs_no"));
				bvo.setId(rs.getString("id"));
				bvo.setSubject(rs.getString("subject"));
				bvo.setContent(rs.getString("content"));
				bvo.setRegdate(rs.getString("regdate"));
				bvo.setHits(rs.getInt("hits"));
				bvo.setRecommend(rs.getInt("recommend"));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return bvo;	
	}

	public ArrayList<BoardVO> searchBySubject(String subject) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		BoardVO bvo = new BoardVO();
		
		String sql = "";
		sql += "select bbs_no, id, subject, content, regdate, hits, recommend ";
		sql += " from bbs";
		sql += " where subject like '%' || ? || '%'";
		
		try {
			conn = DBConn.getConnection();
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
			DBConn.close(conn, pstmt, rs);
		}
		
		return list;
	}

	public ArrayList<BoardVO> search(String col, String q) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		
		String sql = "";
		sql += "select bbs_no, id, subject, content, to_char(regdate,'yyyy-mm-dd') regdate, hits, recommend ";
		sql += " from bbs";
		sql += " where " + col + " like '%' || ? || '%'";
		
		try {
			conn = DBConn.getConnection();
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
			DBConn.close(conn, pstmt, rs);
		}
		
		return list;
	}

	public ArrayList<BoardVO> searchById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	


	// 삭제
	public int remove(int bno) {
		
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "DELETE FROM bbs ";
		sql	+= "WHERE bbs_no = ?";
	
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, null);
		}
		
		return result;
	}

	public int write(BoardVO vo) {
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "insert into bbs values(bbs_seq.nextval, ?, ?, ?, sysdate, 0, 0)";
	
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	public int selectHits(int bno) {
		
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "SELECT hits FROM bbs ";
		sql	+= " WHERE bbs_no = ?";
	
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt("hits");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, null);
		}
		
		return result;
	}

	public int updateHits(int hits, int bno) {
		
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "UPDATE bbs ";
		sql += " SET hits = ? ";
		sql	+= "WHERE bbs_no = ?";
	
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, hits);
			pstmt.setInt(2, bno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	public int selectRecommend(int bno) {
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "SELECT recommend FROM bbs ";
		sql	+= " WHERE bbs_no = ?";
	
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt("recommend");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	public int updateRecommend(int rcm, int bno) {
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "UPDATE bbs ";
		sql += " SET recommend = ? ";
		sql	+= "WHERE bbs_no = ?";
	
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, rcm);
			pstmt.setInt(2, bno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, null);
		}
		
		return result;
	}

	public int update(BoardVO vo) {
		
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "UPDATE bbs ";
		sql += " SET subject = ? , content = ?";
		sql	+= "WHERE bbs_no = ?";
	
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getBbs_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, null);
		}
		
		return result;
		
	}

	// 삭제 - id비교추가
	public int delete(BoardVO vo) {
		Connection conn = null;
		int result = 0;
	
		String sql = "";
		sql += "DELETE FROM bbs ";
		sql	+= "WHERE bbs_no = ?";
		sql	+= " AND id = ?";
	
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, vo.getBbs_no());
			pstmt.setString(2, vo.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, null);
		}
		
		return result;
	}

}
