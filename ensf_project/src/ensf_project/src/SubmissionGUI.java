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

public class SubmissionGUI extends JPanel {

	private JFrame frmSubmissions;
	private JTextField gradeValue;
	private JButton download;
	private JButton assignGrade;
	private JList submissions;
	private DefaultListModel<Submission> model;
	private JButton back;
	
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
	
	public void setListeners(SubmissionListener courseListener) {
		download.addActionListener(courseListener);
		assignGrade.addActionListener(courseListener);
		back.addActionListener(courseListener);
	}
	
	public void setList(Vector<Submission> submissionList)
	{
		model.removeAllElements();
		if(submissionList == null)return;
		for(int i = 0; i < submissionList.size(); i++)
		{
			model.addElement(submissionList.get(i));
		}
	}
	
	public JButton getDownload()
	{
		return download;
	}
	
	public JButton getAssignGrade()
	{
		return assignGrade;
	}
	
	public JButton getBack()
	{
		return back;
	}
	
	public void seListeners(SubmissionListener listener)
	{
		back.addActionListener(listener);
	}
	
	public Assignment getAssignment()
	{
		return assignment;
	}
	
	public Submission getSelectedValue()
	{
		if(submissions.getSelectedValue() != null)return (Submission)submissions.getSelectedValue();
		return null;
	}
	
	public String getGrade()
	{
		return gradeValue.getText();
	}

	public JFrame returnFrame() {
		// TODO Auto-generated method stub
		return frmSubmissions;
	}
}
