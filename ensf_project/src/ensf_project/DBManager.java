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

	public String connectionInfo = "jdbc:mysql://localhost:3306/LearningPlatform",
	  			  login          = "root",
	  			  password       = "####";
	
	public DBManager()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
