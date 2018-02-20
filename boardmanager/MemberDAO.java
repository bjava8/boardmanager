package project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberVO login(String id, String pw) {
		Connection conn = null;
		MemberVO vo = null;
		
		String sql = "";
		sql += "select id, pw, name, phone from member ";
		sql += " where id = ? ";
		sql += " and pw = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); 
			pstmt.setString(2, pw); 
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new MemberVO(rs.getString("id"), rs.getString("pw"), rs.getString("name"), rs.getString("phone"));
				/*
				vo.setId(rs.getString("id"));
				vo.setPw(rs.getString("pw"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				*/
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return vo;
	}

}
