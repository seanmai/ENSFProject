package ensf_project.src;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;

/**
 * Contains all the Methods and fields to send 
 * the proper Strings through the sockets to 
 * communicate with the Server
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class Client {
	
	/**
	 * Socket Used for Communication
	 */
	private Socket theSocket;
	
	/**
	 * Socket Object Output Stream
	 */
	private ObjectOutputStream toServer;
	
	/**
	 * Socket Object Input Stream
	 */
	private ObjectInputStream fromServer;
	
	/**
	 * Socket Input String Stream
	 */
	private BufferedReader socketIn;
	
	/**
	 * Socket Output String Stream
	 */
	private PrintWriter socketOut;
	
	/**
	 * Client Constructor
	 * @param hostname
	 * @param port
	 */
	public Client(String hostname, int port) {
		try {
		theSocket = new Socket(hostname, port);
		toServer = new ObjectOutputStream(theSocket.getOutputStream());
		fromServer = new ObjectInputStream(theSocket.getInputStream());
		socketIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
		socketOut = new PrintWriter(theSocket.getOutputStream(), true);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Uploads an Assignment file to the Server/Database
	 * @param course
	 * @param dueDate
	 */
	public void upload(Course course, String dueDate)
	{
		File selectedFile = null;
		JFileChooser fileBrowser = new JFileChooser();
		if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			selectedFile = fileBrowser.getSelectedFile();

		long length = selectedFile.length();
		byte[] content = new byte[(int) length]; // Converting Long to Int

		try {
			FileInputStream fis = new FileInputStream(selectedFile);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int)length);
			
			socketOut.println("STORE FILE");
			toServer.writeObject(content);
			toServer.flush();
			toServer.writeObject(new Assignment(course.getID(), selectedFile.getName(), dueDate, true));

			//client.getSocketOut().println(new Assignment());
			socketOut.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * Uploads A student Submission to the Server/
	 * Database
	 * @param a
	 * @param s
	 */
	public void uploadSubmission(Assignment a, User s)
	{
		File selectedFile = null;
		JFileChooser fileBrowser = new JFileChooser();
		if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			selectedFile = fileBrowser.getSelectedFile();

		long length = selectedFile.length();
		byte[] content = new byte[(int) length]; // Converting Long to Int

		try {
			FileInputStream fis = new FileInputStream(selectedFile);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int)length);
			
			socketOut.println("STORE SUBMISSION");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm");
			LocalDateTime now = LocalDateTime.now();
			String timeStamp = dtf.format(now);
			toServer.writeObject(content);
			toServer.flush();
			toServer.writeObject(new Submission(a.getID(), s.getID(), selectedFile.getName(), timeStamp));

			//client.getSocketOut().println(new Assignment());
			socketOut.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * Retrieves the File from the Server/Database
	 * @param path
	 * @param name
	 */
	public void getFile(String path, String name)
	{
		socketOut.println("GET FILE");
		socketOut.println(path);
		socketOut.println(name);
		socketOut.flush();
		try {
			byte[] content = (byte[]) fromServer.readObject();
			String absolutepath = "C:\\Users\\Wafa\\Downloads\\";
			
			
			File newFile = new File(absolutepath + name);
			if(! newFile.exists())
				newFile.createNewFile();
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(content);
			bos.close();
			
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the Professor from the specified Course
	 * @param courseID
	 * @return User Prof
	 */
	public User getCourseProf(int courseID) {
		socketOut.println("SEARCH COURSE PROF");
		socketOut.println(courseID);
		socketOut.flush();
		User prof = null;
		
		try {
			prof = (User) fromServer.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return prof;
	}
	
	/**
	 * Sends an email to a string of recipients
	 * @param recipientEmails
	 * @param subject
	 * @param body
	 */
	public void sendEmail(Vector<String> recipientEmails, String subject, String body) {
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, 
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ensf409Project@gmail.com",  "ensf409password");
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ensf409Project@gmail.com"));
			
			for(int i = 0; i < recipientEmails.size(); i++) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipientEmails.get(i)));
			}
			
			message.setSubject(subject);
			message.setText(body);
		}catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the Socket Output Stream
	 * @return
	 */
	public PrintWriter getSocketOut()
	{
		return socketOut;
	}
	
	/**
	 * Gets the Socket Object Input Stream
	 * @return
	 */
	public ObjectInputStream getFromServer()
	{
		return fromServer;
	}
	
	/**
	 * Gets the Socket Object Output Stream
	 * @return
	 */
	public ObjectOutputStream getToServer()
	{
		return toServer;
	}
	
	/**
	 * Creates A new Course in the DataBase
	 * @param c
	 */
	public void createCourse(Course c)
	{
		try {
		socketOut.println("ADD COURSE");
		toServer.writeObject(c);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Gets the List of courses taught by the 
	 * specified Professor
	 * @param id
	 * @return Prof Course List
	 */
	public Vector<Course> ProfessorCourseList(int id) {
		socketOut.println("GET PROF COURSE LIST");
		socketOut.println(id);
		socketOut.flush();
		
		Vector<Course>items = null;
		
		try {
			items = (Vector<Course>)fromServer.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	/**
	 * Gets The list of Students enrolled in a
	 * specified Course
	 * @param courseID
	 * @return List of Students enrolled in course
	 */
	public Vector<String> getEnrolledStudentList(int courseID)
	{
		socketOut.println("GET ENROLLED COURSE STUDENTS");
		socketOut.println(courseID);
		socketOut.flush();
		Vector<User>users = new Vector<User>();
			
		try {
			users = (Vector<User>)fromServer.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		Vector<String> userEmails = new Vector<String>();
		
		for(int i = 0; i < users.size(); i++) {
			userEmails.add(users.get(i).getEmail());
		}
		
		return userEmails;
	}
	
	/**
	 * Gets the Course List for A specified Student
	 * @param id
	 * @return list of Courses student is enrolled in
	 */
	public Vector<Course> studentCourseList(int id) {
		socketOut.println("GET STUD COURSE LIST");
		socketOut.println(id);
		socketOut.flush();
		
		Vector<Course>items = null;
		
		try {
			items = (Vector<Course>)fromServer.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	/**
	 * Gets the status of the course (Active/Inactive)
	 * @param course
	 * @param s
	 */
	public void courseStatus(String course, String s)
	{
			socketOut.println("CHANGE COURSE STATUS");
			socketOut.println(course);
			socketOut.println(s);
			socketOut.flush();
	}
	
	/**
	 * Updates the Students enrollment in a specified Course
	 * @param student
	 * @param courseName
	 * @param s
	 */
	public void studentEnrollment(String student, String courseName, String s)
	{
		socketOut.println("STUDENT ENROLLMENT");
		socketOut.println(student.split(" ")[0]);
		socketOut.println(courseName);
		socketOut.println(s);
		socketOut.flush();
	}
		
	/**
	 * Updates the Status of a specified assignment
	 * @param assignment
	 * @param s
	 */
	public void assignmentStatus(String assignment, String s)
	{
		socketOut.println("CHANGE ASSIGNMENT STATUS");
		socketOut.println(assignment);
		socketOut.println(s);
		socketOut.flush();
	}
	
	/**
	 * Gets the Entire Student Body List
	 * @return
	 */
	public Vector<User> getStudentList()
	{
		socketOut.println("GET COURSE STUDENTS");
		socketOut.flush();
		Vector<User>items = null;
			
		try {
			items = (Vector<User>)fromServer.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
			
		return items;
	}
	
	/**
	 * Gets the List of Assignments for a specified
	 * Course
	 * @param courseName
	 * @return List of Assignments for a given course
	 */
	public Vector<Assignment> getAssignmentList(String courseName)
	{
			socketOut.println("GET ASSIGNMENTS");
			socketOut.println(courseName);
			socketOut.flush();
			Vector<Assignment>items = null;
			
			try {
				items = (Vector<Assignment>)fromServer.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}

			return items;
	}
	
	/**
	 * Gets A list of Active assignments in a specified
	 * Course
	 * @param courseName
	 * @return lsit of Active Assignments in course
	 */
	public Vector<Assignment> getActiveAssignmentList(String courseName)
	{
			socketOut.println("GET ACTIVE ASSIGNMENTS");
			socketOut.println(courseName);
			socketOut.flush();
			Vector<Assignment>items = null;
			
			try {
				items = (Vector<Assignment>)fromServer.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}

			return items;
	}
	
	/**
	 * Checks if the Student is enrolled in specified
	 * Course
	 * @param student
	 * @param course
	 * @return True/False
	 */
	public boolean isEnrolled(User student, String course)
	{
		try {
			socketOut.println("CHECK ENROLLMENT");
			socketOut.println(student.getID());
			toServer.flush();
			socketOut.println(course);
			socketOut.flush();
			String status = socketIn.readLine();
			if(status.equals("t"))return true;
		}
		catch(IOException e)
		{

		}
		return false;
	}
	
	/**
	 * Gets A list of a students submissions for a given
	 * assignment
	 * @param a
	 * @param student
	 * @return list of student submissions
	 */
	public Vector<Submission> getSubmissions(Assignment a, User student)
	{
		try {
			socketOut.println("GET STUD SUBS");
			socketOut.println(student.getID());
			socketOut.println(a.getID());
			socketOut.flush();
			return (Vector<Submission>)fromServer.readObject();
		}
		catch(IOException e)
		{

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets List of Submissions for a given Assignment
	 * @param a
	 * @return List of Submissions
	 */
	public Vector<Submission> getSubmissions(Assignment a)
	{
		try {
			socketOut.println("GET SUBS");
			socketOut.println(a.getID());
			socketOut.flush();
			return (Vector<Submission>)fromServer.readObject();
		}
		catch(IOException e)
		{

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets the Grade for a specified Submission
	 * @param submission
	 * @param grade
	 */
	public void setGrade(Submission submission, String grade)
	{
		try {
			socketOut.println("SET GRADE");
			socketOut.println(grade);
			toServer.writeObject(submission);
			socketOut.flush();
			toServer.flush();
		}
		catch(IOException e)
		{

		}
	}
	
	/**
	 * Searches User By input String and 
	 * uses type to determine whether searching
	 * by ID or LastName
	 * @param search
	 * @param type
	 * @return User matching Student
	 */
	@SuppressWarnings("unchecked")
	public User searchUser(String search, int type) {
		if(type == 0) {
			socketOut.println("SEARCH STUDENT ID");
			socketOut.println(search);
			System.out.println("String Sent Thru Socket!");
		}
		else {
			socketOut.println("SEARCH STUDENT LAST NAME");
			socketOut.println(search);
		}
		
		socketOut.flush();
		
		User result = null;
		
		try {
			System.out.println("Waiting on return from Socket!");
			result = (User)fromServer.readObject();
			System.out.println("Recieved from Socket!");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}