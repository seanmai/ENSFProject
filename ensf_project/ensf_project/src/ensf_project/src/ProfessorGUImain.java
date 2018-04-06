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
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ProfessorGUImain {

	private JFrame frmProfessorgui;
	private JFrame frmCreateCoursegui;
	private JFrame frmCoursegui;
	private JList list;
	private JScrollPane listScroll;
	private Button course, create, activate, deactivate;
	private DefaultListModel<String> model;
	
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
		frmProfessorgui.setBounds(100, 100, 600, 475);
		frmProfessorgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfessorgui.getContentPane().setLayout(null);
		
		client.getSocketOut().println("GET PROF COURSE LIST");
		client.getSocketOut().println(prof.getID());
		client.getSocketOut().flush();
		
		model = new DefaultListModel();
		
		try {
			Vector<Course>items = (Vector<Course>)client.getFromServer().readObject();
			setList(items);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		list = new JList(model);
		list.setBackground(new Color(255, 245, 238));
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 46, 293, 365);
		frmProfessorgui.getContentPane().add(scrollPane);
		
		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		
		course = new Button("View Course");
		course.setFont(new Font("Dialog", Font.PLAIN, 13));
		course.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedValue()!= null)
				{
					String s = (String) list.getSelectedValue();
					CourseGUI course = new CourseGUI(client, s);
					frmCoursegui = course.returnFrame();
					frmProfessorgui.setVisible(false);
					frmCoursegui.setVisible(true);
				}
			}
		});
		
		course.setBounds(401, 207, 97, 22);
		frmProfessorgui.getContentPane().add(course);
		
		Button create = new Button("Create Course");
		create.setFont(new Font("Dialog", Font.PLAIN, 13));
		create.setBounds(401, 250, 97, 22);
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
		activate.setFont(new Font("Dialog", Font.PLAIN, 13));
		activate.setBounds(401, 296, 97, 22);
		frmProfessorgui.getContentPane().add(activate);
		
		deactivate = new Button("Deactivate");
		deactivate.setFont(new Font("Dialog", Font.PLAIN, 13));
		deactivate.setBounds(401, 340, 97, 22);
		frmProfessorgui.getContentPane().add(deactivate);
		
		JLabel lblCourseList = new JLabel("Course List");
		lblCourseList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseList.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCourseList.setBounds(21, 11, 261, 24);
		frmProfessorgui.getContentPane().add(lblCourseList);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 14));
		lblWelcome.setBounds(357, 49, 191, 46);
		frmProfessorgui.getContentPane().add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel(prof.getFirstName() + " " + prof.getLastName());
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(335, 82, 239, 40);
		frmProfessorgui.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(357, 178, 184, 213);
		frmProfessorgui.getContentPane().add(panel);
	}
	
	public void setList(Vector<Course> items) 
	{
		model.removeAllElements();
		for(int i = 0; i < items.size(); i++)
		{
			model.addElement(items.get(i).getName());
		}
	}
}
