package ensf_project.src;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ensf_project.src.StudentGUImain.emailListener;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * Contains methods and fields to retrieve user input for Selecting Course,
 * and Creating Assignments
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class ProfessorGUImain {

	/**
	 * The Frame
	 */
	private JFrame frameHolder;
	
	/**
	 * The PopUpFrame
	 */
	private JFrame popUpWindow;
	
	/**
	 * List for Prof Course List
	 */
	private JList list;
	
	/**
	 * Model Holding the Course List
	 */
	private DefaultListModel<Course> model;
	
	/**
	 * Button to view selected course GUI
	 */
	private JButton course; 
	
	/**
	 * Button to view create Course GUI
	 */
	private JButton create;
	
	/**
	 * Button to Activate selected Course
	 */
	private JButton activate;
	
	/**
	 * Button to Deactivate selected Course
	 */
	private JButton deactivate;


	/**
	 * Course Creation GUI reference
	 */
	private CreateCourseGUI createCourse;

	/**
	 * Search GUI reference
	 */
	private SearchGUI search;

	/**
	 * Course GUI reference
	 */
	private CourseGUI courseGUI;
	
	/**
	 * Email GUI reference
	 */
	private EmailGUI emailGUI;
	
	/**
	 * DateAssignerGUI reference
	 */
	private DateAssignerGUI dateAssign;
	
	/**
	 * SubmissionGUI reference
	 */
	private SubmissionGUI submissionGUI;

	/**
	 * Instance of User accessing GUI
	 */
	private User prof;
	
	/**
	 * Client being used for Socket
	 * Communication
	 */
	private Client client;

	/**
	 * Create the application.
	 */
	public ProfessorGUImain(User p, Client c) {
		client = c;
		prof = p;
		frameHolder = this.createFrame();
		frameHolder.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JFrame createFrame() {
		JFrame frmProfessorgui = new JFrame();
		frmProfessorgui.getContentPane().setBackground(new Color(153, 204, 204));
		frmProfessorgui.getContentPane().setForeground(SystemColor.desktop);
		frmProfessorgui.setTitle("ProfessorGUI");
		frmProfessorgui.setBounds(100, 100, 600, 475);
		frmProfessorgui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProfessorgui.getContentPane().setLayout(null);


		//List, ScrollPane and Button Components
		model = new DefaultListModel();
		setList();
		list = new JList(model);
		list.setBackground(new Color(255, 245, 238));
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 46, 293, 365);
		frmProfessorgui.getContentPane().add(scrollPane);

		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));

		course = new JButton("View Course");
		course.setFont(new Font("Dialog", Font.PLAIN, 13));
		course.addActionListener(new profMainListener());

		course.setBounds(390, 207, 126, 22);
		frmProfessorgui.getContentPane().add(course);

		create = new JButton("Create Course");
		create.setFont(new Font("Dialog", Font.PLAIN, 13));
		create.setBounds(390, 250, 126, 22);
		frmProfessorgui.getContentPane().add(create);
		create.addActionListener(new profMainListener());

		activate = new JButton("Activate");
		activate.addActionListener(new profMainListener()); 
		activate.setFont(new Font("Dialog", Font.PLAIN, 13));
		activate.setBounds(390, 296, 126, 22);
		frmProfessorgui.getContentPane().add(activate);

		deactivate = new JButton("Deactivate");
		deactivate.addActionListener(new profMainListener());

		deactivate.setFont(new Font("Dialog", Font.PLAIN, 13));
		deactivate.setBounds(390, 340, 126, 22);
		frmProfessorgui.getContentPane().add(deactivate);


		//Aesthetic Pieces
		JLabel lblCourseList = new JLabel("Course List");
		lblCourseList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseList.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCourseList.setBounds(21, 11, 261, 24);
		frmProfessorgui.getContentPane().add(lblCourseList);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 16));
		lblWelcome.setBounds(357, 49, 191, 46);
		frmProfessorgui.getContentPane().add(lblWelcome);

		JLabel lblNewLabel = new JLabel(prof.getFirstName() + " " + prof.getLastName());
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(335, 82, 239, 40);
		frmProfessorgui.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(367, 178, 174, 213);
		frmProfessorgui.getContentPane().add(panel);

		return frmProfessorgui;
	}
		/**
		 * Class representing the Listener for the main
		 * Prof GUI
		 */
		public class profMainListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {

				//Activates Selected Course
				if(e.getSource() == activate) {
					if(list.getSelectedValue() != null) {
						Course c = (Course)list.getSelectedValue();
						client.courseStatus(c.getName(), "t");
						setList();
					}
				}

				//Deactivates Selected Course
				if(e.getSource() == deactivate) {
					if(list.getSelectedValue() != null) {
						Course c = (Course)list.getSelectedValue();
						client.courseStatus(c.getName(), "f");
						setList();
					}
				}
				
				//Opens Create Course GUI
				if(e.getSource() == create) {
					createCourse = new CreateCourseGUI();
					createCourse.setListeners(new profCreateListener());
					frameHolder.setVisible(false);
					frameHolder = createCourse.returnFrame();
					frameHolder.setVisible(true);
				}
				
				//Opens Course View GUI
				if(e.getSource() == course) {
					courseGUIsetup();
				}
			}
		}

		/**
		 * Class representing the Listener for the ProfCourseGUI
		 */
		public class profCourseListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == courseGUI.getrdbtnAssignments()) {
					setAssignmentScroll();
				}
				if(e.getSource() == courseGUI.getrdbtnStudents()) {
					setStudentScroll();
				}
				if(e.getSource() == courseGUI.getEnroll()) {
					String student = (String)courseGUI.getSelectedValue();
					client.studentEnrollment(student, courseGUI.course.getName(), "e");
					setStudentScroll();
				}
				if(e.getSource() == courseGUI.getUnenroll()) {
					if(courseGUI.getSelectedValue() != null) {
						String student = (String)courseGUI.getSelectedValue();
						client.studentEnrollment(student, courseGUI.course.getName(), "u");
						setStudentScroll();
					}
				}
				if(e.getSource() == courseGUI.getUploadAssignment()) {
					dateAssign = new DateAssignerGUI();
					dateAssign.setListener(new profDateListener());
					//client.upload(courseGUI.getCourse());
				}
				if(e.getSource() == courseGUI.getEmail()) {
					email();
				}
				if(e.getSource() == courseGUI.getGrade()) {
					submissionGUIsetup();
				}
				if(e.getSource() == courseGUI.getActivateAssignment()) {
					if(courseGUI.getSelectedValue() != null) {
						Assignment assignment = (Assignment)courseGUI.getSelectedValue();
						client.assignmentStatus(assignment.getTitle(), "a");
						setAssignmentScroll();
					}
				}
				if(e.getSource() == courseGUI.getDeactivateAssignment()) {
					if(courseGUI.getSelectedValue() != null) {
						Assignment assignment = (Assignment)courseGUI.getSelectedValue();
						client.assignmentStatus(assignment.getTitle(), "d");
						setAssignmentScroll();
					}
				}
				if(e.getSource() == courseGUI.getBack()) {
					frameHolder.setVisible(false);
					frameHolder = createFrame();
					frameHolder.setVisible(true);
				}
				if(e.getSource() == courseGUI.getSearch()) {
					search = new SearchGUI();
					search.setListeners(new profSearchListener());
					popUpWindow = search.returnFrame();
					popUpWindow.setVisible(true);
				}
			}
		}
		
		/**
		 * Class representing the listener for the ProfSearchGUI
		 */
		public class profSearchListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == search.search) {
					User searchResult = null;
					if(search.buttonGroup.getSelection().equals(search.LastName.getModel())) {
						searchResult = client.searchUser(search.searchText.getText(), 1);
					}
					else {
						searchResult = client.searchUser(search.searchText.getText(), 0);
					}
					if(searchResult != null) {
						courseGUI.getModel().removeAllElements();
						courseGUI.getModel().addElement(searchResult);
					}
					else {
						courseGUI.getModel().removeAllElements();
						courseGUI.getModel().addElement("No Matching Users Found!");
						//courseGUI.list.setModel(courseGUI.model);
					}
					popUpWindow.setVisible(false);
				}
				if(e.getSource() == search.back) {
					popUpWindow.setVisible(false);
				}
			}
		}
		
		/**
		 * Class representing the listener for the CreateCourseGUI
		 */
		public class profCreateListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//CreateCourseGUI Back Button
				if(e.getSource() == createCourse.back) {
					frameHolder.setVisible(false);
					frameHolder = createFrame();
					frameHolder.setVisible(true);
				}

				//CreateCourseGUI Create Course
				if(e.getSource() == createCourse.create) {
					Course c = new Course(-1, prof.getID(), createCourse.courseName.getText(), 
							createCourse.getValidType()); //Constructed course w/ -1 as id because did not know better
					client.createCourse(c);
					frameHolder.setVisible(false);
					frameHolder = createFrame();
					frameHolder.setVisible(true);
					setList();
				}
			}
		}
		
		/**
		 * Class representing the listener for the DateAssignerGUI
		 */
		public class profDateListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == dateAssign.getAccept())
				{
					dateAssign.setDate();
					dateAssign.setInvisible();
					client.upload(courseGUI.getCourse(), dateAssign.getDate());
					setAssignmentScroll();
				}
			}
		}
		
		/**
		 * Class representing the listener for the emailGUI
		 */
		public class mailListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == emailGUI.getSend()) {
					Vector<String> classEmails = client.getEnrolledStudentList(courseGUI.getCourse().getID());
					
//					for(int i = 0; i < classEmails.size(); i++) {
//						System.out.println(classEmails.get(i));
//					}
					
					client.sendEmail(classEmails, emailGUI.getSubject(), emailGUI.getMessage());
					popUpWindow.setVisible(false);
				}
			}
		}
		
		/**
		 * Class representing the listener for the SubmissionsGUI
		 */
		public class SubmissionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == submissionGUI.getBack())courseGUIsetup();
				
				else if(e.getSource() == submissionGUI.getDownload())
				{
					Submission submission = submissionGUI.getSelectedValue();
					client.getFile(submission.getPath(), submission.getTitle());
				}
				
				else if(e.getSource() == submissionGUI.getAssignGrade())
				{
					String grade = submissionGUI.getGrade();
					Submission sub = submissionGUI.getSelectedValue();
					if(grade != null && sub != null)
					{
						client.setGrade(sub, grade);
						Vector<Submission> list = client.getSubmissions(submissionGUI.getAssignment());
						submissionGUI.setList(list);
					}
				}
			}
		}
			
			/**
			 * Sets up the courseGUI
			 */
			private void courseGUIsetup() {
				if(list.getSelectedValue()!= null)
				{
					Course c = (Course)list.getSelectedValue();
					courseGUI = new CourseGUI(c);
					courseGUI.setListeners(new profCourseListener());

					frameHolder.setVisible(false);
					frameHolder = courseGUI.returnFrame();
					frameHolder.setVisible(true);

					//Initializing Scroll List with Students
					setStudentScroll();
					//courseGUI.list.setModel(courseGUI.model);
				}
			}
			
			/**
			 * Sets up the Submission GUI
			 */
			private void submissionGUIsetup()
			{
				if(list.getSelectedValue()!= null)
				{	
					submissionGUI = new SubmissionGUI((Assignment)courseGUI.getSelectedValue());
					Vector<Submission> list = client.getSubmissions(submissionGUI.getAssignment());
					submissionGUI.setList(list);
					submissionGUI.setListeners(new SubmissionListener());
					
					frameHolder.setVisible(false);
					frameHolder = submissionGUI.returnFrame();
					frameHolder.setVisible(true);
					
				}
			}
			
			/**
			 * Sets up the emailGUI
			 */
			public void email() {
				emailGUI = new EmailGUI();
				popUpWindow = emailGUI.getFrame();
				popUpWindow.setVisible(true);
				emailGUI.setListeners(new mailListener());
			}
			
			/**
			 * Sets the list to Student List
			 */
			private void setStudentScroll() {
				Vector<User> studentList = client.getStudentList();
				courseGUI.getModel().removeAllElements();
				if(studentList == null)return;
				for(int i = 0; i < studentList.size(); i++)
				{
					String info = studentList.get(i).getID() + " " + studentList.get(i).getFirstName() 
							+ " " + studentList.get(i).getLastName() + "   ";;
							if(client.isEnrolled(studentList.get(i), courseGUI.getCourse().getName()))
								info += ("		(enrolled)");
							courseGUI.getModel().addElement(info);
				}
				//courseGUI.list.setModel(courseGUI.model);
			}
			
			/**
			 * Sets the list to Assignment List
			 */
			private void setAssignmentScroll() {
				courseGUI.getModel().removeAllElements();
				Vector<Assignment> assignmentList = client.getAssignmentList(courseGUI.getCourse().getName());
				if(assignmentList == null)return;
				String s;
				for(int i = 0; i < assignmentList.size(); i++)
				{
					courseGUI.getModel().addElement(assignmentList.get(i));
				}
			}
			
			/**
			 * Sets the Prof Course List 
			 */
		public void setList() 
		{
			Vector<Course> items = client.ProfessorCourseList(prof.getID());
			model.removeAllElements();
			if(items == null)return;
			String s;
			for(int i = 0; i < items.size(); i++)
			{
				s = items.get(i).getName();
				model.addElement(items.get(i));
			}
		}

	}