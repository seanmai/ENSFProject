package ensf_project.src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ensf_project.src.StudentGUImain.CourseListener;
import ensf_project.src.StudentGUImain.SubmissionListenerStudent;

/**
 * Contains methods and fields to create a graphical user interface representing
 * a dropbox for assignments for a student.
 * 
 * @author Wafa Anam, Sea Main, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class StudentSubmissionGUI {

	/**
	 * The GUI frame
	 */
	private JFrame frmSubmissions;
	
	/**
	 * List model to display submissions
	 */
	private DefaultListModel<Submission> model;
	
	/**
	 * List to contain all submissions
	 */
	private JList<Submission> submissions;
	
	/**
	 * Button to upload files
	 */
	private JButton upload;
	
	/**
	 * Text field to display a submission grade
	 */
	private JTextField gradeValue;
	
	/**
	 * Button to navigate to previous page
	 */
	private JButton back;
	
	/**
	 * Assignment the submissions are for
	 */
	private Assignment assignment;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StudentSubmissionGUI window = new StudentSubmissionGUI(new Assignment(0, 0, "a.txt", "a", false));
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
		frmSubmissions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSubmissions.getContentPane().setLayout(null);
		
		
		//List, TextField and Button Components
		model = new DefaultListModel();
		submissions = new JList(model);
		submissions.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(submissions.getSelectedValue() != null)
				{
					Submission s = (Submission)submissions.getSelectedValue();
					gradeValue.setText(Integer.toString(s.getSubmissionGrade()));
				}
				
			}
		});
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
		
		JLabel lblStudentsubmissions = new JLabel(assignment.getTitle());
		lblStudentsubmissions.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStudentsubmissions.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentsubmissions.setBounds(29, 11, 271, 34);
		frmSubmissions.getContentPane().add(lblStudentsubmissions);
	}

	/**
	 * Retrieves the GUI frame
	 * @return the frame
	 */
	public JFrame returnFrame() {
		return frmSubmissions;
	}
	
	/**
	 * Retrieves the assignment the GUI was created for
	 * @return the assignment
	 */
	public Assignment getAssignment()
	{
		return assignment;
	}
	
	/**
	 * Sets all button listeners to specified listener
	 * @param courseListener the listener to set all button listeners to
	 */
	public void setListeners(SubmissionListenerStudent courseListener) {
		upload.addActionListener(courseListener);
		back.addActionListener(courseListener);
	}
	
	/**
	 * Displays submission list to specified value
	 * @param submissionList vector containing submissions for list
	 */
	public void setList(Vector<Submission> submissionList)
	{
		model.removeAllElements();
		for(int i = 0; i < submissionList.size(); i++)
		{
			model.addElement(submissionList.get(i));
		}
	}
	
	/**
	 * Retrieves upload button
	 * @return upload button
	 */
	public JButton getUpload()
	{
		return upload;
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