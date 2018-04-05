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

public class CourseGUI {

	private JFrame frmCourseOptions;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseGUI window = new CourseGUI();
					window.frmCourseOptions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CourseGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCourseOptions = new JFrame();
		frmCourseOptions.setTitle("Course Options");
		frmCourseOptions.getContentPane().setBackground(new Color(153, 204, 204));
		frmCourseOptions.setBounds(100, 100, 450, 369);
		frmCourseOptions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCourseOptions.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBackground(new Color(204, 255, 255));
		list.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		list.setBounds(23, 41, 184, 248);
		frmCourseOptions.getContentPane().add(list);
		
		JRadioButton rdbtnStudents = new JRadioButton("Students");
		rdbtnStudents.setSelected(true);
		rdbtnStudents.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnStudents);
		rdbtnStudents.setBounds(23, 296, 96, 23);
		frmCourseOptions.getContentPane().add(rdbtnStudents);
		
		JRadioButton rdbtnAssignments = new JRadioButton("Assignments");
		rdbtnAssignments.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnAssignments);
		rdbtnAssignments.setBounds(121, 296, 109, 23);
		frmCourseOptions.getContentPane().add(rdbtnAssignments);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.setBounds(273, 40, 89, 23);
		frmCourseOptions.getContentPane().add(btnEnroll);
		
		JButton btnUnenroll = new JButton("Unenroll");
		btnUnenroll.setBounds(273, 74, 89, 23);
		frmCourseOptions.getContentPane().add(btnUnenroll);
		
		JButton btnUploadAssignment = new JButton("Upload Assignment");
		btnUploadAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upload();
			}
		});
		btnUploadAssignment.setBounds(236, 266, 166, 23);
		frmCourseOptions.getContentPane().add(btnUploadAssignment);
		
		JButton btnEmail = new JButton("Email");
		btnEmail.setBounds(273, 108, 89, 23);
		frmCourseOptions.getContentPane().add(btnEmail);
		
		JButton btnGrade = new JButton("Grade");
		btnGrade.setBounds(273, 142, 89, 23);
		frmCourseOptions.getContentPane().add(btnGrade);
		
		JButton btnActivateAssignment = new JButton("Activate Assignment");
		btnActivateAssignment.setBounds(236, 201, 166, 23);
		frmCourseOptions.getContentPane().add(btnActivateAssignment);
		
		JButton btnDeactivateAssignment = new JButton("Deactivate Assignment");
		btnDeactivateAssignment.setBounds(236, 232, 166, 23);
		frmCourseOptions.getContentPane().add(btnDeactivateAssignment);
	}
	
	public static void upload()
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
		//uncomment once this has access to client
//		client.sendFile(content);
		
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch(IOException e){
		e.printStackTrace();
		}

	}
}
