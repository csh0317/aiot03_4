package newPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserDao0 {
	
	private static final String SELECT_USER_BY_ID = "select id,user_name,file_name,photo from image where id =?";
	

	public int getPage_search(String word){
		
		int recordCount=0,t1=0,t2=0;
		PreparedStatement pstmt=null;
		ResultSet result=null;
		JDBCUtils jdbc=new JDBCUtils();
		Connection conn=jdbc.connect();
		String sql="select count(*) from users WHERE file_name LIKE '%"+word+"%'";
		
		try {
			pstmt=conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			result.next();
			recordCount=result.getInt(1);
			t1=recordCount%5;
			t2=recordCount/5;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			jdbc.close(conn, pstmt, result);
		}
		if(t1 != 0){
			t2=t2+1;
		}
		return t2;
	}
	public List<User> listUser_search(int pageNo,String word){
		
		PreparedStatement pstmt=null;
		ResultSet result=null;
		List<User> list=new ArrayList<User>();
		int pageSize=5;
		int page=(pageNo-1)*5;
		JDBCUtils jdbc=new JDBCUtils();
		Connection conn=jdbc.connect();
		String sql="select * from users WHERE file_name LIKE '%"+word+"%' order by id limit ?,?";
		
		try {
			int id;
			String username;
			String filename;
			byte[] photo;
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, pageSize);
			result=pstmt.executeQuery();
			while(result.next()){
				User user=new User();
				user.setId(result.getInt(1));
				user.setName(result.getString(2));
				user.setNumber(result.getString(3));
//				User user=new User(result.getInt(1),result.getString(2),result.getString(3),result.getBytes(4));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			jdbc.close(conn, pstmt, result);
		}
		return list;
	}
	
	public int getPage(){
		
		int recordCount=0,t1=0,t2=0;
		PreparedStatement pstmt=null;
		ResultSet result=null;
		JDBCUtils jdbc=new JDBCUtils();
		Connection conn=jdbc.connect();
		String sql="select count(*) from users";
		
		try {
			pstmt=conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			result.next();
			recordCount=result.getInt(1);
			t1=recordCount%5;
			t2=recordCount/5;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			jdbc.close(conn, pstmt, result);
		}
		if(t1 != 0){
			t2=t2+1;
		}
		return t2;
	}
	
	public List<User> listUser(int pageNo){
		
		PreparedStatement pstmt=null;
		ResultSet result=null;
		List<User> list=new ArrayList<User>();
		int pageSize=5;
		int page=(pageNo-1)*5;
		JDBCUtils jdbc=new JDBCUtils();
		Connection conn=jdbc.connect();
		String sql="select * from users order by id limit ?,?";
		
		try {
			int id;
			String username;
			String filename;
			byte[] photo;
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, page);
			pstmt.setInt(2, pageSize);
			result=pstmt.executeQuery();
			while(result.next()){
				User user=new User();
				user.setId(result.getInt(1));
				user.setName(result.getString(2));
				user.setNumber(result.getString(3));
//				User user=new User(result.getInt(1),result.getString(2),result.getString(3),result.getBytes(4));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			jdbc.close(conn, pstmt, result);
		}
		return list;
	}
	
	public User selectUser(int id) {
		User user = null;
		JDBCUtils jdbc=new JDBCUtils();
		Connection conn=jdbc.connect();
		PreparedStatement pstmt=null;
		ResultSet result=null;
		String sql="select id,user_name,file_name,photo from users where id =?";
		// Step 1: Establishing a Connection
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			System.out.println(pstmt);
			// Step 3: Execute the query or update query
			ResultSet rs = pstmt.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String userName = rs.getString("user_name");
				String fileName = rs.getString("file_name");
				byte[] imageData = rs.getBytes("photo");
				user = new User(id, userName, fileName ,imageData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
