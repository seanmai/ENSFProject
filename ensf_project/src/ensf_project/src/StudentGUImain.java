package ensf_project.src;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * Contains methods and fields to create a graphical user interface representing a learning
 * platform homepage for a student.
 * Other pages can be navigated to from this page.
 * 
 * @author Wafa Anam, Sea Main, Matt Kadatz
 * @version 1.0
 * @since April 5, 2018
 */
public class StudentGUImain {
	
	/**
	 * Frameholder permitting switching of frames
	 */
	private JFrame frameHolder;
	
	/**
	 * The main GUI frame
	 */
	private JFrame frmStudent;
	
	/**
	 * Popup window for emailing functionality
	 */
	private JFrame popUpWindow;
	
	/**
	 * List model to display active courses
	 */
	private DefaultListModel<Course> model;
	
	/**
	 * List to contain active courses
	 */
	private JList list;
	
	/**
	 * Button to navigate to course page
	 */
	private JButton viewCourse;
	
	/**
	 * Course GUI replacement page
	 */
	private StudentCourseGUI courseGUI;
	
	/**
	 * Submission GUI replacement page
	 */
	private StudentSubmissionGUI submissionGUI;
	
	/**
	 * EmailGUI replacement page
	 */
	private EmailGUI emailGUI;
	
	/**
	 * Student using the GUI
	 */
	private User stud;
	
	/**
	 * Client for communication with backend
	 */
	private Client client;

	/**
	 * Create the application.
	 */
	public StudentGUImain(User s, Client c) {
		client = c;
		stud = s;
		frameHolder = this.createFrame();
		frameHolder.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private JFrame createFrame() {
		frmStudent = new JFrame();
		frmStudent.setTitle("Student Menu");
		frmStudent.getContentPane().setBackground(new Color(153, 204, 204));
		frmStudent.getContentPane().setLayout(null);
		frmStudent.setBounds(100, 100, 600, 475);
		
		
		//List, and Button Components
		model = new DefaultListModel();
		setList();
		list = new JList(model);
//		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setBounds(10, 46, 293, 365);
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		list.setBackground(new Color(255, 245, 238));
		list.setBounds(10, 35, 215, 263);
		frmStudent.getContentPane().add(list);
		
		viewCourse = new JButton("View Course");
		viewCourse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				studentCourseGUIsetup();
			}
		});
		viewCourse.setFont(new Font("Dialog", Font.PLAIN, 13));
		viewCourse.setBounds(268, 147, 116, 27);
		frmStudent.getContentPane().add(viewCourse);
		
		
		//Aesthetic Pieces
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBounds(248, 129, 159, 63);
		frmStudent.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblWelcome.setBounds(235, 35, 191, 46);
		frmStudent.getContentPane().add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel("My Courses");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 215, 22);
		frmStudent.getContentPane().add(lblNewLabel);
		
		JLabel NameLabel = new JLabel(stud.getFirstName());
		NameLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
		NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NameLabel.setBounds(223, 60, 211, 40);
		frmStudent.getContentPane().add(NameLabel);
		
		return frmStudent;
	}
	
	/**
	 * Class to handle events from a Course GUI
	 *
	 */
	public class CourseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == courseGUI.getDropbox())dropboxGUIsetup();

			else if(e.getSource() == courseGUI.getEmail())email();

			else if(e.getSource() == courseGUI.getDownload())
			{
				String name = courseGUI.getAssignment().getTitle();
				String path = courseGUI.getAssignment().getPath();
				client.getFile(path, name);
			}

			else if(e.getSource() == courseGUI.getBack())
			{
				frameHolder.setVisible(false);
				frameHolder = createFrame();
				frameHolder.setVisible(true);
			}
		}
	}
	
	/**
	 * Class to handle events from a submission GUI
	 *
	 */
	public class SubmissionListenerStudent implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submissionGUI.getUpload())
			{
				client.uploadSubmission(submissionGUI.getAssignment(), stud);
				setSubmissionScroll();
			}
			else if(e.getSource() == submissionGUI.getBack())
			{
				studentCourseGUIsetup();
			}
		}
	}
	
	/**
	 * Class to handle events from an email GUI
	 *
	 */
	public class emailListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == emailGUI.getSend()) {
				User prof = client.getCourseProf(courseGUI.getCourse().getID());
				Vector<String> profEmail = new Vector<String>();
				profEmail.add(prof.getEmail());
				
				client.sendEmail(profEmail, emailGUI.getSubject(), emailGUI.getMessage());
				popUpWindow.setVisible(false);
			}
		}
	}
	
	/**
	 * Initializes course GUI and sets frameholder to this GUI
	 */
	private void studentCourseGUIsetup() {
		if(list.getSelectedValue()!= null)
		{
			Course c = (Course)list.getSelectedValue();

			courseGUI = new StudentCourseGUI(c);

			courseGUI.setListeners(new CourseListener());
			
			frameHolder.setVisible(false);
			frameHolder = courseGUI.returnFrame();
			frameHolder.setVisible(true);

			//Initializing Scroll List with Students
			setAssignmentScroll();
			//courseGUI.list.setModel(courseGUI.model);
		}
	}
	
	/**
	 * Initializes dropbox GUI and sets frameholder to hold this GUI
	 */
	public void dropboxGUIsetup()
	{
		if(list.getSelectedValue()!= null)
		{
			submissionGUI = new StudentSubmissionGUI(courseGUI.getAssignment());
			
			
			frameHolder.setVisible(false);
			frameHolder = submissionGUI.returnFrame();
			frameHolder.setVisible(true);
			
			submissionGUI.setListeners(new SubmissionListenerStudent());
			setSubmissionScroll();
		}
	}
	
	/**
	 * Sets assignment scroll list for a course GUI
	 */
	private void setAssignmentScroll()
	{
		Vector<Assignment> assignmentList = client.getActiveAssignmentList(courseGUI.getCourse().getName());
		if(assignmentList == null)return;
		courseGUI.setList(assignmentList);
	}
	
	/**
	 * Sets assignment scroll list for a submission GUI
	 */
	private void setSubmissionScroll()
	{
		Vector<Submission> submissionList = client.getSubmissions(submissionGUI.getAssignment(), stud);
		submissionGUI.setList(submissionList);
		submissionGUI.setList(submissionList);
	}
	
	/**
	 * Sets scroll list for courses
	 */
	public void setList() 
	{
		Vector<Course> items = client.studentCourseList(stud.getID());
		//System.out.println("getting course list " + items.get(0));
		model.removeAllElements();
		if(items == null)return;
		if(items.size() == 0)return;
		String s;
		for(int i = 0; i < items.size(); i++)
		{
			s = items.get(i).getName();
			model.addElement(items.get(i));
		}
	}
	
	/**
	 * Sends an email to professor of course
	 */
	public void email()
	{
		emailGUI = new EmailGUI();
		popUpWindow = emailGUI.getFrame();
		popUpWindow.setVisible(true);
		emailGUI.setListeners(new emailListener());
	}
}
