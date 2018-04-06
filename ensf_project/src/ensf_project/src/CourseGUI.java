package ensf_project.src;

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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;

public class CourseGUI {

	private JFrame frmCourseOptions;
	private Client client;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String course;
	private DefaultListModel<String> model;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseGUI window = new CourseGUI(new Client("a", 0), "a");
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
		


		
		model = new DefaultListModel();
		studentSelected();
		list = new JList(model);
		
		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBackground(new Color(255, 245, 238));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		list.setBounds(23, 35, 259, 349);
		frmCourseOptions.getContentPane().add(list);
		
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 35, 293, 365);
		frmCourseOptions.getContentPane().add(scrollPane);
		
		JRadioButton rdbtnStudents = new JRadioButton("Students");
		rdbtnStudents.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            studentSelected();

	        }
	    });
		rdbtnStudents.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnStudents.setSelected(true);
		rdbtnStudents.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnStudents);
		rdbtnStudents.setBounds(56, 405, 96, 23);
		frmCourseOptions.getContentPane().add(rdbtnStudents);
		
		JRadioButton rdbtnAssignments = new JRadioButton("Assignments");
		rdbtnAssignments.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            assignmentSelected();

	        }
	    });
		rdbtnAssignments.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnAssignments.setBackground(new Color(153, 204, 204));
		buttonGroup.add(rdbtnAssignments);
		rdbtnAssignments.setBounds(154, 405, 109, 23);
		frmCourseOptions.getContentPane().add(rdbtnAssignments);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentEnrollment("e");
			}
		});
		btnEnroll.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnEnroll.setBounds(393, 59, 89, 23);
		frmCourseOptions.getContentPane().add(btnEnroll);
		
		JButton btnUnenroll = new JButton("Unenroll");
		btnUnenroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentEnrollment("u");
			}
		});
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
		btnActivateAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assignmentStatus("a");
			}
		});
		btnActivateAssignment.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnActivateAssignment.setBounds(355, 263, 166, 23);
		frmCourseOptions.getContentPane().add(btnActivateAssignment);
		
		JButton btnDeactivateAssignment = new JButton("Deactivate Assignment");
		btnDeactivateAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assignmentStatus("d");
			}
		});
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
		client.getSocketOut().println(course);
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
	
	public void studentSelected()
	{
		try {
			client.getSocketOut().println("GET COURSE STUDENTS");
			client.getSocketOut().flush();
			Vector<User>items = (Vector<User>)client.getFromServer().readObject();
			model.removeAllElements();
			if(items == null)return;
			for(int i = 0; i < items.size(); i++)
			{
				model.addElement((items.get(i).getID() + " " + items.get(i).getFirstName() + " " + items.get(i).getLastName()));
			}
		}
		catch(IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void assignmentSelected()
	{
		try {
			client.getSocketOut().println("GET ASSIGNMENTS");
			client.getSocketOut().println(course);
			client.getSocketOut().flush();
			Vector<Assignment>items = (Vector<Assignment>)client.getFromServer().readObject();
			model.removeAllElements();
			if(items == null)return;
			for(int i = 0; i < items.size(); i++)
			{
				model.addElement((items.get(i).getTitle()));
			}
		}
		catch(IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void studentEnrollment(String s)
	{
		String student = (String)list.getSelectedValue();
		client.getSocketOut().println("STUDENT ENROLLMENT");
		client.getSocketOut().println(student.split(" ")[0]);
		client.getSocketOut().println(course);
		client.getSocketOut().println(s);
		client.getSocketOut().flush();
	}
	
	public void assignmentStatus(String s)
	{
		if(list.getSelectedValue() != null)
		{
			String assign = (String)list.getSelectedValue();
			client.getSocketOut().println("CHANGE ASSIGNMENT STATUS");
			client.getSocketOut().println(assign);
			client.getSocketOut().println(s);
			client.getSocketOut().flush();
		}
	}
}
