package ensf_project;
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
	
	/**
	 * Retrieves all users with the specified id from the database
	 * @param userID the id to search
	 * @return a vector of all clients matching the id
	 */
	public Vector <User> searchClientByID(int clientID) {
		String sql = "SELECT * FROM " + userTable + " WHERE ID= ?";
		Vector <User> results = new Vector <User>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, clientID);
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
	
	public User getUser(int clientID, String password) {
		String sql = "SELECT * FROM " + userTable + " WHERE ID= ?"
				+ " AND PASSWORD= ?";
		ResultSet query;
		User user = null;
		
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, clientID);
			pStatement.setString(2, password);
			query = pStatement.executeQuery();
			query.next();
			
			user = new User(query.getInt("ID"),
					 		query.getString("PASSWORD"),
					 		query.getString("EMAIL"),
					 		query.getString("FIRSTNAME"),
					 		query.getString("LASTNAME"),
					 		query.getString("TYPE"));
		} catch (SQLException e) {e.printStackTrace();}
		
		return user;
	}
	
	public static void main(String[] args) {
		DBManager m = new DBManager();
		//m.createDB();
		//m.createUserTable();
		//m.createAssignmentTable();

	}

}
