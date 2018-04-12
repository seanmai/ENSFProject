package ensf_project.src;

import java.io.Serializable;

public class Assignment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
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
	public Assignment(int id, int courseID, String title, String path, String dueDate, boolean active){
		this.id = id;
		this.courseID = courseID;
		this.title = title;
		this.path = path;
		this.active = active;
		this.dueDate = dueDate;
	}
	
	public void setPath(String p)
	{
		path = p;
	}
	
	public int getID()
	{
		return id;
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
