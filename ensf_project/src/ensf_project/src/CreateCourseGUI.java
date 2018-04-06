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
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CreateCourseGUI {

	private JFrame frmCreateCourse;
	private JTextField courseName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnActive;
	private JRadioButton rdbtnInactive;
	private Client client;
	private User prof;
	private JPanel panel;

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
		frmCreateCourse.setBounds(100, 100, 585, 264);
		frmCreateCourse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateCourse.getContentPane().setLayout(null);
		
		courseName = new JTextField();
		courseName.setFont(new Font("Dialog", Font.PLAIN, 13));
		courseName.setBounds(193, 70, 240, 23);
		frmCreateCourse.getContentPane().add(courseName);
		courseName.setColumns(10);
		
		JLabel lblNewCourseName = new JLabel("New Course Name:");
		lblNewCourseName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewCourseName.setBounds(60, 72, 132, 14);
		frmCreateCourse.getContentPane().add(lblNewCourseName);
		
		rdbtnActive = new JRadioButton("Active");
		rdbtnActive.setBackground(new Color(204, 255, 255));
		buttonGroup.add(rdbtnActive);
		rdbtnActive.setBounds(233, 100, 63, 23);
		frmCreateCourse.getContentPane().add(rdbtnActive);
		
		rdbtnInactive = new JRadioButton("Inactive");
		rdbtnInactive.setBackground(new Color(204, 255, 255));
		buttonGroup.add(rdbtnInactive);
		rdbtnInactive.setBounds(324, 100, 109, 23);
		frmCreateCourse.getContentPane().add(rdbtnInactive);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnBack.setBounds(154, 173, 89, 23);
		frmCreateCourse.getContentPane().add(btnBack);
		
		JButton btnCreateCourse = new JButton("Create Course");
		btnCreateCourse.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnCreateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCourse();
			}
		});
		btnCreateCourse.setBounds(313, 173, 120, 23);
		frmCreateCourse.getContentPane().add(btnCreateCourse);
		
		panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new LineBorder(new Color(128,128,128), 2, true));
		panel.setBounds(46, 42, 478, 94);
		frmCreateCourse.getContentPane().add(panel);
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
