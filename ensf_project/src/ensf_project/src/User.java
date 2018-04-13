package ensf_project.src;

import java.io.Serializable;

/**
 * Class representing a user, both Professor
 * or Student
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Unique Student/Prof ID
	 */
	private int id;
	
	/**
	 * User Password
	 */
	private String password;
	
	/**
	 * User Email
	 */
	private String email;
	
	/**
	 * User First Name
	 */
	private String firstName;
	
	/**
	 * User Last Name
	 */
	private String lastName;
	
	/**
	 * String specifying if Student 
	 * or Professor
	 */
	String type;
	
	/**
	 * Constructor
	 * @param id
	 * @param password
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param type
	 */
	public User(int id, String password, String email, String firstName, String lastName, String type) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
	}
	
	/**
	 * Gets User ID
	 * @return id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Gets User Password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets User Email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets User First Name
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Gets User Last Name
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Gets User Type
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Determines if the input password
	 * matches Users password
	 * @param input
	 * @return true/false
	 */
	public boolean correctPassword(String input) {
		return password.equals(input);
	}
	
	/**
	 * Method to display User as String
	 */
	public String toString()
	{
		return (id + " " + firstName + " " + lastName);
	}
}
