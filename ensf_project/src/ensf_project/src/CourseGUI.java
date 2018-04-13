package ensf_project.src;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import ensf_project.src.ProfessorGUImain.profCourseListener;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Contains methods and fields to retrieve user input for Selecting Course,
 * Creating Assignments, and Enrolling Students
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class CourseGUI {
	
	/**
	 * The Frame
	 */
	private JFrame frmCourseOptions;
	
	/**
	 * ScrollPane for List
	 */
	private JScrollPane scrollPane;
	
	/**
	 * RadioButtons to switch between Student
	 * and Assignment Views
	 */
	private JRadioButton rdbtnStudents;
	private JRadioButton rdbtnAssignments;
	
	/**
	 * JButtons to enroll/unenroll a selected
	 * student
	 */
	private JButton enroll;
	private JButton unenroll;
	
	/**
	 * JButton to initialize the upload of an 
	 * Assignment
	 */
	private JButton uploadAssignment;
	
	/**
	 * JButton to open Email Window
	 */
	private JButton email;
	
	/**
	 * JButton to switch to grade view
	 */
	private JButton grade;
	
	/**
	 * JButton to Activate/Deactive a selected
	 * assignment
	 */
	private JButton activateAssignment;
	private JButton deactivateAssignment;
	
	/**
	 * Returns to previous frame
	 */
	private JButton back;
	
	/**
	 * JButton to open search window
	 */
	private JButton search;
	
	/**
	 * Holds the Assignment/Student
	 * Radio Buttons
	 */
	private ButtonGroup buttonGroup;
	
	/**
	 * Model containing the list
	 */
	private DefaultListModel model;
	
	/**
	 * List holding either Students or
	 * Assignments
	 */
	private JList list;
	
	/**
	 * Panel containing buttons
	 */
	private JPanel upperRight;
	
	Course course;
	/**
	 * Create the application.
	 */
	public CourseGUI(Course course) {
		this.course = course;
		createFrame();
	}
	
	/**
	 * @return Create Course GUI Frame
	 */
	public JFrame returnFrame() {
		return frmCourseOptions;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void createFrame() {
		frmCourseOptions = new JFrame();
		frmCourseOptions.setTitle("Course Options");
		frmCourseOptions.getContentPane().setBackground(new Color(153, 204, 204));
		frmCourseOptions.setBounds(100, 100, 590, 483);
		frmCourseOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCourseOptions.getContentPane().setLayout(null);
		
		
		//List, ScrollPane, Button Components
		buttonGroup = new ButtonGroup();
		
		model = new DefaultListModel();
		list = new JList(model);
		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBackground(new Color(255, 245, 238));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		list.setBounds(23, 35, 259, 349);
		frmCourseOptions.getContentPane().add(list);
		
		scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 35, 293, 365);
		frmCourseOptions.getContentPane().add(scrollPane);
		
		rdbtnStudents = new JRadioButton("Students");
		rdbtnStudents.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setStudentButtons();
			}
		});
		rdbtnStudents.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnStudents.setSelected(true);
		rdbtnStudents.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnStudents);
		rdbtnStudents.setBounds(56, 405, 96, 23);
		frmCourseOptions.getContentPane().add(rdbtnStudents);
		
		rdbtnAssignments = new JRadioButton("Assignments");
		rdbtnAssignments.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setAssignButtons();
			}
		});
		rdbtnAssignments.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnAssignments.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnAssignments);
		rdbtnAssignments.setBounds(154, 405, 109, 23);
		frmCourseOptions.getContentPane().add(rdbtnAssignments);
		
		back = new JButton("Back");
		back.setFont(new Font("Dialog", Font.PLAIN, 13));
		back.setBounds(453, 393, 89, 23);
		frmCourseOptions.getContentPane().add(back);
		
		upperRight = new JPanel();
		upperRight.setLayout(new GridLayout(4, 1));
		upperRight.setBackground(new Color(204, 255, 255));
		upperRight.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		upperRight.setBounds(339, 215, 203, 153);
		upperRight.setBounds(339, 140, 203, 153);
		
		formatButtons();
		setStudentButtons();
	}
	
	/**
	 * Reformats the Buttons
	 */
	public void formatButtons()
	{
		grade = new JButton("Grade");
		grade.setFont(new Font("Dialog", Font.PLAIN, 13));
		grade.setBounds(393, 161, 89, 23);
		
		activateAssignment = new JButton("Activate Assignment");
		activateAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		activateAssignment.setBounds(354, 241, 176, 23);
		
		deactivateAssignment = new JButton("Deactivate Assignment");
		deactivateAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		deactivateAssignment.setBounds(354, 279, 176, 23);
		
		uploadAssignment = new JButton("Upload Assignment");
		uploadAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		uploadAssignment.setBounds(354, 317, 176, 23);
		
		enroll = new JButton("Enroll");
		enroll.setFont(new Font("Dialog", Font.PLAIN, 13));
		enroll.setBounds(58, 13, 86, 27);
		
		unenroll = new JButton("Unenroll");
		unenroll.setFont(new Font("Dialog", Font.PLAIN, 13));
		unenroll.setBounds(58, 44, 86, 27);
		
		email = new JButton("Email");
		email.setFont(new Font("Dialog", Font.PLAIN, 13));
		email.setBounds(58, 113, 86, 27);
		
		search = new JButton("Search");
		search.setFont(new Font("Dialog", Font.PLAIN, 13));
		search.setBounds(58, 84, 86, 27);
	}
	
	/**
	 * Resets the buttons when RadioButtons
	 * are Switched from Assignments/Students
	 */
	public void setAssignButtons()
	{
//		lowerRight = new JPanel();
//		lowerRight.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
//		lowerRight.setBackground(new Color(204, 255, 255));
//		lowerRight.setBounds(371, 41, 135, 163);
//		lowerRight.setBounds(371, 140, 135, 163);
		upperRight.removeAll();
		//upperRight.setLayout(null);
	
		upperRight.add(grade);
		//frmCourseOptions.getContentPane().add(grade);
		
		upperRight.add(activateAssignment);
		//frmCourseOptions.getContentPane().add(activateAssignment);
	
		upperRight.add(deactivateAssignment);
		//frmCourseOptions.getContentPane().add(deactivateAssignment);
	
		upperRight.add(uploadAssignment);
		//frmCourseOptions.getContentPane().add(uploadAssignment);
		
		frmCourseOptions.revalidate();
		frmCourseOptions.repaint();
	}
	
	/**
	 * Resets the buttons when RadioButtons
	 * are Switched from Assignments/Students
	 */
	public void setStudentButtons()
	{	
		upperRight.removeAll();
		//upperRight.setLayout(null);

		upperRight.add(enroll);
		//frmCourseOptions.getContentPane().add(enroll);

		upperRight.add(unenroll);
		//frmCourseOptions.getContentPane().add(unenroll);
	
		upperRight.add(email);
		//frmCourseOptions.getContentPane().add(email);
	
		upperRight.add(search);
		//frmCourseOptions.getContentPane().add(search);
		
		frmCourseOptions.getContentPane().add(upperRight);
		
		frmCourseOptions.revalidate();
		frmCourseOptions.repaint();
	}
	
	/**
	 * Sets the Listeners for the Buttons in the frame
	 * @param listener
	 */
	public void setListeners(profCourseListener listener) {
		rdbtnAssignments.addActionListener(listener);
		rdbtnStudents.addActionListener(listener);
		enroll.addActionListener(listener);
		unenroll.addActionListener(listener);
		uploadAssignment.addActionListener(listener);
		email.addActionListener(listener);
		grade.addActionListener(listener);
		activateAssignment.addActionListener(listener);
		deactivateAssignment.addActionListener(listener);
		back.addActionListener(listener);
		search.addActionListener(listener);
	}
	
	/**
	 * Gets the Course
	 * @return course
	 */
	public Course getCourse()
	{
		return course;
	}
	
	/**
	 * Starts the GUI (for testing)
	 * @param args
	 */
	public static void main(String[] args)
	{
		CourseGUI g = new CourseGUI(new Course(0, 0, null, false));
		g.frmCourseOptions.setVisible(true);
	}
	
	/**
	 * Gets the AssignmentRadio Button
	 * @return rdbtnAssignments
	 */
	public JRadioButton getrdbtnAssignments() {
		return rdbtnAssignments;
	}
	
	/**
	 * Gets the search Button
	 * @return search
	 */
	public JButton getSearch() {
		return search;
	}
	
	/**
	 * Gets the Back button
	 * @return back
	 */
	public JButton getBack() {
		return back;
	}
	
	/**
	 * Gets the DeactivateAssignment Button
	 * @return deactivateAssignment
	 */
	public JButton getDeactivateAssignment() {
		return deactivateAssignment;
	}
	
	/**
	 * Gets the ActivateAssignment Button
	 * @return activateAssignment
	 */
	public JButton getActivateAssignment() {
		return activateAssignment;
	}
	
	/**
	 * Gets the Grade Button
	 * @return grade
	 */
	public JButton getGrade() {
		return grade;
	}
	
	/**
	 * Gets the Email Button
	 * @return email
	 */
	public JButton getEmail() {
		return email;
	}
	
	/**
	 * Gets the Enroll Button
	 * @return enroll
	 */
	public JButton getEnroll() {
		return enroll;
	}

	/**
	 * Gets the Student RadioButton
	 * @return rdbtnStudents
	 */
	public  JRadioButton getrdbtnStudents() {
		return rdbtnStudents;
	}
	
	/**
	 * Gets selected value from the list
	 * @return list.getSelectedValue()
	 */
	public Object getSelectedValue() {
		if(list.getSelectedValue() != null)return list.getSelectedValue();
		return null;
	}
	
	/**
	 * Gets the Unenroll Button
	 * @return unenroll
	 */
	public JButton getUnenroll() {
		return unenroll;
	}

	/**
	 * Gets the Upload Assignment Button
	 * @return uploadAssignment
	 */
	public JButton getUploadAssignment() {
		return uploadAssignment;
	}
	
	/**
	 * Gets the list Model
	 * @return model
	 */
	public DefaultListModel getModel()
	{
		return model;
	}
}