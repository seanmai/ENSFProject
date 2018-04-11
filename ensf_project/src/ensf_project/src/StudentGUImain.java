package ensf_project.src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import ensf_project.src.ProfessorGUImain.ButtonPress;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentGUImain {
	private JFrame frameHolder;
	private JFrame frmStudent;
	JList list;
	JButton viewCourse;
	JButton exit;
	
	private StudentCourseGUI courseGUI;
	
	private User stud;
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
		list = new JList();
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
		
		exit = new JButton("Exit");
		exit.setBounds(284, 252, 89, 23);
		frmStudent.getContentPane().add(exit);
		
		
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
		
		JLabel NameLabel = new JLabel("InsertNameHere");
		NameLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
		NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NameLabel.setBounds(223, 60, 211, 40);
		frmStudent.getContentPane().add(NameLabel);
		
		return frmStudent;
	}
	
	private void studentCourseGUIsetup() {
		if(list.getSelectedValue()!= null)
		{
			Course c = (Course)list.getSelectedValue();

			courseGUI = new StudentCourseGUI(c);

			frameHolder.setVisible(false);
			frameHolder = courseGUI.returnFrame();
//			frameHolder.setVisible(true);
//
//			//Initializing Scroll List with Students
//			setAssignmentScroll();
//			courseGUI.list.setModel(courseGUI.model);
//
//			courseGUI.rdbtnAssignments.addActionListener(new ButtonPress());
//			courseGUI.rdbtnStudents.addActionListener(new ButtonPress());
//			courseGUI.enroll.addActionListener(new ButtonPress());
//			courseGUI.unenroll.addActionListener(new ButtonPress());
//			courseGUI.uploadAssignment.addActionListener(new ButtonPress());
//			courseGUI.email.addActionListener(new ButtonPress());
//			courseGUI.grade.addActionListener(new ButtonPress());
//			courseGUI.activateAssignment.addActionListener(new ButtonPress());
//			courseGUI.deactivateAssignment.addActionListener(new ButtonPress());
//			courseGUI.back.addActionListener(new ButtonPress());
//			courseGUI.search.addActionListener(new ButtonPress());
		}
		
	}

}
