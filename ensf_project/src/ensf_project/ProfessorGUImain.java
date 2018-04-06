package ensf_project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class ProfessorGUImain {

	private JFrame frmProfessorgui;
	private JFrame frmCreateCoursegui;
	private JFrame frmCoursegui;
	private JList<Course> list;
	private JScrollPane listScroll;
	private Button course, create, activate, deactivate;
	
	private User prof;
	private Client client;

	/**
	 * Create the application.
	 */
	public ProfessorGUImain(User p, Client c) {
		client = c;
		prof = p;
		initialize();
		frmProfessorgui.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProfessorgui = new JFrame();
		frmProfessorgui.getContentPane().setBackground(new Color(153, 204, 204));
		frmProfessorgui.getContentPane().setForeground(SystemColor.desktop);
		frmProfessorgui.setTitle("ProfessorGUI");
		frmProfessorgui.setBounds(100, 100, 385, 280);
		frmProfessorgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfessorgui.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 229, 204);
		frmProfessorgui.getContentPane().add(scrollPane);
		
		list = new JList<Course>();
		list.setBackground(new Color(204, 255, 255));
		client.getSocketOut().println("GET PROF COURSE LIST");
		client.getSocketOut().println(prof.getID());
		client.getSocketOut().flush();
		
		Vector<Course> items = null;
		
		try {
			items = (Vector<Course>)client.getFromServer().readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		list.setListData(items);
		list.setFont(new Font("Bell MT", Font.PLAIN, 11));
		list.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		listScroll = new JScrollPane(list);
		
		JLabel lblCourseList = DefaultComponentFactory.getInstance().createTitle("Course List");
		lblCourseList.setFont(new Font("Bell MT", Font.BOLD, 14));
		lblCourseList.setBounds(84, 11, 90, 14);
		lblCourseList.setLabelFor(list);
		frmProfessorgui.getContentPane().add(lblCourseList);
		
		course = new Button("View Course");
		course.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CourseGUI course = new CourseGUI();
				frmCoursegui = course.returnFrame();
				frmProfessorgui.setVisible(false);
				frmCoursegui.setVisible(true);
			}
		});
		
		course.setBounds(263, 73, 84, 22);
		frmProfessorgui.getContentPane().add(course);
		
		Button create = new Button("Create Course");
		create.setBounds(263, 101, 84, 22);
		frmProfessorgui.getContentPane().add(create);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateCourseGUI newCourse = new CreateCourseGUI(client, prof);
				frmCreateCoursegui = newCourse.returnFrame();
				frmProfessorgui.setVisible(false);
				frmCreateCoursegui.setVisible(true);
			}
		});
		
		activate = new Button("Activate");
		activate.setBounds(263, 129, 84, 22);
		frmProfessorgui.getContentPane().add(activate);
		
		deactivate = new Button("Deactivate");
		deactivate.setBounds(263, 157, 84, 22);
		frmProfessorgui.getContentPane().add(deactivate);
	}
}
