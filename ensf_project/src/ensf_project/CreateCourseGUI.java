package ensf_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateCourseGUI {

	private JFrame frmCreateCourse;
	private JTextField courseName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnActive;
	private JRadioButton rdbtnInactive;
	private Client client;
	private User prof;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CreateCourseGUI window = new CreateCourseGUI();
					//window.frmCreateCourse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateCourseGUI(Client c, User p) {
		prof = p;
		client = c;
		initialize();
	}
	
	public JFrame returnFrame() {
		return frmCreateCourse;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateCourse = new JFrame();
		frmCreateCourse.getContentPane().setBackground(new Color(153, 204, 204));
		frmCreateCourse.setTitle("Create Course");
		frmCreateCourse.setBounds(100, 100, 450, 204);
		frmCreateCourse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateCourse.getContentPane().setLayout(null);
		
		courseName = new JTextField();
		courseName.setBounds(154, 43, 208, 20);
		frmCreateCourse.getContentPane().add(courseName);
		courseName.setColumns(10);
		
		JLabel lblNewCourseName = new JLabel("New Course Name:");
		lblNewCourseName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewCourseName.setBounds(42, 45, 119, 14);
		frmCreateCourse.getContentPane().add(lblNewCourseName);
		
		rdbtnActive = new JRadioButton("Active");
		rdbtnActive.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnActive);
		rdbtnActive.setBounds(175, 70, 63, 23);
		frmCreateCourse.getContentPane().add(rdbtnActive);
		
		rdbtnInactive = new JRadioButton("Inactive");
		rdbtnInactive.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnInactive);
		rdbtnInactive.setBounds(266, 70, 109, 23);
		frmCreateCourse.getContentPane().add(rdbtnInactive);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(118, 113, 89, 23);
		frmCreateCourse.getContentPane().add(btnBack);
		
		JButton btnCreateCourse = new JButton("Create Course");
		btnCreateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCourse();
			}
		});
		btnCreateCourse.setBounds(229, 113, 120, 23);
		frmCreateCourse.getContentPane().add(btnCreateCourse);
	}
	
	public void createCourse()
	{
		try {
		Course c = new Course(prof.getID(), courseName.getText(), getValidType());
		client.getSocketOut().println("ADD COURSE");
		client.getToServer().writeObject(c);
		}
		catch(IOException e)
		{
			
		}
	}
	
	public boolean getValidType()
	{
		if(buttonGroup.getSelection().equals(rdbtnActive.getModel())) {
			return true;
		}
		else {
			return false;
		}
	}
}
