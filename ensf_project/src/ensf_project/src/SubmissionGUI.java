package ensf_project.src;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ensf_project.src.ProfessorGUImain.SubmissionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Vector;

/**
 * Contains methods and fields to retrieve prof Grading and
 * display submissions by Students
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class SubmissionGUI extends JPanel {

	/**
	 * The Frame
	 */
	private JFrame frmSubmissions;
	
	/**
	 * JTextField used for inputting Grade Value
	 */
	private JTextField gradeValue;
	
	/**
	 * JButton to initiate download
	 */
	private JButton download;
	
	/**
	 * JButton for assigning inputted Grade
	 */
	private JButton assignGrade;
	
	/**
	 * JList used to display submissions
	 */
	private JList submissions;
	
	/**
	 * Model used to Hold JList
	 */
	private DefaultListModel<Submission> model;
	
	/**
	 * JButton to return to previous frame
	 */
	private JButton back;
	
	/**
	 * Assignment dropbox being used
	 */
	private Assignment assignment;
	
	/**
	 * Constructor, Create the Frame.
	 */
	public SubmissionGUI(Assignment a) {
		assignment = a;
		frmSubmissions = new JFrame();
		frmSubmissions.setTitle("Student Submissions");
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
		
		download = new JButton("download");
		download.setFont(new Font("Dialog", Font.PLAIN, 13));
		download.setBounds(26, 23, 106, 23);
		
		assignGrade = new JButton("Assign Grade");
		assignGrade.setFont(new Font("Dialog", Font.PLAIN, 13));
		assignGrade.setBounds(21, 69, 111, 23);
		
		gradeValue = new JTextField();
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
		panel_1.setBounds(342, 174, 155, 122);
		panel_1.add(gradeValue);
		panel_1.add(assignGrade);
		frmSubmissions.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(342, 77, 155, 68);
		panel.add(download);
		frmSubmissions.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblGrade.setBounds(21, 28, 46, 14);
		panel_1.add(lblGrade);
		lblGrade.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblStudentSubmissions = new JLabel("Student Submissions");
		lblStudentSubmissions.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStudentSubmissions.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentSubmissions.setBounds(29, 11, 271, 34);
		frmSubmissions.getContentPane().add(lblStudentSubmissions);
	}
	
	/**
	 * Sets the Listeners for the JButtons
	 * @param courseListener
	 */
	public void setListeners(SubmissionListener courseListener) {
		download.addActionListener(courseListener);
		assignGrade.addActionListener(courseListener);
		back.addActionListener(courseListener);
	}
	
	/**
	 * Sets the List to display Student Submissions
	 * @param submissionList
	 */
	public void setList(Vector<Submission> submissionList)
	{
		model.removeAllElements();
		if(submissionList == null)return;
		for(int i = 0; i < submissionList.size(); i++)
		{
			model.addElement(submissionList.get(i));
		}
	}
	
	/**
	 * Gets the Download Button
	 * @return download
	 */
	public JButton getDownload()
	{
		return download;
	}
	
	/**
	 * Gets the Assign Grade Button
	 * @return assignGrade
	 */
	public JButton getAssignGrade()
	{
		return assignGrade;
	}
	
	/**
	 * Gets the Back button
	 * @return
	 */
	public JButton getBack()
	{
		return back;
	}
	
	/**
	 * Sets the Submission Listener
	 * @param listener
	 */
	public void seListeners(SubmissionListener listener)
	{
		back.addActionListener(listener);
	}
	
	/**
	 * Gets the Assignment Considered
	 * @return assignment
	 */
	public Assignment getAssignment()
	{
		return assignment;
	}
	
	/**
	 * Gets selected Submission
	 * @return submissions.getSelectedValue()
	 */
	public Submission getSelectedValue()
	{
		if(submissions.getSelectedValue() != null)return (Submission)submissions.getSelectedValue();
		return null;
	}
	
	/**
	 * Gets Input Grade
	 * @return gradeValue.getText()
	 */
	public String getGrade()
	{
		return gradeValue.getText();
	}
	
	/**
	 * Gets the JFrame
	 * @return frmSubmissions
	 */
	public JFrame returnFrame() {
		return frmSubmissions;
	}
}
