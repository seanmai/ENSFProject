package ensf_project.src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class SearchGUI extends JFrame {

	private JPanel contentPane;
	JTextField searchText;
	JButton search;
	JButton back;
	JRadioButton studentID;
	JRadioButton LastName;
	ButtonGroup buttonGroup;
	
	public JFrame returnFrame() {
		JFrame frame = new JFrame();
		frame.add(contentPane);
		frame.setBounds(700, 100, 450, 216);
		return frame;
	}
	
	/**
	 * Create the frame.
	 */
	public SearchGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 216);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//Search TextField, Button Components
		back = new JButton("Back");
		back.setFont(new Font("Dialog", Font.PLAIN, 13));
		back.setBounds(119, 141, 89, 23);
		contentPane.add(back);
		
		search = new JButton("Search");
		search.setFont(new Font("Dialog", Font.PLAIN, 13));
		search.setBounds(232, 141, 89, 23);
		contentPane.add(search);
		
		searchText = new JTextField();
		searchText.setFont(new Font("DialogInput", Font.PLAIN, 13));
		searchText.setBounds(108, 36, 211, 20);
		searchText.setColumns(10);
		
		studentID = new JRadioButton("Student ID");
		studentID.setSelected(true);
		studentID.setFont(new Font("Dialog", Font.PLAIN, 13));
		studentID.setBackground(new Color(204, 255, 255));
		studentID.setBounds(118, 68, 97, 23);
		
		LastName = new JRadioButton("Last Name");
		LastName.setFont(new Font("Dialog", Font.PLAIN, 13));
		LastName.setBackground(new Color(204, 255, 255));
		LastName.setBounds(221, 68, 109, 23);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(studentID);
		buttonGroup.add(LastName);
		
		
		//Aesthetic Pieces
		JLabel lblStudent = new JLabel("Student:");
		lblStudent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudent.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblStudent.setBounds(33, 37, 65, 14);
		
		JLabel lblSearchStudentDatabase = new JLabel("Search Student Database");
		lblSearchStudentDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchStudentDatabase.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSearchStudentDatabase.setBounds(119, 11, 202, 14);
		contentPane.add(lblSearchStudentDatabase);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(30, 30, 370, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(lblStudent);
		panel.add(LastName);
		panel.add(studentID);
		panel.add(searchText);
	}
	
	public void setListeners(profSearchListener listener) {
		back.addActionListener(new ButtonPress());
		search.addActionListener(new ButtonPress());
	}
}