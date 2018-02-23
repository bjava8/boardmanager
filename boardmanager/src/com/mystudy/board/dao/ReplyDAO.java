package com.mystudy.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mystudy.board.common.DBConn;
import com.mystudy.board.vo.ReplyVO;

public class ReplyDAO {
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	//특정 글의 전체 댓글 조회
	public ArrayList<ReplyVO> repAll(int bbs_no) {
		ArrayList<ReplyVO> list = null;
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			
			if(conn == null) {
				System.out.println(" >> DB 연결 객체가 없습니다. 이 이상 진행할 수 없습니다.");
				return list;
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT REP_NO, ID, CONTENT, REGDATE, BBS_NO ");
			sb.append(" FROM REPLY ");
			sb.append(" WHERE BBS_NO = ? ");
			sb.append(" ORDER BY REP_NO");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, bbs_no);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				ReplyVO rvo = new ReplyVO(rs.getInt("REP_NO"),
			                             rs.getString("ID"),
			                             rs.getString("CONTENT"),
			                             rs.getString("REGDATE"),
			                             rs.getInt("BBS_NO"));
				
				list.add(rvo);
			}
			
			int allCnt = list.size();
			if (0 < allCnt) {
				System.out.println(allCnt + "개의 댓글이 조회되었습니다.");
				//System.out.println(list);
			}
			else {
				System.out.println("조회된 댓글이 없습니다.");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBConn.close(conn, pstmt, rs);
		}
				
		return list;
	}
	
	//댓글 입력
	public int repInsert(ReplyVO vo) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO REPLY ");
			sb.append(" (REP_NO, ID, CONTENT, REGDATE, BBS_NO) ");
			sb.append(" VALUES (?, ?, ?, TO_CHAR(SYSDATE, 'YYYY/MM/DD'), ?) ");
			
			pstmt = conn.prepareStatement(sb.toString());
			/*
			pstmt.setInt(1, rep_no);
			pstmt.setString(2, id);
			pstmt.setString(3, content);
			pstmt.setInt(4, bbs_no);
			*/
			result = pstmt.executeUpdate();
			
			if (0 < result) {
				System.out.println(result + "건의 댓글이 입력되었습니다.");
			}
			else {
				System.out.println("댓글 입력에 실패하였습니다.");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	public ArrayList<ReplyVO> repSearch(String content) { //내용으로 댓글 검색
		ArrayList<ReplyVO> list = null;
		ReplyVO rvo = null;
		
		Connection conn = null;

		try {
			
			conn = DBConn.getConnection();
			if(conn == null) {
				System.out.println(" >> DB 연결 객체가 없습니다. 이 이상 진행할 수 없습니다.");
				return list;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT REP_NO, ID, CONTENT, REGDATE, BBS_NO ");
			sb.append(" FROM REPLY ");
			sb.append(" WHERE CONTENT LIKE '%' || ? || '%' ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, content);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				rvo = new ReplyVO(rs.getInt("REP_NO"),
						                  rs.getString("ID"),
						                  rs.getString("CONTENT"),
						                  rs.getString("REGDATE"),
						                  rs.getInt("BBS_NO"));
				list.add(rvo);
			}
			
			int seCnt = list.size();
			if (0 < seCnt) {
				System.out.println(seCnt + "개의 댓글이 검색되었습니다.");
				System.out.println(list);
			}
			else {
				System.out.println("검색된 댓글이 없습니다.");
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return list;
		
	}
	
	
}
