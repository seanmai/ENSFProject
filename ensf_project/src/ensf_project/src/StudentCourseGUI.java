package ensf_project.src;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import ensf_project.src.StudentGUImain.CourseListener;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StudentCourseGUI {

	private JFrame frmCourseOptions;
	private JScrollPane scrollPane;
	private JButton email;
	private JButton download;
	private JButton back;
	private JButton dropbox;
	private DefaultListModel model;
	private JList list;
	
	Course course;
	
	/**
	 * @return Create Course GUI Frame
	 */
	public JFrame returnFrame() {
		return frmCourseOptions;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public StudentCourseGUI(Course course) {
		this.course = course;
		frmCourseOptions = new JFrame();
		frmCourseOptions.setTitle("Course Options");
		frmCourseOptions.getContentPane().setBackground(new Color(153, 204, 204));
		frmCourseOptions.setBounds(100, 100, 534, 383);
		frmCourseOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCourseOptions.getContentPane().setLayout(null);
		
		
		//List, ScrollPane, Button Components
		model = new DefaultListModel();
		list = new JList(model);
		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBackground(new Color(255, 245, 238));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		list.setBounds(23, 35, 259, 349);
		frmCourseOptions.getContentPane().add(list);
		
		scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 51, 255, 266);
		frmCourseOptions.getContentPane().add(scrollPane);
		
		back = new JButton("Back");
		back.setFont(new Font("Dialog", Font.PLAIN, 13));
		back.setBounds(347, 282, 89, 23);
		frmCourseOptions.getContentPane().add(back);
		
		download = new JButton("Download");
		download.setBounds(10, 109, 145, 23);
		download.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		email = new JButton("Email Professor");
		email.setBounds(10, 23, 145, 23);
		//10, 65, 145, 23
		email.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		
		//Aesthetic Pieces
		JLabel lblAssignments = new JLabel(this.course.getName() + " Assignments");
		lblAssignments.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAssignments.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignments.setBounds(10, 17, 255, 23);
		frmCourseOptions.getContentPane().add(lblAssignments);
		
		JPanel lowerRight = new JPanel();
		lowerRight.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		lowerRight.setBackground(new Color(204, 255, 255));
		lowerRight.setBounds(309, 74, 167, 155);
		lowerRight.add(email);
		lowerRight.add(download);
		frmCourseOptions.getContentPane().add(lowerRight);
		lowerRight.setLayout(null);
		
		dropbox = new JButton("Open Dropbox");
		dropbox.setFont(new Font("Dialog", Font.PLAIN, 13));
		dropbox.setBounds(10, 65, 145, 23);
		lowerRight.add(dropbox);
	}
	
	public Course getCourse()
	{
		return course;
	}
	
	public static void main(String[] args)
	{
		StudentCourseGUI s = new StudentCourseGUI(new Course(0, 0, null, false));
		s.frmCourseOptions.setVisible(true);
	}
	
	public Assignment getAssignment()
	{
		if(list.getSelectedValue() != null)return (Assignment)list.getSelectedValue();
		return null;
	}
	
	public void setList(Vector<Assignment> assignmentList)
	{
		model.removeAllElements();
		for(int i = 0; i < assignmentList.size(); i++)
		{
			model.addElement(assignmentList.get(i));
		}
	}

	public void setListeners(CourseListener courseListener) {
		email.addActionListener(courseListener);
		download.addActionListener(courseListener);
		back.addActionListener(courseListener);
		dropbox.addActionListener(courseListener);
	}
	
	public JButton getDropbox()
	{
		return dropbox;
	}
	
	public JButton getEmail()
	{
		return email;
	}
	
	public JButton getDownload()
	{
		return download;
	}
	
	public JButton getBack()
	{
		return back;
	}
}