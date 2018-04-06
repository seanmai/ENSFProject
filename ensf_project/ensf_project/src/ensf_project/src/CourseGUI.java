package ensf_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;

public class CourseGUI {

	private JFrame frmCourseOptions;
	private Client client;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String course;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CourseGUI window = new CourseGUI();
					//window.frmCourseOptions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CourseGUI(Client c, String course) {
		client = c;
		this.course = course;
		initialize();
	}
	
	public JFrame returnFrame() {
		return frmCourseOptions;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCourseOptions = new JFrame();
		frmCourseOptions.setTitle("Course Options");
		frmCourseOptions.getContentPane().setBackground(new Color(153, 204, 204));
		frmCourseOptions.setBounds(100, 100, 581, 483);
		frmCourseOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCourseOptions.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBackground(new Color(255, 245, 238));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		list.setBounds(23, 41, 259, 349);
		frmCourseOptions.getContentPane().add(list);
		
		JRadioButton rdbtnStudents = new JRadioButton("Students");
		rdbtnStudents.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnStudents.setSelected(true);
		rdbtnStudents.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnStudents);
		rdbtnStudents.setBounds(56, 397, 96, 23);
		frmCourseOptions.getContentPane().add(rdbtnStudents);
		
		JRadioButton rdbtnAssignments = new JRadioButton("Assignments");
		rdbtnAssignments.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnAssignments.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnAssignments);
		rdbtnAssignments.setBounds(154, 397, 109, 23);
		frmCourseOptions.getContentPane().add(rdbtnAssignments);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnEnroll.setBounds(393, 59, 89, 23);
		frmCourseOptions.getContentPane().add(btnEnroll);
		
		JButton btnUnenroll = new JButton("Unenroll");
		btnUnenroll.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnUnenroll.setBounds(393, 93, 89, 23);
		frmCourseOptions.getContentPane().add(btnUnenroll);
		
		JButton btnUploadAssignment = new JButton("Upload Assignment");
		btnUploadAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnUploadAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upload();
			}
		});
		
		btnUploadAssignment.setBounds(355, 339, 166, 23);
		frmCourseOptions.getContentPane().add(btnUploadAssignment);
		
		JButton btnEmail = new JButton("Email");
		btnEmail.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnEmail.setBounds(393, 127, 89, 23);
		frmCourseOptions.getContentPane().add(btnEmail);
		
		JButton btnGrade = new JButton("Grade");
		btnGrade.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnGrade.setBounds(393, 161, 89, 23);
		frmCourseOptions.getContentPane().add(btnGrade);
		
		JButton btnActivateAssignment = new JButton("Activate Assignment");
		btnActivateAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnActivateAssignment.setBounds(355, 263, 166, 23);
		frmCourseOptions.getContentPane().add(btnActivateAssignment);
		
		JButton btnDeactivateAssignment = new JButton("Deactivate Assignment");
		btnDeactivateAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnDeactivateAssignment.setBounds(355, 301, 166, 23);
		frmCourseOptions.getContentPane().add(btnDeactivateAssignment);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBounds(334, 237, 209, 153);
		frmCourseOptions.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBounds(371, 41, 135, 163);
		frmCourseOptions.getContentPane().add(panel_1);
	}
	
	public void upload()
	{
		File selectedFile = null;
		JFileChooser fileBrowser = new JFileChooser();
		if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			selectedFile = fileBrowser.getSelectedFile();

		long length = selectedFile.length();
		byte[] content = new byte[(int) length]; // Converting Long to Int
		try {
		FileInputStream fis = new FileInputStream(selectedFile);
		BufferedInputStream bos = new BufferedInputStream(fis);
		bos.read(content, 0, (int)length);
		client.sendFile(content);
		client.getSocketOut().println(selectedFile.getName());
		//client.getSocketOut().println(new Assignment());
		client.getSocketOut().flush();
		System.out.println(selectedFile.getAbsolutePath());
		System.out.println(selectedFile.getName());
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch(IOException e){
		e.printStackTrace();
		}
	}
}
