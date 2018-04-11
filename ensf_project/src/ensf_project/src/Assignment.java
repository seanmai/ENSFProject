package ensf_project.src;

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
	
	public Assignment(int courseID, String title, String dueDate, boolean active){
		this.courseID = courseID;
		this.title = title;
		this.active = active;
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
	
	public String toString()
	{
		String s = title;
		if(active)s += "   (active)";
		s += "  DUE: " + dueDate;
		return s;
	}
}
