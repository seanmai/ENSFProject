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

public class Client {
	private Socket theSocket;
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
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
	
	public PrintWriter getSocketOut()
	{
		return socketOut;
	}
	
	public ObjectInputStream getFromServer()
	{
		return fromServer;
	}
	
	public ObjectOutputStream getToServer()
	{
		return toServer;
	}
	
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
	
	public Vector<User> getEnrolledStudentList(String course)
	{
		socketOut.println("GET COURSE STUDENTS");
		socketOut.flush();
		Vector<User>items = null;
			
		try {
			items = (Vector<User>)fromServer.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		Vector<User> users = new Vector<User>();
		
		for(int i = 0; i < items.size(); i++) {
			if(isEnrolled(items.get(i), course))
				users.add(items.get(i));
		}
		
		return users;
	}
	
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
	
	public void courseStatus(String course, String s)
	{
			socketOut.println("CHANGE COURSE STATUS");
			socketOut.println(course);
			socketOut.println(s);
			socketOut.flush();
	}

	public void studentEnrollment(String student, String courseName, String s)
	{
		socketOut.println("STUDENT ENROLLMENT");
		socketOut.println(student.split(" ")[0]);
		socketOut.println(courseName);
		socketOut.println(s);
		socketOut.flush();
	}
		
	public void assignmentStatus(String assignment, String s)
	{
		socketOut.println("CHANGE ASSIGNMENT STATUS");
		socketOut.println(assignment);
		socketOut.println(s);
		socketOut.flush();
	}
	
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