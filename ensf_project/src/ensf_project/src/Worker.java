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
import java.util.Vector;

/**
 * Contains methods and fields to Listen and react to 
 * Socket Inputs and write back to the Socket
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class Worker implements Runnable {
	/**
	 * Socket being used
	 */
	private Socket socket;
	
	/**
	 * Socket Strings Input
	 */
	private BufferedReader socketIn;
	
	/**
	 * Socket String Output
	 */
	private PrintWriter socketOut;
	
	/**
	 * Socket Output Stream
	 */
	private ObjectOutputStream objectOut;
	
	/**
	 * Socket Object Input Stream
	 */
	private ObjectInputStream objectIn;
	
	/**
	 * DataBase being used to perform
	 * Database operations
	 */
	private DBManager db;
	
	/**
	 * Constructor for Worker
	 * @param socket
	 */
	public Worker(Socket socket) {
		try {
			this.socket = socket;
			objectIn = new ObjectInputStream(this.socket.getInputStream());
			objectOut = new ObjectOutputStream(this.socket.getOutputStream());
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Thread method to listen for Socket input and
	 * react based on input Strings from socket
	 */
	@Override
	public void run() {
			System.out.println("Worker Up");
			db = new DBManager();
			
			while(true) {
				try {
					String input = socketIn.readLine();
					//Searching for User By ID
					if(input.startsWith("SEARCH USER ID")) {
						int id = Integer.parseInt(socketIn.readLine());
						User result = db.searchUserByID(id);
						objectOut.writeObject(result);
						objectOut.flush();
					}
					
					else if(input.startsWith("ADD COURSE"))
					{
						Course course = (Course)objectIn.readObject();
						db.addCourse(course);
					}
					
					else if(input.startsWith("GET PROF COURSE LIST")) {
						int id = Integer.parseInt(socketIn.readLine());
						Vector <Course> courses = db.searchCourses(id);
						objectOut.writeObject(courses);
						objectOut.flush();
					}
					else if(input.startsWith("GET STUD COURSE LIST")) {
						int id = Integer.parseInt(socketIn.readLine());
						Vector <Course> courses = db.getEnrolledCourses(id);
						objectOut.writeObject(courses);
						objectOut.flush();
					}
					else if(input.startsWith("STORE FILE")) {
						storeFile();
					}
					else if(input.startsWith("STORE SUBMISSION")) {
						storeSubmission();
					}
					else if(input.startsWith("GET COURSE STUDENTS")) {
						objectOut.writeObject(db.getStudents());
						objectOut.flush();
					}
					else if(input.startsWith("GET ASSIGNMENTS")) {
						String course = socketIn.readLine();
						objectOut.writeObject(db.getAssignments(course));
						objectOut.flush();
					}
					else if(input.startsWith("GET ACTIVE ASSIGNMENTS")) {
						String course = socketIn.readLine();
						int courseID = db.getCourseID(course);
						objectOut.writeObject(db.getActiveAssignments(courseID));
						objectOut.flush();
					}
					else if(input.startsWith("CHANGE COURSE STATUS")) {
						String course = socketIn.readLine();
						String status = socketIn.readLine();
						if(status.equals("t"))
						{
							db.updateCourseStatus(course, true);
						}
						else
						{
							db.updateCourseStatus(course, false);
						}
					}
					else if(input.startsWith("STUDENT ENROLLMENT")) {
						int studentID = Integer.parseInt(socketIn.readLine());
						String course = socketIn.readLine();
						String status = socketIn.readLine();
						if(status.equals("e"))
						{
							db.addStudentEnrollment(studentID, course);
						}
						else
						{
							db.removeStudentEnrollment(studentID, course);
						}
					}
					else if(input.startsWith("CHANGE ASSIGNMENT STATUS")) {
						String assign = socketIn.readLine();
						String status = socketIn.readLine();
						if(status.equals("a"))
						{
							db.updateAssignmentStatus(assign, true);
						}
						else
						{
							db.updateAssignmentStatus(assign, false);
						}
					}
					else if(input.startsWith("SEARCH STUDENT ID")) {
						int ID = Integer.parseInt(socketIn.readLine());
						objectOut.writeObject(db.searchStudentByID(ID));
						objectOut.flush();
					}
					else if(input.startsWith("SEARCH STUDENT LAST NAME")) {
						String LastName = socketIn.readLine();
						objectOut.writeObject(db.searchStudentByName(LastName));
						objectOut.flush();
					}
					else if(input.startsWith("CHECK ENROLLMENT"))
					{
						int studentID = Integer.parseInt(socketIn.readLine());
						String course = socketIn.readLine();
						boolean status = db.checkStudentEnrollment(studentID, course);
						if(status)socketOut.println("t");
						else socketOut.println("f");
						socketOut.flush();
					}
					else if(input.startsWith("GET STUD SUBS"))
					{
						int studentID = Integer.parseInt(socketIn.readLine());
						int assignID = Integer.parseInt(socketIn.readLine());
						objectOut.writeObject(db.getSubmissionsByStudentID(assignID, studentID));
						objectOut.flush();
					}
					else if(input.startsWith("GET SUBS"))
					{
						int assignID = Integer.parseInt(socketIn.readLine());
						objectOut.writeObject(db.getSubmissions(assignID));
						objectOut.flush();
					}
					else if(input.startsWith("SEARCH COURSE PROF")) {
						int courseID = Integer.parseInt(socketIn.readLine());
						objectOut.writeObject(db.searchProfByIDFromCourse(courseID));
						objectOut.flush();
					}
					else if(input.startsWith("GET ENROLLED COURSE STUDENTS")) {
						int courseID = Integer.parseInt(socketIn.readLine());
						objectOut.writeObject(db.getEnrolledStudents(courseID));
						objectOut.flush();
					}
					else if(input.startsWith("GET FILE"))
					{
						String path = socketIn.readLine();
						String name = socketIn.readLine();
						
						File selectedFile = new File(path, name); 
						sendFile(selectedFile);
					}
					else if(input.startsWith("SET GRADE"))
					{
						int grade = Integer.parseInt(socketIn.readLine());
						Submission s = (Submission)objectIn.readObject();
						db.updateSubmissionGrade(s.getAssignID(), grade);
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}	
	}
	
	/**
	 * Stores files and Calls the DataBase Manager to store
	 * File PathName
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void storeFile() throws ClassNotFoundException, IOException
	{
		byte[] content = (byte[]) objectIn.readObject();
		String path = "C:\\Users\\Wafa\\Documents\\ENSF_files\\";
		
		Assignment a = (Assignment)objectIn.readObject();
		
		File newFile = new File(path + a.getTitle());
		if(! newFile.exists())
			newFile.createNewFile();
		FileOutputStream writer = new FileOutputStream(newFile);
		BufferedOutputStream bos = new BufferedOutputStream(writer);
		bos.write(content);
		bos.close();
		a.setPath(path);
		db.addAssignment(a);
	}
	
	/**
	 * Stores User Submissions on the Server side and calls Database
	 * Manager to store the file PathName
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void storeSubmission() throws ClassNotFoundException, IOException
	{
		byte[] content = (byte[]) objectIn.readObject();
		String path = "C:\\Users\\Wafa\\Documents\\ENSF_files\\";
		
		Submission s = (Submission)objectIn.readObject();
		
		File newFile = new File(path + s.getTitle());
		if(! newFile.exists())
			newFile.createNewFile();
		FileOutputStream writer = new FileOutputStream(newFile);
		BufferedOutputStream bos = new BufferedOutputStream(writer);
		bos.write(content);
		bos.close();
		s.setPath(path);
		db.addSubmission(s);
	}
	
	/**
	 * Sends a file from specified path provided by DataBase Manager
	 * @param selectedFile
	 */
	public void sendFile(File selectedFile)
	{
		long length = selectedFile.length();
		byte[] content = new byte[(int) length]; // Converting Long to Int
		try {
			FileInputStream fis = new FileInputStream(selectedFile);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int)length);
			objectOut.writeObject(content);
			objectOut.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
