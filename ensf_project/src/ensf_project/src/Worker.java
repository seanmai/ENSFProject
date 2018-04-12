package ensf_project.src;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class Worker implements Runnable {
	Socket socket;
	BufferedReader socketIn;
	PrintWriter socketOut;
	
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	DBManager db;
	
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
	
	@Override
	public void run() {
			System.out.println("Worker Up");
			db = new DBManager();
//			db.createDB();
//			db.createUserTable();
//			db.createAssignmentTable();
//			db.createCourseTable();
//			db.createGradeTable();
//			db.createStudentEnrollmentTable();
//			db.createSubmissionTable();
//			db.fillUserTable();
			
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
						System.out.println("Caught search string");
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
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}	
	}
	
	public void storeFile() throws ClassNotFoundException, IOException
	{
		byte[] content = (byte[]) objectIn.readObject();
		String path = "C:\\Users\\Wafa\\Downloads\\";
		
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
	
	public void storeSubmission() throws ClassNotFoundException, IOException
	{
		byte[] content = (byte[]) objectIn.readObject();
		String path = "C:\\Users\\Wafa\\Downloads\\";
		
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
}
