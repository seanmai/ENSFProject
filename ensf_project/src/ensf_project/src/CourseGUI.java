package ensf_project.src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;

public class CourseGUI {

	JFrame frmCourseOptions;
	JScrollPane scrollPane;
	JRadioButton rdbtnStudents;
	JRadioButton rdbtnAssignments;
	JButton enroll;
	JButton unenroll;
	JButton uploadAssignment;
	JButton email;
	JButton grade;
	JButton activateAssignment;
	JButton deactivateAssignment;
	JButton back;
	JButton search;
	ButtonGroup buttonGroup;
	DefaultListModel<String> model;
	JList list;
	
	String courseName;
	/**
	 * Create the application.
	 */
	public CourseGUI(String course) {
		this.courseName = course;
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
		frmCourseOptions.setBounds(100, 100, 581, 483);
		frmCourseOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 35, 293, 365);
		frmCourseOptions.getContentPane().add(scrollPane);
		
		rdbtnStudents = new JRadioButton("Students");
		rdbtnStudents.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnStudents.setSelected(true);
		rdbtnStudents.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnStudents);
		rdbtnStudents.setBounds(56, 405, 96, 23);
		frmCourseOptions.getContentPane().add(rdbtnStudents);
		
		rdbtnAssignments = new JRadioButton("Assignments");
		rdbtnAssignments.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnAssignments.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnAssignments);
		rdbtnAssignments.setBounds(154, 405, 109, 23);
		frmCourseOptions.getContentPane().add(rdbtnAssignments);
		
		enroll = new JButton("Enroll");
		enroll.setFont(new Font("Dialog", Font.PLAIN, 13));
		enroll.setBounds(393, 59, 89, 23);
		frmCourseOptions.getContentPane().add(enroll);
		
		unenroll = new JButton("Unenroll");
		unenroll.setFont(new Font("Dialog", Font.PLAIN, 13));
		unenroll.setBounds(393, 93, 89, 23);
		frmCourseOptions.getContentPane().add(unenroll);
		
		uploadAssignment = new JButton("Upload Assignment");
		uploadAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		uploadAssignment.setBounds(354, 317, 176, 23);
		frmCourseOptions.getContentPane().add(uploadAssignment);
		
		email = new JButton("Email");
		email.setFont(new Font("Dialog", Font.PLAIN, 13));
		email.setBounds(393, 127, 89, 23);
		frmCourseOptions.getContentPane().add(email);
		
		grade = new JButton("Grade");
		grade.setFont(new Font("Dialog", Font.PLAIN, 13));
		grade.setBounds(393, 161, 89, 23);
		frmCourseOptions.getContentPane().add(grade);
		
		activateAssignment = new JButton("Activate Assignment");
		activateAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		activateAssignment.setBounds(354, 241, 176, 23);
		frmCourseOptions.getContentPane().add(activateAssignment);
		
		deactivateAssignment = new JButton("Deactivate Assignment");
		deactivateAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		deactivateAssignment.setBounds(354, 279, 176, 23);
		frmCourseOptions.getContentPane().add(deactivateAssignment);
		
		search = new JButton("Search");
		search.setFont(new Font("Dialog", Font.PLAIN, 13));
		search.setBounds(339, 393, 89, 23);
		frmCourseOptions.getContentPane().add(search);
		
		back = new JButton("Back");
		back.setFont(new Font("Dialog", Font.PLAIN, 13));
		back.setBounds(453, 393, 89, 23);
		frmCourseOptions.getContentPane().add(back);
		
		
		//Aesthetic Pieces
		JPanel upperRight = new JPanel();
		upperRight.setBackground(new Color(204, 255, 255));
		upperRight.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		upperRight.setBounds(339, 215, 203, 153);
		frmCourseOptions.getContentPane().add(upperRight);
		
		JPanel lowerRight = new JPanel();
		lowerRight.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		lowerRight.setBackground(new Color(204, 255, 255));
		lowerRight.setBounds(371, 41, 135, 163);
		frmCourseOptions.getContentPane().add(lowerRight);
	}
}
	


