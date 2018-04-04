package ensf_project;

public class User {
	private int id;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String type;
	
	public User(int id, String password, String email, String firstName, String lastName, String type) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
	}
	
	public int getID() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String email() {
		return email;
	}
	
	public String firstName() {
		return firstName;
	}
	
	public String lastName() {
		return lastName;
	}
	
	public String type() {
		return type;
	}
}
