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
	
	public int getID()
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
	
	public boolean getStatus()
	{
		return active;
	}

	public String getDueDate()
	{
		return dueDate;
	}
}
