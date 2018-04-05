package ensf_project;

import java.io.BufferedReader;
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
			DBManager db = new DBManager();
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
						int id = socketIn.read();
						Vector <User> result = db.searchClientByID(id);
						objectOut.writeObject(result);
						objectOut.flush();
					}
					
					if(input.startsWith("VERIFY USER")) {
						int id = (socketIn.read()- 48);
						String password = socketIn.readLine();
						
						System.out.println("Test: " + id + password);
						
						User result = db.getUser(id, password);
						objectOut.writeObject(result);
						objectOut.flush();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
}
