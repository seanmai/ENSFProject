package ensf_project.src;

import java.io.Serializable;

public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int profID;
	private boolean active;
	private String name;
	
	public Course(int id, int profID, String name, boolean a) {
		this.profID = profID;
		this.name = name;
		active = a;
		this.id = id;
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
	
	public int getID()
	{
		return id;
	}
	
	public String toString()
	{
		String s = name;
		if(active)s += "   (active)";
		return s;
	}
}
