package ensf_project.src;

import java.io.Serializable;

/**
 * Contains methods and fields to create a Java data type representing a course
 * that is created by a professor user.
 * Imlements serializable.
 * 
 * @author Wafa Anam, Sea Main, Matt Kadatz
 * @version 1.0
 * @since April 5, 2018
 */

public class Course implements Serializable{
	/**
	 *	Serial id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Unique ID
	 */
	private int id;
	
	/**
	 * ID of associated professor user
	 */
	private int profID;
	
	/**
	 * Active status of assignment set by a professor
	 */
	private boolean active;
	
	/**
	 * Course name
	 */
	private String name;
	
	/**
	 * Constructs an object of type course with specified values
	 * @param id the course id
	 * @param profID the id of the professor user associated with the submission
	 * @param name the name of the course
	 * @param a if the course is active
	 */
	public Course(int id, int profID, String name, boolean a) {
		this.profID = profID;
		this.name = name;
		active = a;
		this.id = id;
	}
		
	/**
	 * Retrieves professor user id
	 * @return professor user id
	 */
	public int getProfID() {
		return profID;
	}
	
	/**
	 * Retrieves the active status of the course
	 * @return if the course is active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Retrieves the course name
	 * @return course name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retrieves the course ID
	 * @return course ID
	 */
	public int getID()
	{
		return id;
	}
	
	/**
	 * Converts an object of type submission to a String
	 */
	public String toString()
	{
		String s = name;
		if(active)s += "   (active)";
		return s;
	}
}
