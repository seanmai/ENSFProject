package ensf_project.src;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ProfessorGUImain {
	
	/**
	 * Professor GUI Components
	 */
	private JFrame frameHolder;
	private JList list;
	private DefaultListModel<String> model;
	private JButton course; 
	private JButton create;
	private JButton activate;
	private JButton deactivate;
	
	
	/**
	 * Course Creation GUI reference
	 */
	private CreateCourseGUI createCourse;
	
	
	/**
	 * Course GUI reference
	 */
	private CourseGUI courseGUI;
	
	private User prof;
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
		frmProfessorgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfessorgui.getContentPane().setLayout(null);
		
		Vector<Course> items = client.ProfessorCourseList(prof.getID());
		
		model = new DefaultListModel();
		setList(items);
		list = new JList(model);
		list.setBackground(new Color(255, 245, 238));
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 46, 293, 365);
		frmProfessorgui.getContentPane().add(scrollPane);
		
		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		
		course = new JButton("View Course");
		course.setFont(new Font("Dialog", Font.PLAIN, 13));
		course.addActionListener(new ButtonPress());
		
		course.setBounds(390, 207, 126, 22);
		frmProfessorgui.getContentPane().add(course);
		
		create = new JButton("Create Course");
		create.setFont(new Font("Dialog", Font.PLAIN, 13));
		create.setBounds(390, 250, 126, 22);
		frmProfessorgui.getContentPane().add(create);
		create.addActionListener(new ButtonPress());
		
		activate = new JButton("Activate");
		activate.addActionListener(new ButtonPress()); 
		activate.setFont(new Font("Dialog", Font.PLAIN, 13));
		activate.setBounds(390, 296, 126, 22);
		frmProfessorgui.getContentPane().add(activate);
		
		deactivate = new JButton("Deactivate");
		deactivate.addActionListener(new ButtonPress());
		
		deactivate.setFont(new Font("Dialog", Font.PLAIN, 13));
		deactivate.setBounds(390, 340, 126, 22);
		frmProfessorgui.getContentPane().add(deactivate);
		
		JLabel lblCourseList = new JLabel("Course List");
		lblCourseList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseList.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCourseList.setBounds(21, 11, 261, 24);
		frmProfessorgui.getContentPane().add(lblCourseList);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblWelcome.setBounds(357, 49, 191, 46);
		frmProfessorgui.getContentPane().add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel(prof.getFirstName() + " " + prof.getLastName());
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
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
	
	public class ButtonPress implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			//Activates Selected Course
			if(e.getSource() == activate) {
				if(list.getSelectedValue() != null) {
					String item = (String) list.getSelectedValue();
				client.courseStatus(item, "t");
				}
			}
			
			//Deactivates Selected Course
			if(e.getSource() == deactivate) {
				if(list.getSelectedValue() != null) {
					String item = (String) list.getSelectedValue();
				client.courseStatus(item, "f");
				}
			}
			
			
			
			
			
			//Opens Course View GUI
			if(e.getSource() == course) {
				if(list.getSelectedValue()!= null)
				{
					String s = (String) list.getSelectedValue();
					courseGUI = new CourseGUI(s);
					
					frameHolder.setVisible(false);
					frameHolder = courseGUI.returnFrame();
					frameHolder.setVisible(true);
					
					//Initializing Scroll List with Students
					Vector<User> studentList = client.getStudentList();
					for(int i = 0; i < studentList.size(); i++)
					{
						courseGUI.model.addElement((studentList).get(i).getID() + " " + studentList.get(i).getFirstName() 
								 					+ " " + studentList.get(i).getLastName());
					}
					courseGUI.list.setModel(courseGUI.model);
					
					courseGUI.rdbtnAssignments.addActionListener(new ButtonPress());
					courseGUI.rdbtnStudents.addActionListener(new ButtonPress());
					courseGUI.enroll.addActionListener(new ButtonPress());
					courseGUI.unenroll.addActionListener(new ButtonPress());
					courseGUI.uploadAssignment.addActionListener(new ButtonPress());
					courseGUI.email.addActionListener(new ButtonPress());
					courseGUI.grade.addActionListener(new ButtonPress());
					courseGUI.activateAssignment.addActionListener(new ButtonPress());
					courseGUI.deactivateAssignment.addActionListener(new ButtonPress());
					courseGUI.back.addActionListener(new ButtonPress());
				}
			}
			
			if(e.getSource() == courseGUI.rdbtnAssignments) {
				courseGUI.model.removeAllElements();
				Vector<Assignment> assignmentList = client.getAssignmentList(courseGUI.courseName);
				
				for(int i = 0; i < assignmentList.size(); i++)
				{
					courseGUI.model.addElement((assignmentList.get(i).getTitle()));
				}
				
				courseGUI.list.setModel(courseGUI.model);
			}
			if(e.getSource() == courseGUI.rdbtnStudents) {
				courseGUI.model.removeAllElements();
				Vector<User> studentList = client.getStudentList();
				
				for(int i = 0; i < studentList.size(); i++)
				{
					courseGUI.model.addElement((studentList).get(i).getID() + " " + studentList.get(i).getFirstName() 
											 + " " + studentList.get(i).getLastName());
				}
				courseGUI.list.setModel(courseGUI.model);
			}
			if(e.getSource() == courseGUI.enroll) {
				String student = (String)courseGUI.list.getSelectedValue();
				client.studentEnrollment(student, courseGUI.courseName, "e");
			}
			if(e.getSource() == courseGUI.unenroll) {
				if(courseGUI.list.getSelectedValue() != null) {
					String student = (String)courseGUI.list.getSelectedValue();
					client.studentEnrollment(student, courseGUI.courseName, "u");
				}
			}
			if(e.getSource() == courseGUI.uploadAssignment) {
				client.upload(courseGUI.courseName);
			}
			if(e.getSource() == courseGUI.email) {
				//TO DO
				//
				//
			}
			if(e.getSource() == courseGUI.grade) {
				//TO DO
				//
				//
			}
			if(e.getSource() == courseGUI.activateAssignment) {
				if(courseGUI.list.getSelectedValue() != null) {
					String assignment = (String)courseGUI.list.getSelectedValue();
					client.assignmentStatus(assignment, "a");
				}
			}
			if(e.getSource() == courseGUI.deactivateAssignment) {
				if(courseGUI.list.getSelectedValue() != null) {
					String assignment = (String)courseGUI.list.getSelectedValue();
					client.assignmentStatus(assignment, "d");
				}
			}
			if(e.getSource() == courseGUI.back) {
				frameHolder.setVisible(false);
				frameHolder = createFrame();
				frameHolder.setVisible(true);
			}
			
			
			
			
			//Opens Create Course GUI
			if(e.getSource() == create) {
				createCourse = new CreateCourseGUI();
				createCourse.create.addActionListener(new ButtonPress());
				createCourse.back.addActionListener(new ButtonPress());
				
				frameHolder.setVisible(false);
				frameHolder = createCourse.returnFrame();
				frameHolder.setVisible(true);
			}
			
			//CreateCourseGUI Back Button
			if(e.getSource() == createCourse.back) {
				frameHolder.setVisible(false);
				frameHolder = createFrame();
				frameHolder.setVisible(true);
			}
			
			//CreateCourseGUI Create Course
			if(e.getSource() == createCourse.create) {
				Course c = new Course(prof.getID(), createCourse.courseName.getText(), 
									  createCourse.getValidType());
				client.createCourse(c);
				frameHolder.setVisible(false);
				frameHolder = createFrame();
				frameHolder.setVisible(true);
			}
	}
}
	
	public void setList(Vector<Course> items) 
	{
		model.removeAllElements();
		for(int i = 0; i < items.size(); i++)
		{
			model.addElement(items.get(i).getName());
		}
	}
	
}
