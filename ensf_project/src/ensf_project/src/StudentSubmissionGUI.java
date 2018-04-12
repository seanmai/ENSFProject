package ensf_project.src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class StudentSubmissionGUI {

	private JFrame frmSubmissions;
	JList Submissions;
	JButton Upload;
	JTextField GradeValue;
	JButton Back;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSubmissionGUI window = new StudentSubmissionGUI();
					window.frmSubmissions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentSubmissionGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSubmissions = new JFrame();
		frmSubmissions.setTitle("Student Submissions");
		frmSubmissions.getContentPane().setBackground(new Color(153, 204, 204));
		frmSubmissions.setBounds(100, 100, 547, 425);
		frmSubmissions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSubmissions.getContentPane().setLayout(null);
		
		
		//List, TextField and Button Components
		Submissions = new JList();
		Submissions.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		Submissions.setBackground(new Color(255, 245, 238));
		Submissions.setBounds(29, 48, 271, 310);
		frmSubmissions.getContentPane().add(Submissions);
		
		Upload = new JButton("Upload");
		Upload.setFont(new Font("Dialog", Font.PLAIN, 13));
		Upload.setBounds(26, 23, 106, 23);
		
		GradeValue = new JTextField();
		GradeValue.setEditable(false);
		GradeValue.setFont(new Font("DialogInput", Font.PLAIN, 13));
		GradeValue.setBounds(77, 23, 44, 23);
		GradeValue.setColumns(10);
		
		Back = new JButton("Back");
		Back.setFont(new Font("Dialog", Font.PLAIN, 13));
		Back.setBounds(376, 322, 89, 23);
		frmSubmissions.getContentPane().add(Back);
		
		
		//Aesthetic Pieces
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBounds(342, 202, 155, 68);
		panel_1.add(GradeValue);
		frmSubmissions.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(342, 89, 155, 68);
		panel.add(Upload);
		frmSubmissions.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblGrade.setBounds(21, 28, 46, 14);
		panel_1.add(lblGrade);
		lblGrade.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblStudentSubmissions = new JLabel("[CourseName] Submissions");
		lblStudentSubmissions.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStudentSubmissions.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentSubmissions.setBounds(29, 11, 271, 34);
		frmSubmissions.getContentPane().add(lblStudentSubmissions);
	}

}
