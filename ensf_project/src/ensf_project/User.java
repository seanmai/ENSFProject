package ensf_project;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	String type;

	
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
	
	public String getEmail() {
		return email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean correctPassword(String input) {
		return password.equals(input);
	}
}
