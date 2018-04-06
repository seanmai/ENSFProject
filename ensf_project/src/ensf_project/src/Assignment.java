package ensf_project;

import java.io.Serializable;

public class Assignment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int courseID;
	private String title;
	private String path;
	private boolean active;
	private String dueDate;
	
	public Assignment(int courseID, String title, String dueDate){
		this.courseID = courseID;
		this.title = title;
		active = true;
		this.dueDate = dueDate;
	}
	
	public void setPath(String p)
	{
		path = p;
	}
	
	public int getCourseID()
	{
		return courseID;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public boolean isActive()
	{
		return active;
	}

	public String getDueDate()
	{
		return dueDate;
	}
}
