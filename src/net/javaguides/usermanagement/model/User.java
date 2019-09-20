package net.javaguides.usermanagement.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class User {
	protected int id;
	protected String UserName;
	protected String FileName;
	protected byte[] imageData;
	protected String user;
	protected String password;
	
	public User(int id, String userName, String fileName, byte[] imageData, String user, String password) {
		super();
		this.id = id;
		this.UserName = userName;
		this.FileName = fileName;
		this.imageData = imageData;
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public User(int id2, String userName2, String fileName2) {
		super();
		this.id = id2;
		this.UserName = userName2;
		this.FileName = fileName2;
	}
	
	public User(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
}
