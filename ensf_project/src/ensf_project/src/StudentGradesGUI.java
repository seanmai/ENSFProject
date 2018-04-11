package ensf_project.src;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import java.awt.Font;

public class StudentGradesGUI {

	private JFrame frmCourseGrades;
	JTextArea StudentGrades;
	
	public JFrame getFrame() {
		return frmCourseGrades;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private StudentGradesGUI() {
		frmCourseGrades = new JFrame();
		frmCourseGrades.setTitle("Course Grades");
		frmCourseGrades.getContentPane().setBackground(new Color(153, 204, 204));
		frmCourseGrades.getContentPane().setLayout(null);
		
		
		//Text Area Component
		StudentGrades = new JTextArea();
		StudentGrades.setBorder(new LineBorder(new Color(0, 0, 0)));
		StudentGrades.setText("Insert Assignment Name and Grades Here");
		StudentGrades.setBackground(new Color(255, 245, 238));
		StudentGrades.setFont(new Font("Dialog", Font.PLAIN, 13));
		StudentGrades.setRows(10);
		StudentGrades.setColumns(2);
		StudentGrades.setBounds(10, 11, 394, 217);
		frmCourseGrades.setBounds(100, 100, 450, 300);
		frmCourseGrades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//Aesthetic Piece
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(10, 11, 414, 239);
		panel.add(StudentGrades);
		frmCourseGrades.getContentPane().add(panel);
		panel.setLayout(null);
	}
}
