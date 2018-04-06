package ensf_project;

import java.io.Serializable;

public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int profID;
	private boolean active;
	private String name;
	
	public Course(int profID, String name) {
		this.profID = profID;
		this.name = name;
		active = true;
	}
		
	public int getProfID() {
		return profID;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public String getName() {
		return name;
	}
}
