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

import ensf_project.src.StudentGUImain.CourseListener;

public class StudentSubmissionGUI {

	private JFrame frmSubmissions;
	JList submissions;
	JButton upload;
	JTextField gradeValue;
	JButton back;
	
	private Assignment assignment;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StudentSubmissionGUI window = new StudentSubmissionGUI();
//					window.frmSubmissions.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public StudentSubmissionGUI(Assignment a) {
		assignment = a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSubmissions = new JFrame();
		frmSubmissions.setTitle("Student submissions");
		frmSubmissions.getContentPane().setBackground(new Color(153, 204, 204));
		frmSubmissions.setBounds(100, 100, 547, 425);
		frmSubmissions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSubmissions.getContentPane().setLayout(null);
		
		
		//List, TextField and Button Components
		submissions = new JList();
		submissions.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		submissions.setBackground(new Color(255, 245, 238));
		submissions.setBounds(29, 48, 271, 310);
		frmSubmissions.getContentPane().add(submissions);
		
		upload = new JButton("upload");
		upload.setFont(new Font("Dialog", Font.PLAIN, 13));
		upload.setBounds(26, 23, 106, 23);
		
		gradeValue = new JTextField();
		gradeValue.setEditable(false);
		gradeValue.setFont(new Font("DialogInput", Font.PLAIN, 13));
		gradeValue.setBounds(77, 23, 44, 23);
		gradeValue.setColumns(10);
		
		back = new JButton("back");
		back.setFont(new Font("Dialog", Font.PLAIN, 13));
		back.setBounds(376, 322, 89, 23);
		frmSubmissions.getContentPane().add(back);
		
		
		//Aesthetic Pieces
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBounds(342, 202, 155, 68);
		panel_1.add(gradeValue);
		frmSubmissions.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(342, 89, 155, 68);
		panel.add(upload);
		frmSubmissions.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblGrade.setBounds(21, 28, 46, 14);
		panel_1.add(lblGrade);
		lblGrade.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblStudentsubmissions = new JLabel(assignment.getTitle().split(".")[0]);
		lblStudentsubmissions.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStudentsubmissions.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentsubmissions.setBounds(29, 11, 271, 34);
		frmSubmissions.getContentPane().add(lblStudentsubmissions);
	}

	public JFrame returnFrame() {
		// TODO Auto-generated method stub
		return frmSubmissions;
	}

	public void showGrade() {
		// TODO Auto-generated method stub
		
	}
	
	public void setListeners(SubmissionListener courseListener) {
		submissions.addListSelectionListener(courseListener);
		upload.addActionListener(courseListener);
		gradeValue.addActionListener(courseListener);
		back.addActionListener(courseListener);
	}

	public Object getSelectedAssign() {
		// TODO Auto-generated method stub
		return null;
	}
}
