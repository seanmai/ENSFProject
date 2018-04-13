package ensf_project.src;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import ensf_project.src.StudentGUImain.CourseListener;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Contains methods and fields to create a graphical user interface representing a course
 * page for a student.
 * 
 * @author Wafa Anam, Sea Main, Matt Kadatz
 * @version 1.0
 * @since April 5, 2018
 */
public class StudentCourseGUI {

	/**
	 * The frame
	 */
	private JFrame frmCourseOptions;
	
	/**
	 * Scrollpane for a list
	 */
	private JScrollPane scrollPane;
	
	/**
	 * Buttons
	 */
	private JButton email, download, back, dropbox;
	
	/**
	 * List model to display assignments
	 */
	private DefaultListModel model;
	
	/**
	 * List of active assignments for course
	 */
	private JList list;
	
	/**
	 * Course the page is for
	 */
	private Course course;
	
	/**
	 * @return Create Course GUI Frame
	 */
	public JFrame returnFrame() {
		return frmCourseOptions;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public StudentCourseGUI(Course course) {
		this.course = course;
		frmCourseOptions = new JFrame();
		frmCourseOptions.setTitle("Course Options");
		frmCourseOptions.getContentPane().setBackground(new Color(153, 204, 204));
		frmCourseOptions.setBounds(100, 100, 534, 383);
		frmCourseOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCourseOptions.getContentPane().setLayout(null);
		
		
		//List, ScrollPane, Button Components
		model = new DefaultListModel();
		list = new JList(model);
		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBackground(new Color(255, 245, 238));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		list.setBounds(23, 35, 259, 349);
		frmCourseOptions.getContentPane().add(list);
		
		scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 51, 255, 266);
		frmCourseOptions.getContentPane().add(scrollPane);
		
		back = new JButton("Back");
		back.setFont(new Font("Dialog", Font.PLAIN, 13));
		back.setBounds(347, 282, 89, 23);
		frmCourseOptions.getContentPane().add(back);
		
		download = new JButton("Download");
		download.setBounds(10, 109, 145, 23);
		download.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		email = new JButton("Email Professor");
		email.setBounds(10, 23, 145, 23);
		//10, 65, 145, 23
		email.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		
		//Aesthetic Pieces
		JLabel lblAssignments = new JLabel(this.course.getName() + " Assignments");
		lblAssignments.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAssignments.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignments.setBounds(10, 17, 255, 23);
		frmCourseOptions.getContentPane().add(lblAssignments);
		
		JPanel lowerRight = new JPanel();
		lowerRight.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		lowerRight.setBackground(new Color(204, 255, 255));
		lowerRight.setBounds(309, 74, 167, 155);
		lowerRight.add(email);
		lowerRight.add(download);
		frmCourseOptions.getContentPane().add(lowerRight);
		lowerRight.setLayout(null);
		
		dropbox = new JButton("Open Dropbox");
		dropbox.setFont(new Font("Dialog", Font.PLAIN, 13));
		dropbox.setBounds(10, 65, 145, 23);
		lowerRight.add(dropbox);
	}
	
	/**
	 * Retrieves the course
	 * @return course the page is for
	 */
	public Course getCourse()
	{
		return course;
	}
	
	/**
	 * Main function for testing
	 * @param args
	 */
	public static void main(String[] args)
	{
		StudentCourseGUI s = new StudentCourseGUI(new Course(0, 0, null, false));
		s.frmCourseOptions.setVisible(true);
	}
	
	/**
	 * Retrieves selected assignment
	 * @return selected assignment
	 * null if no selection made
	 */
	public Assignment getAssignment()
	{
		if(list.getSelectedValue() != null)return (Assignment)list.getSelectedValue();
		return null;
	}
	
	/**
	 * Populates list of assignments with active assignments for course
	 * @param assignmentList vector of all active assignments
	 */
	public void setList(Vector<Assignment> assignmentList)
	{
		model.removeAllElements();
		for(int i = 0; i < assignmentList.size(); i++)
		{
			model.addElement(assignmentList.get(i));
		}
	}

	/**
	 * Sets specified listener for all buttons
	 * @param courseListener the listener
	 */
	public void setListeners(CourseListener courseListener) {
		email.addActionListener(courseListener);
		download.addActionListener(courseListener);
		back.addActionListener(courseListener);
		dropbox.addActionListener(courseListener);
	}
	
	/**
	 * Retrieves dropbox button
	 * @return dropbox button
	 */
	public JButton getDropbox()
	{
		return dropbox;
	}
	
	/**
	 * Retrieves email button
	 * @return email button
	 */
	public JButton getEmail()
	{
		return email;
	}
	
	/**
	 * Retrieves download button
	 * @return download button
	 */
	public JButton getDownload()
	{
		return download;
	}
	
	/**
	 * Retrieves back button
	 * @return back button
	 */
	public JButton getBack()
	{
		return back;
	}
}