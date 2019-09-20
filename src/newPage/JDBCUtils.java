package newPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
	
	private Connection conn = null;
	private PreparedStatement pstmt=null; 
	
	public Connection connect(){
		
		String user="root";
		String password="1qaz2wsx";
		String driverClass = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://192.168.34.16:3306/demo?useSSL=false&serverTimezone=CST";
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(jdbcUrl, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void close(Connection conn,PreparedStatement pstmt,ResultSet result){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(result != null){
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
