package ensf_project.src;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

public class DBManager {
	public Connection jdbc_connection;
	public PreparedStatement pStatement;
	public String databaseName = "LearningPlatformDB"; 
	public String userTable = "userTable",
				  assignmentTable = "assignmentTable",
				  gradeTable = "gradeTable",
				  courseTable = "courseTable",
				  studentEnrollmentTable = "studentEnrollmentTable",
				  submissionTable = "submissionTable";
	public String userDataFile = "users.txt";

	public String connectionInfo = "jdbc:mysql://localhost:3306/LearningPlatformDB",
	  			  login          = "root",
	  			  password       = "Thirteen13!";
	
	public DBManager() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	
	public void createDB() {
		try {
			String sql = "CREATE DATABASE " + databaseName;
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.executeUpdate();
			System.out.println("Created Database " + databaseName);
		} 
		catch( SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUserTable()
	{
		String sql = "CREATE TABLE " + userTable + "(" +
				     "ID INT(8) NOT NULL, " +
				     "PASSWORD VARCHAR(20) NOT NULL, " +
				     "EMAIL VARCHAR(50) NOT NULL, " +
				     "FIRSTNAME VARCHAR(30) NOT NULL, " +
				     "LASTNAME VARCHAR(30) NOT NULL, " +
				     "TYPE CHAR(1) NOT NULL, " +  
				     "PRIMARY KEY ( id ))";
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.executeUpdate();
			System.out.println("Created Table " + userTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void fillUserTable()
	{
		try{
			Scanner sc = new Scanner(new FileReader(userDataFile));
			while(sc.hasNext())
			{
				String userInfo[] = sc.nextLine().split(";");
				addUser( new User( Integer.parseInt(userInfo[0]),
								   					userInfo[1],
						                            userInfo[2],
						                            userInfo[3],
						                            userInfo[4],
						                            userInfo[5]) );
			}
			sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("File " + userDataFile + " Not Found!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addUser(User user) {
		String sql = "INSERT INTO " + userTable +
				" VALUES (?, ?, ?, ?, ?, ?)";
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, user.getID());
			pStatement.setString(2, user.getPassword());
			pStatement.setString(3, user.getEmail());
			pStatement.setString(4, user.getFirstName());
			pStatement.setString(5, user.getLastName());
			pStatement.setString(6, user.getType());
			pStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves all users with the specified id from the database
	 * @param userID the id to search
	 * @return a vector of all users matching the id
	 */
	public User searchStudentByID(int userID) {
		String sql = "SELECT * FROM " + userTable + " WHERE ID= ?" + " and TYPE=?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, userID);
			pStatement.setString(2, "S");
			ResultSet users = pStatement.executeQuery();
			if(users.next())
			{
				return new User(users.getInt("ID"),
								users.getString("PASSWORD"),
								users.getString("EMAIL"),
								users.getString("FIRSTNAME"),
								users.getString("LASTNAME"),
								users.getString("TYPE"));							  	 
			}

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Retrieves all users with the specified id from the database
	 * @param userID the id to search
	 * @return a vector of all users matching the id
	 */
	public Vector<User> getStudents() {
		String sql = "SELECT * FROM " + userTable + " WHERE TYPE= ?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setString(1, "S");
			Vector <User> results = new Vector <User>();
			ResultSet users = pStatement.executeQuery();
			while(users.next())
			{
				results.add(new User(users.getInt("ID"),
									 users.getString("PASSWORD"),
									 users.getString("EMAIL"),
									 users.getString("FIRSTNAME"),
									 users.getString("LASTNAME"),
									 users.getString("TYPE")));							  	 
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	public User searchUserByIDFromCourse(int userID) {
		String sql = "SELECT * FROM " + userTable + ", " + courseTable + " WHERE ID= ?" + "and " + userTable + ".ID=" + courseTable + ".ID";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, userID);
			ResultSet users = pStatement.executeQuery();
			while(users.next())
			{
				return new User(users.getInt("ID"),
								users.getString("PASSWORD"),
								users.getString("EMAIL"),
								users.getString("FIRSTNAME"),
								users.getString("LASTNAME"),
								users.getString("TYPE"));							  	 
			}

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	public Vector <User> searchStudentByName(String lastName) {
		String sql = "SELECT * FROM " + userTable + " WHERE LASTNAME= ?" + " and TYPE=?";
		Vector <User> results = new Vector <User>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setString(1, lastName);
			pStatement.setString(2, "S");
			ResultSet users = pStatement.executeQuery();
			while(users.next())
			{
				results.add(new User(users.getInt("ID"),
									 users.getString("PASSWORD"),
									 users.getString("EMAIL"),
									 users.getString("FIRSTNAME"),
									 users.getString("LASTNAME"),
									 users.getString("TYPE")));							  	 
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	public void createAssignmentTable() {
		String sql = "CREATE TABLE " + assignmentTable + "(" +
			     "ID INT(8) NOT NULL, " +
			     "COURSE_ID INT(8) NOT NULL, " +
			     "TITLE VARCHAR(50) NOT NULL, " +
			     "PATH VARCHAR(100) NOT NULL, " +
			     "ACTIVE BIT(1) NOT NULL, " +
			     "DUE_DATE CHAR(16) NOT NULL, " +  
			     "PRIMARY KEY ( id ))";
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.executeUpdate();
			System.out.println("Created Table " + assignmentTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void addAssignment(Assignment assignment) {
		String sql = "INSERT INTO " + assignmentTable +
				" VALUES (?, ?, ?, ?, ?, ?)";
		int id = dbSize(assignmentTable)+1;
		
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			
			pStatement.setInt(1, id);
			pStatement.setInt(2, assignment.getCourseID());
			pStatement.setString(3, assignment.getTitle());
			pStatement.setString(4, assignment.getPath());
			pStatement.setBoolean(5, assignment.isActive());
			pStatement.setString(6, assignment.getDueDate());
			pStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Vector<Assignment> getAssignments(String courseName)
	{	int courseID = getCourseID(courseName);
		if (courseID == -1) return null;
		String sql = "SELECT * FROM " + assignmentTable + " WHERE COURSE_ID= ?";
		Vector <Assignment> results = new Vector <Assignment>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, courseID);
			ResultSet assign = pStatement.executeQuery();
			while(assign.next())
			{
				results.add(new Assignment(assign.getInt("ID"),
									 assign.getString("TITLE"),
									 assign.getString("DUE_DATE")));						  	 
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	public int getCourseID(String courseName)
	{
		String sql = "SELECT * FROM " + courseTable + " WHERE NAME= ?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setString(1, courseName);
			ResultSet users = pStatement.executeQuery();
			if(users.next())
			{
				return users.getInt("ID");							  	 
			}

		} catch (SQLException e) { e.printStackTrace(); }
	
		return -1;
	}
	
	public void updateAssignmentStatus(String assignmentName, boolean status) {
		String sql = "UPDATE " + assignmentTable + " SET ACTIVE=?" + " WHERE TITLE=?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setBoolean(1, status);
			pStatement.setString(2, assignmentName);
			pStatement.executeQuery();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public void createGradeTable() {
		String sql = "CREATE TABLE " + gradeTable + "(" +
			     "ID INT(8) NOT NULL, " +
			     "ASSIGN_ID INT(8) NOT NULL, " +
			     "STUDENT_ID INT(8) NOT NULL, " +
			     "COURSE_ID INT(8) NOT NULL, " +
			     "ASSIGNMENT_GRADE INT(3) NOT NULL, " +  
			     "PRIMARY KEY ( id ))";
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.executeUpdate();
			System.out.println("Created Table " + gradeTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void createCourseTable() {
		String sql = "CREATE TABLE " + courseTable + "(" +
			     "ID INT(8) NOT NULL, " +
			     "PROF_ID INT(8) NOT NULL, " +
			     "NAME VARCHAR(50) NOT NULL, " +
			     "ACTIVE BIT(1) NOT NULL, " +  
			     "PRIMARY KEY ( id ))";
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.executeUpdate();
			System.out.println("Created Table " + courseTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void addCourse(Course course) {
		String sql = "INSERT INTO " + courseTable +
				" VALUES (?, ?, ?, ?)";
		int id = dbSize(courseTable)+1;
		
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			
			pStatement.setInt(1, id);
			pStatement.setInt(2, course.getProfID());
			pStatement.setString(3, course.getName());
			pStatement.setBoolean(4, course.isActive());
			pStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateCourseStatus(String courseName, boolean status) {
		String sql = "UPDATE " + courseTable + " SET ACTIVE=?" + " WHERE NAME=?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setBoolean(1, status);
			pStatement.setString(2, courseName);
			pStatement.executeQuery();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public Vector <Course> searchCourses(int profID) {
		String sql = "SELECT * FROM " + courseTable + " WHERE PROF_ID= ?";
		Vector <Course> results = new Vector <Course>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, profID);
			ResultSet courses = pStatement.executeQuery();
			while(courses.next())
			{
				results.add(new Course(courses.getInt("PROF_ID"),
							  	 courses.getString("NAME"),
							  	 courses.getBoolean("ACTIVE")));
			}
			System.out.println(results.get(0).getName());
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	public Course searchCourseByName(String name) {
		String sql = "SELECT * FROM " + userTable + " WHERE NAME= ?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setString(1, name);
			ResultSet course = pStatement.executeQuery();
			if(course.next())
			{
				return new Course(course.getInt("PROF_ID"),
					  	 		  course.getString("NAME"),
					  	 		  course.getBoolean("ACTIVE"));							  	 
			}

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	public void createStudentEnrollmentTable() {
		String sql = "CREATE TABLE " + studentEnrollmentTable + "(" +
			     "ID INT(8) NOT NULL, " +
			     "STUDENT_ID INT(8) NOT NULL, " +
			     "COURSE_ID INT(8) NOT NULL, " +  
			     "PRIMARY KEY ( id ))";
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.executeUpdate();
			System.out.println("Created Table " + studentEnrollmentTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void addStudentEnrollment(int studentID, int courseID) {
		String sql = "INSERT INTO " + studentEnrollmentTable +
				" VALUES (?, ?, ?)";
		int id = dbSize(studentEnrollmentTable)+1;
		
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			
			pStatement.setInt(1, id);
			pStatement.setInt(2, studentID);
			pStatement.setInt(3, courseID);
			pStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void removeStudentEnrollment(int studentID, int courseID) {
		String sql = "DELETE FROM " + studentEnrollmentTable +
				" WHERE STUDENT_ID=?" + " and COURSE_ID=?";
		
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			
			pStatement.setInt(1, studentID);
			pStatement.setInt(2, courseID);
			pStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void createSubmissionTable() {
		String sql = "CREATE TABLE " + submissionTable + "(" +
			     "ID INT(8) NOT NULL, " +
			     "ASSIGN_ID INT(8) NOT NULL, " +
			     "STUDENT_ID INT(8) NOT NULL, " +
			     "PATH VARCHAR(100) NOT NULL, " +
			     "TITLE VARCHAR(50) NOT NULL, " +
			     "SUBMISSION_GRADE INT(3) NOT NULL, " +
			     "COMMENTS VARCHAR(140) NOT NULL, " +
			     "TIMESTAMP CHAR(16) NOT NULL, " +  
			     "PRIMARY KEY ( id ))";
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.executeUpdate();
			System.out.println("Created Table " + submissionTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	private int dbSize(String tableName) {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM " + tableName;
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			ResultSet size = pStatement.executeQuery();
			while(size.next())
			{
				count = size.getInt(1);
			}
			size.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		DBManager m = new DBManager();
//		Assignment assign = new Assignment(1, "Final Project.docx", "May 15, 10:00pm");
//		assign.setPath("");
		//m.createDB();
		//m.createUserTable();
		//m.createAssignmentTable();
//		m.addAssignment(assign);

	}

}
