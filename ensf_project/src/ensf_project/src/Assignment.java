package ensf_project.src;

import java.io.Serializable;

/**
 * Contains methods and fields to create a Java data type representing an assignment
 * that is created by a professor.
 * Imlements serializable.
 * 
 * @author Wafa Anam, Sea Main, Matt Kadatz
 * @version 1.0
 * @since April 5, 2018
 */
public class Assignment implements Serializable{
	
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Unique assignment id
	 */
	private int id;
	
	/**
	 * ID of the course the assignment belongs to
	 */
	private int courseID;
	
	/**
	 * Title of an assignment
	 */
	private String title;
	
	/**
	 * Path an assignment file is stored in
	 */
	private String path;
	
	/**
	 * Active status of assignment set by a professor
	 */
	private boolean active;
	
	/**
	 * Date the assignment is due
	 */
	private String dueDate;
	
	/**
	 * Constructs an object of type assignment with specified values
	 * @param courseID the id of the course the assignment belongs to
	 * @param title the name of the assignment
	 * @param dueDate the date the assignment is due 
	 * @param active the status of the assignment
	 */
	public Assignment(int courseID, String title, String dueDate, boolean active){
		this.courseID = courseID;
		this.title = title;
		this.active = active;
		this.dueDate = dueDate;
	}
	
	/**
	 * Constructs an object of type assignment with specified values from database side
	 * @param id assignment id
	 * @param courseID the id of the course the assignment belongs to
	 * @param title the name of the assignment
	 * @param path the path describing where assignment file is stored
	 * @param dueDate the date the assignment is due 
	 * @param active the status of the assignment
	 */
	public Assignment(int id, int courseID, String title, String path, String dueDate, boolean active){
		this.id = id;
		this.courseID = courseID;
		this.title = title;
		this.path = path;
		this.active = active;
		this.dueDate = dueDate;
	}
	
//	/**
//	 * Constructs an object of type assignment
//	 * @param id
//	 * @param courseID
//	 * @param title
//	 * @param dueDate
//	 * @param active
//	 */
//	public Assignment(int id, int courseID, String title, String dueDate, boolean active){
//		this.id = id;
//		this.courseID = courseID;
//		this.title = title;
//		this.active = active;
//		this.dueDate = dueDate;
//	}
	
	/**
	 * Sets the assignment path to the specified value
	 * @param p specifies path assignment is stored at
	 */
	public void setPath(String p)
	{
		path = p;
	}
	
	/**
	 * Retrieves assignment id
	 * @return assignment id
	 */
	public int getID()
	{
		return id;
	}
	
	/**
	 * Retrieves course id
	 * @return course id
	 */
	public int getCourseID()
	{
		return courseID;
	}
	
	/**
	 * Retrieves assignment name
	 * @return title of assignment
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Retrieves the storage path of assignment
	 * @return storage path
	 */
	public String getPath()
	{
		return path;
	}
	
	/**
	 * Retrieves status of assignment
	 * @return true if assignment is active
	 * false otherwise
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * Retrieves the due date of an assignment
	 * @return assignment due date
	 */
	public String getDueDate()
	{
		return dueDate;
	}
	
	/**
	 * Converts an object of type assignment to a String
	 */
	@Override 
	public String toString()
	{
		String s = title;
		if(active)s += "   (active)";
		s += "  DUE: " + dueDate;
		return s;
	}
}
