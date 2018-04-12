package ensf_project.src;

import java.io.Serializable;

public class Submission implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int assignID;
	private int studentID;
	private String path;
	private String title;
	private int submissionGrade;
	private String comments;
	private String timestamp;
	
	public Submission(int assignID, int studentID, String title, String timestamp){
		this.assignID = assignID;
		this.studentID = studentID;
		this.title = title;
		submissionGrade = 0;
		comments = "";
		this.timestamp = timestamp;
	}
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
	
	public void setGrade(int g) {
		submissionGrade = g;
	}
	
	public void setPath(String p)
	{
		path = p;
	}
	
	public void setComments(String c) {
		comments = c;
	}
	
		
	public int getAssignID() {
		return assignID;
	}
	
	public int getStudentID() {
		return studentID;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getSubmissionGrade(){
		return submissionGrade;
	}
	
	public String getComments() {
		return comments;
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	public String toString()
	{
		String s = title;
		s += "  SUBMITTED: " + timestamp;
		return s;
	}
}
