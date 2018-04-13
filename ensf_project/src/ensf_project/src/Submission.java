package ensf_project.src;

import java.io.Serializable;

/**
 * Contains methods and fields to create a Java data type representing a submission
 * that is created by a student user.
 * Implements serializable.
 * 
 * @author Wafa Anam, Sea Main, Matt Kadatz
 * @version 1.0
 * @since April 5, 2018
 */

public class Submission implements Serializable{
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Unique id
	 */
	private int id;
	
	/**
	 * ID of assignment the submission is associated to
	 */
	private int assignID;
	
	/**
	 * ID of student user the submission is associated to
	 */
	private int studentID;
	
	/**
	 * File path of the stored submission
	 */
	private String path;
	
	/**
	 * Title of submission
	 */
	private String title;
	
	/**
	 * Grade assigned by professor user that created the associated assignment
	 */
	private int submissionGrade;
	
	/**
	 * Comments given by professor user that created the associated assignment
	 */
	private String comments;
	
	/**
	 * Time that submission was created
	 */
	private String timestamp;
	
	/**
	 * Constructs an object of type submission with specified values
	 * @param assignID the id of the assignment associated with the submission
	 * @param studentID the id of the student user associated with the submission
	 * @param title the name of the submission
	 * @param timestamp the date that the submission was created
	 */
	public Submission(int assignID, int studentID, String title, String timestamp){
		this.assignID = assignID;
		this.studentID = studentID;
		this.title = title;
		submissionGrade = 0;
		comments = "";
		this.timestamp = timestamp;
	}
	
	/**
	 * Constructs an object of type submission with specified values
	 * @param id submission id
	 * @param assignID the id of the assignment associated with the submission
	 * @param studentID the id of the student user associated with the submission
	 * @param path file path the submission is stored to
	 * @param title the name of the submission
	 * @param submissionGrade the grade given to the submission by the associated professor
	 * @param comments the comments given to the submission by the associated professor
	 * @param timestamp the date that the submission was created
	 */
	public Submission(int id, int assignID, int studentID, String path, String title, int submissionGrade, String comments, String timestamp){
		this.id = id;
		this.assignID = assignID;
		this.studentID = studentID;
		this.path = path;
		this.title = title;
		this.submissionGrade = submissionGrade;
		this.comments = comments;
		this.timestamp = timestamp;
	}
	
	/**
	 * Sets the submission grade to the specified value
	 * @param g specifies grade submission will receive
	 */
	public void setGrade(int g) {
		submissionGrade = g;
	}
	
	/**
	 * Sets the submission path to the specified value
	 * @param p specifies path assignment is stored at
	 */
	public void setPath(String p)
	{
		path = p;
	}
	
	/**
	 * Sets the submission comments to the specified value
	 * @param g specifies grade submission will receive
	 */
	public void setComments(String c) {
		comments = c;
	}
	
	/**
	 * Retrieves assignment id
	 * @return assignment id
	 */	
	public int getAssignID() {
		return assignID;
	}
	
	/**
	 * Retrieves student user id
	 * @return student user id
	 */
	public int getStudentID() {
		return studentID;
	}
	
	/**
	 * Retrieves submission file path
	 * @return submission file path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Retrieves submission title
	 * @return submission title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Retrieves submission grade
	 * @return submission grade
	 */
	public int getSubmissionGrade(){
		return submissionGrade;
	}
	
	/**
	 * Retrieves submission comments
	 * @return submission comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Retrieves date of submission creation
	 * @return submission timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Converts an object of type submission to a String
	 */
	public String toString()
	{
		String s = title;
		s += "  SUBMITTED: " + timestamp;
		return s;
	}
}
