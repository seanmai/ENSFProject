package ensf_project.src;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ensf_project.src.ProfessorGUImain.profCreateListener;

/**
 * Contains methods and fields to retrieve user input for a Course Name 
 * and Active Boolean
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class CreateCourseGUI {
	
	/**
	 * The frame
	 */
	private JFrame frmCreateCourse;
	
	/**
	 * TextField for Course Name
	 */
	JTextField courseName;
	
	/**
	 * RadioButtons to determine if Active or Inactive
	 */
	JRadioButton rdbtnActive;
	JRadioButton rdbtnInactive;
	
	/**
	 * Button to initialize Creation of Course
	 */
	JButton create;
	
	/**
	 * Returns to previous frame
	 */
	JButton back;
	
	/**
	 * Group holding radioButtons
	 */
	ButtonGroup buttonGroup;
	
	/**
	 * Create the application.
	 */
	public CreateCourseGUI() {
		initialize();
	}
	
	/**
	 * @return Create Course GUI Frame
	 */
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
		frmCreateCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreateCourse.getContentPane().setLayout(null);
		
		
		//Text Field and Button Components
		courseName = new JTextField();
		courseName.setFont(new Font("Dialog", Font.PLAIN, 13));
		courseName.setBounds(193, 70, 240, 23);
		frmCreateCourse.getContentPane().add(courseName);
		courseName.setColumns(10);
		
		rdbtnActive = new JRadioButton("Active");
		rdbtnActive.setSelected(true);
		rdbtnActive.setBackground(new Color(204, 255, 255));
		rdbtnActive.setBounds(233, 100, 63, 23);
		frmCreateCourse.getContentPane().add(rdbtnActive);
		
		rdbtnInactive = new JRadioButton("Inactive");
		rdbtnInactive.setBackground(new Color(204, 255, 255));
		rdbtnInactive.setBounds(324, 100, 109, 23);
		frmCreateCourse.getContentPane().add(rdbtnInactive);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnActive);
		buttonGroup.add(rdbtnInactive);
		
		back = new JButton("Back");
		back.setFont(new Font("Dialog", Font.PLAIN, 13));
		back.setBounds(154, 173, 89, 23);
		frmCreateCourse.getContentPane().add(back);
		
		create = new JButton("Create Course");
		create.setFont(new Font("Dialog", Font.PLAIN, 13));
		create.setBounds(313, 173, 120, 23);
		frmCreateCourse.getContentPane().add(create);
		
		
		//Aesthetic Pieces
		JLabel lblNewCourseName = new JLabel("New Course Name:");
		lblNewCourseName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewCourseName.setBounds(60, 72, 132, 14);
		frmCreateCourse.getContentPane().add(lblNewCourseName);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new LineBorder(new Color(128,128,128), 2, true));
		panel.setBounds(46, 42, 478, 94);
		frmCreateCourse.getContentPane().add(panel);
	}
	
	/**
	 * Sets the Listeners for the JButtons
	 * @param listener
	 */
	public void setListeners(profCreateListener listener) {
		create.addActionListener(listener);
		back.addActionListener(listener);
	}
	
	/**
	 * @return True if rdButton is set to active
	 * 		   False if set to inactive
	 */
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
