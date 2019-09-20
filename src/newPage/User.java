package newPage;

public class User {
	
	private int id;
	private String name;
	private String number;
	protected String UserName;
	protected String FileName;
	protected byte[] imageData;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public User(int id, String userName, String fileName, byte[] imageData) {
		super();
		this.id = id;
		this.UserName = userName;
		this.FileName = fileName;
		this.imageData = imageData;
	}
	
	public User() {
		super();
	}
	
	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

}
