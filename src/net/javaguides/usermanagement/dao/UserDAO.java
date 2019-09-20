package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.User;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class UserDAO {
	//照片資料庫
	private String jdbcURL = "jdbc:mysql://192.168.34.16:3306/demo?useSSL=false&serverTimezone=CST";
	private String photodbUser = "root";
	private String photodbpwd = "1qaz2wsx";
	
	private static final String SELECT_USER_BY_ID = "select id,user_name,file_name,photo from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";	
//	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
//	private static final String UPDATE_USERS_SQL = "update users set user_name = ?,file_name= ?, photo =? where id = ?";

	
	//使用者資瞭料庫
	private String UserUrl = "jdbc:mysql://192.168.34.18:3306/mytable?useSSL=false&serverTimezone=CST";	
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345678";
	private static final String INSERT_USERS_SQL = "INSERT INTO password" + "  (user, password) VALUES "+ " (?, ?);";
	
	private static final String SELECT_ALL_USER = "select * from password";

	
	

	public UserDAO() {
	}

	//設定照片資料庫連結
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, photodbUser, photodbpwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

    //設定資料庫連結(創造帳號用)
	protected Connection getAccountConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(UserUrl, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		try (Connection con = getAccountConnection();
			PreparedStatement prep = con.prepareStatement(INSERT_USERS_SQL))  {
			prep.setString(1, user.getUser());
			prep.setString(2, user.getPassword());
			
			System.out.println("User" + user.getUser()+"added");
			System.out.println(prep);
			prep.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	public List<User> selectAllUser() {
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection con = getAccountConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement prep = con.prepareStatement(SELECT_ALL_USER);) {
			System.out.println(prep);
			// Step 3: Execute the query or update query
			ResultSet rs = prep.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String user = rs.getString("user");
				String password = rs.getString("password");
				
				users.add(new User(id, user, password));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection con = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement prep = con.prepareStatement(SELECT_USER_BY_ID);) {
			prep.setInt(1, id);
			System.out.println(prep);
			// Step 3: Execute the query or update query
			ResultSet rs = prep.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String userName = rs.getString("user_name");
				String fileName = rs.getString("file_name");
				byte[] imageData = rs.getBytes("photo");
				
				user = new User(id, userName, fileName ,imageData, null, null);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<User> selectAllUsers() {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("user_name");
				String fileName = rs.getString("file_name");
				users.add(new User(id, userName, fileName));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

//	public boolean deleteUser(int id) throws SQLException {
//		boolean rowDeleted;
//		try (Connection connection = getConnection();
//				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
//			statement.setInt(1, id);
//			rowDeleted = statement.executeUpdate() > 0;
//		}
//		return rowDeleted;
//	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
