package ensf_project.src;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

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
	
	public void sendFile(byte[] content)
	{
		try {
			socketOut.println("STORE FILE");
			toServer.writeObject(content);
			toServer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	public void courseStatus(String course, String s)
	{
			socketOut.println("CHANGE COURSE STATUS");
			socketOut.println(course);
			socketOut.println(s);
			socketOut.flush();
	}

	public void upload(String course)
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
		sendFile(content);
		socketOut.println(selectedFile.getName());
		socketOut.println(course);
		//client.getSocketOut().println(new Assignment());
		socketOut.flush();
		System.out.println(selectedFile.getAbsolutePath());
		System.out.println(selectedFile.getName());
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch(IOException e){
		e.printStackTrace();
		}
		
		
		
		
		
		
		
		
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
	
	
}
	
	

