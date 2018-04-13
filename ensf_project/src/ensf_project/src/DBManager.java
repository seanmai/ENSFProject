package ensf_project.src;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Contains methods to create and query MySQL database
 * 
 * @author Wafa Anam, Sea Main, Matt Kadatz
 * @version 1.0
 * @since April 5, 2018
 */
public class DBManager {
	/**
	 * jdbc connection to connect to mySQL
	 */
	public Connection jdbc_connection;
	
	/**
	 * Prepared statement
	 */
	public PreparedStatement pStatement;
	
	/**
	 * name of created database
	 */
	public String databaseName = "LearningPlatformDB";
	
	/**
	 * name of tables created in database
	 */
	public String userTable = "userTable",
				  assignmentTable = "assignmentTable",
				  gradeTable = "gradeTable",
				  courseTable = "courseTable",
				  studentEnrollmentTable = "studentEnrollmentTable",
				  submissionTable = "submissionTable";
	
	/**
	 * name of input file for users
	 */
	public String userDataFile = "users.txt";

	/**
	 * Connection credentials for mySQL
	 */
	public String connectionInfo = "jdbc:mysql://localhost:3306/LearningPlatformDB?autoReconnect=true&useSSL=false",
	  			  login          = "root",
	  			  password       = "Thirteen13!";
	
	/**
	 * Connection handler for database 
	 */
	public DBManager() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * Creates a database
	 */
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
	
	/**
	 * Creates a user table in the database
	 */
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
	
	/**
	 * Populates user table in the database from input file
	 */
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
	
	/**
	 * Adds user rows into the user table
	 */
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
	 * Queries users from user table
	 * @param userID id of user
	 * @return The user found in the user table matching the query
	 */
	public User searchUserByID(int userID) {
		String sql = "SELECT * FROM " + userTable + " WHERE ID= ?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, userID);
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
	 * Queries student users with the specified id from the database
	 * @param userID the id to search
	 * @return The user found in the user table matching the query
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
	 * Retrieves all student users with the specified id from the database
	 * @param userID the id to search
	 * @return a vector of all student users
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
	
	/**
	 * Queries professor users from user table joined to course table
	 * @param courseID the id of the course
	 * @return The user found in the user table and course table matching the query
	 */
	public User searchProfByIDFromCourse(int courseID) {
		String sql = "SELECT * FROM " + courseTable + " WHERE ID= ?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, courseID);
			ResultSet course = pStatement.executeQuery();
			if(course.next())
			{	
				return searchUserByID(course.getInt("PROF_ID"));			  	 
			}

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Queries users from user table with the specified last name
	 * @param lastName the last name of the user
	 * @return The user found in the user table matching the query
	 */
	public User searchStudentByName(String lastName) {
		String sql = "SELECT * FROM " + userTable + " WHERE LASTNAME= ?" + " and TYPE=?";
		User result = null;
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setString(1, lastName);
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
			return result;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Creates assignment table in the database
	 */
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
	
	/**
	 * Adds assignment row to the assignment table
	 * @param assignment the assignment being added to the table
	 */
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
	
	/**
	 * Queries all assignments under a given course name
	 * @param courseName the course associated with the assignments
	 * @return A vector of all assignments matching the query
	 */
	public Vector<Assignment> getAssignments(String courseName)
	{	
		int courseID = getCourseID(courseName);
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
									 assign.getInt("COURSE_ID"),
									 assign.getString("TITLE"),
									 assign.getString("PATH"),
									 assign.getString("DUE_DATE"),
									 assign.getBoolean("ACTIVE")));	
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Queries all assignments that are active
	 * @param courseID the course ID associated with the assignments
	 * @return A vector of all assignments matching the query
	 */
	public Vector<Assignment> getActiveAssignments(int courseID){
		String sql = "SELECT * FROM " + assignmentTable + " WHERE COURSE_ID= ?";
		Vector <Assignment> results = new Vector <Assignment>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, courseID);
			ResultSet assign = pStatement.executeQuery();
			while(assign.next())
			{
				if(assign.getBoolean("ACTIVE")) {
					results.add(new Assignment(assign.getInt("ID"),
							 assign.getInt("COURSE_ID"),
							 assign.getString("TITLE"),
							 assign.getString("PATH"),
							 assign.getString("DUE_DATE"),
							 assign.getBoolean("ACTIVE")));	
				}
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Queries courses matching the given name
	 * @param courseName the name of the course
	 * @return the ID of the course matching the query
	 */
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
	
	/**
	 * Queries and updates assignment to a new status given an assignment name
	 * @param assignmentName the name of the assignment to be queried
	 * @param status the active status to be set
	 */
	public void updateAssignmentStatus(String assignmentName, boolean status) {
		String sql = "UPDATE " + assignmentTable + " SET ACTIVE=?" + " WHERE TITLE=?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setBoolean(1, status);
			pStatement.setString(2, assignmentName);
			pStatement.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Creates a grade table in the database
	 */
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
	
	/**
	 * Adds a grade row to the grade table
	 * @param assignID the assignment id associated with the grade
	 * @param studentID the student id associated with the grade
	 * @param courseID the course id associated with the grade
	 * @param grade the grade of the assignment
	 */
	public void addGrade(int assignID, int studentID, int courseID, int grade) {
		String sql = "INSERT INTO " + gradeTable +
				" VALUES (?, ?, ?, ?, ?)";
		int id = dbSize(gradeTable)+1;
		
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			
			pStatement.setInt(1, id);
			pStatement.setInt(2, assignID);
			pStatement.setInt(3, studentID);
			pStatement.setInt(4, courseID);
			pStatement.setInt(5, grade);
			pStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Queries for grade of an assignment of a student user
	 * @param assignID the assignment id associated with the grade
	 * @param studentID the student user associated with the grade
	 * @return The grade of the assignment
	 */
	public int searchGrade(int assignID, int studentID) {
		String sql = "SELECT * FROM " + gradeTable + " WHERE ASSIGN_ID= ?" + " and STUDENT_ID=?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, assignID);
			pStatement.setInt(2, studentID);
			ResultSet grade = pStatement.executeQuery();
			if(grade.next())
			{
				return grade.getInt("ASSIGNMENT_GRADE");							  	 
			}

		} catch (SQLException e) { e.printStackTrace(); }

		return 0;
	}
	
	public void updateGrade(int assignID, int studentID, int grade) {
		String sql = "UPDATE " + gradeTable + " SET ASSIGNMENT_GRADE=?" + " WHERE ASSIGN_ID=?" + " and STUDENT_ID=?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, grade);
			pStatement.setInt(2, assignID);
			pStatement.setInt(3, studentID);
			pStatement.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Queries for a grade and updates it
	 * @param studentID student id associated with the grade
	 * @param courseID course id associated with the grade
	 * @return
	 */
	public double getCourseGrade(int studentID, int courseID) {
		String sql = "SELECT * FROM " + gradeTable + " WHERE STUDENT_ID= ?" + " and COURSE_ID=?";
		double total = 0;
		int count = 0;
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, studentID);
			pStatement.setInt(2, courseID);
			ResultSet grade = pStatement.executeQuery();
			if(grade.next())
			{
				total += grade.getInt("ASSIGNMENT_GRADE");
				count++;
			}
			return total/count;

		} catch (SQLException e) { e.printStackTrace(); }

		return total;
	}
	
	/**
	 * Creates a course table in the database
	 */
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
	
	/**
	 * adds a course row to the course table
	 * @param course the course being added to the row
	 */
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
	
	/**
	 * Queries for a course and updates the status
	 * @param courseName the name of the course
	 * @param status the status to be updated to
	 */
	public void updateCourseStatus(String courseName, boolean status) {
		String sql = "UPDATE " + courseTable + " SET ACTIVE=?" + " WHERE NAME=?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setBoolean(1, status);
			pStatement.setString(2, courseName);
			pStatement.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Queries for courses associated with a professor user
	 * @param profID the id of the associated professor
	 * @return A vector of courses matching the query
	 */
	public Vector <Course> searchCourses(int profID) {
		String sql = "SELECT * FROM " + courseTable + " WHERE PROF_ID= ?";
		Vector <Course> results = new Vector <Course>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, profID);
			ResultSet courses = pStatement.executeQuery();
			while(courses.next())
			{
				results.add(new Course(courses.getInt("ID"),
						courses.getInt("PROF_ID"),
						courses.getString("NAME"),
						courses.getBoolean("ACTIVE")));
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Queries courses from a given course name
	 * @param name the name of the course
	 * @return A course matching the given query
	 */
	public Course searchCourseByName(String name) {
		String sql = "SELECT * FROM " + courseTable + " WHERE NAME= ?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setString(1, name);
			ResultSet course = pStatement.executeQuery();
			if(course.next())
			{
				return new Course(
						course.getInt("ID"),
						course.getInt("PROF_ID"),
						course.getString("NAME"),
						course.getBoolean("ACTIVE"));							  	 
			}

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Queries courses from a given course ID
	 * @param courseID the id of the course
	 * @return The course matching the given query
	 */
	public Course searchCourseByID(int courseID) {
		String sql = "SELECT * FROM " + courseTable + " WHERE ID= ?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, courseID);
			ResultSet course = pStatement.executeQuery();
			if(course.next())
			{
				return new Course(
						course.getInt("ID"),
						course.getInt("PROF_ID"),
						course.getString("NAME"),
						course.getBoolean("ACTIVE"));							  	 
			}

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Creates a student enrollment table in the database
	 */
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
		
	/**
	 * Queries for all active courses associated with the a student
	 * @param studentID the id of the associated student
	 * @return A vector of courses that match the query
	 */
	public Vector <Course> getEnrolledCourses(int studentID) {
		String sql = "SELECT * FROM " + studentEnrollmentTable + " WHERE STUDENT_ID= ?";
		Vector <Course> results = new Vector <Course>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, studentID);
			ResultSet courses = pStatement.executeQuery();
			while(courses.next())
			{
				Course c = searchCourseByID(courses.getInt("COURSE_ID"));
				if(c.isActive()) {
					results.add(c);
				}
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;		
	}
	
	/**
	 * Queries for all students enrolled in a course
	 * @param courseID the id of the associated course
	 * @return A vector of student users matching the query
	 */
	public Vector <User> getEnrolledStudents(int courseID) {
		String sql = "SELECT * FROM " + studentEnrollmentTable + " WHERE COURSE_ID= ?";
		Vector <User> results = new Vector <User>();
		
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, courseID);
			ResultSet courses = pStatement.executeQuery();
			while(courses.next())
			{
				User u = searchUserByID(courses.getInt("STUDENT_ID"));
				results.add(u);
			}
			
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;		
	}
	
	/**
	 * Adds a student enrollment row to the student enrollment table
	 * @param studentID the id of the student being added
	 * @param courseName the name of the course the student is being enrolled into
	 */
	public void addStudentEnrollment(int studentID, String courseName) {
		int courseID = getCourseID(courseName);
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
	
	/**
	 * Removes a student from the student enrollment table
	 * @param studentID id of student being removed
	 * @param courseName name of the course the student is being removed from
	 */
	public void removeStudentEnrollment(int studentID, String courseName) {
		int courseID = getCourseID(courseName);
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
	
	/**
	 * Checks if the student is enrolled in a course
	 * @param studentID the id of the student
	 * @param courseName the name of the associated course
	 * @return if the student is enrolled in the course
	 */
	public boolean checkStudentEnrollment(int studentID, String courseName)
	{
		int courseID = getCourseID(courseName);
		String sql = "SELECT 1 FROM " + studentEnrollmentTable +
				" WHERE STUDENT_ID=?" + " and COURSE_ID=?";
		
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, studentID);
			pStatement.setInt(2, courseID);
			ResultSet enrolled = pStatement.executeQuery();
			
			if(!enrolled.isBeforeFirst())return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Creates a submission table in the database
	 */
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
	 * Adds a submission row to the submission table
	 * @param submission submission to be added
	 */
	public void addSubmission(Submission submission) {
		String sql = "INSERT INTO " + submissionTable +
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int id = dbSize(submissionTable)+1;
		
		try{
			pStatement = jdbc_connection.prepareStatement(sql);
			
			pStatement.setInt(1, id);
			pStatement.setInt(2, submission.getAssignID());
			pStatement.setInt(3, submission.getStudentID());
			pStatement.setString(4, submission.getPath());
			pStatement.setString(5, submission.getTitle());
			pStatement.setInt(6, submission.getSubmissionGrade());
			pStatement.setString(7, submission.getComments());
			pStatement.setString(8, submission.getTimestamp());
			pStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Queries for all submissions of an assignment
	 * @param assignID id of the associated assignment
	 * @return A vector of all submissions matching the query
	 */
	public Vector<Submission> getSubmissions(int assignID) {
		String sql = "SELECT * FROM " + submissionTable + " WHERE ASSIGN_ID= ?";
		Vector <Submission> results = new Vector <Submission>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, assignID);
			ResultSet submission = pStatement.executeQuery();
			while(submission.next())
			{
				results.add(new Submission(submission.getInt("ID"),
									 submission.getInt("ASSIGN_ID"),
									 submission.getInt("STUDENT_ID"),
									 submission.getString("PATH"),
									 submission.getString("TITLE"),
									 submission.getInt("SUBMISSION_GRADE"),
									 submission.getString("COMMENTS"),
									 submission.getString("TIMESTAMP")));	
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Queries for all submissions of a student
	 * @param assignID id of associated assignment
	 * @param studentID id of the associated student
	 * @return A vector of all submissions matching the query
	 */
	public Vector<Submission> getSubmissionsByStudentID(int assignID, int studentID) {
		String sql = "SELECT * FROM " + submissionTable + " WHERE ASSIGN_ID= ?" + " and STUDENT_ID=?";
		Vector <Submission> results = new Vector <Submission>();
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, assignID);
			pStatement.setInt(2, studentID);
			ResultSet submission = pStatement.executeQuery();
			while(submission.next())
			{
				results.add(new Submission(submission.getInt("ID"),
									 submission.getInt("ASSIGN_ID"),
									 submission.getInt("STUDENT_ID"),
									 submission.getString("PATH"),
									 submission.getString("TITLE"),
									 submission.getInt("SUBMISSION_GRADE"),
									 submission.getString("COMMENTS"),
									 submission.getString("TIMESTAMP")));	
			}
			return results;

		} catch (SQLException e) { e.printStackTrace(); }

		return null;
	}
	
	/**
	 * Queries and updates a submission with a new submission grade
	 * @param submissionID id of the submission
	 * @param grade grade to be reassigned to the submission
	 */
	public void updateSubmissionGrade(int submissionID, int grade) {
		String sql = "UPDATE " + submissionTable + " SET SUBMISSION_GRADE=?" + " WHERE ID=?";
		try {
			pStatement = jdbc_connection.prepareStatement(sql);
			pStatement.setInt(1, grade);
			pStatement.setInt(2, submissionID);
			pStatement.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Method to get the number of rows in a table
	 * @param tableName name of the table
	 * @return the number of rows in the table
	 */
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
		
//		db.createDB();
//		db.createUserTable();
//		db.createAssignmentTable();
//		db.createCourseTable();
//		db.createGradeTable();
//		db.createStudentEnrollmentTable();
//		db.createSubmissionTable();
//		db.fillUserTable();

	}

}
