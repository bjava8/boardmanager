package boardmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String USER = "boardmanager";
	final static String PASSWORD = "boardmanagerpw";

	public DBConn() {
		//step1 load the driver class
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		//step2 create  the connection object
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // url, user, password
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
