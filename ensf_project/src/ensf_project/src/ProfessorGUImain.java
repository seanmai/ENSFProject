package ensf_project.src;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ProfessorGUImain {

	/**
	 * Professor GUI Components
	 */
	private JFrame frameHolder;
	private JFrame popUpSearch;
	private JList list;
	private DefaultListModel<Course> model;
	private JButton course; 
	private JButton create;
	private JButton activate;
	private JButton deactivate;


	/**
	 * Course Creation GUI reference
	 */
	private CreateCourseGUI createCourse;

	/**
	 * Search GUI reference
	 */
	private SearchGUI search;

	/**
	 * Course GUI reference
	 */
	private CourseGUI courseGUI;


	private DateAssignerGUI dateAssign;

	private User prof;
	private Client client;

	/**
	 * Create the application.
	 */
	public ProfessorGUImain(User p, Client c) {
		client = c;
		prof = p;
		frameHolder = this.createFrame();
		frameHolder.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JFrame createFrame() {
		JFrame frmProfessorgui = new JFrame();
		frmProfessorgui.getContentPane().setBackground(new Color(153, 204, 204));
		frmProfessorgui.getContentPane().setForeground(SystemColor.desktop);
		frmProfessorgui.setTitle("ProfessorGUI");
		frmProfessorgui.setBounds(100, 100, 600, 475);
		frmProfessorgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProfessorgui.getContentPane().setLayout(null);


		//List, ScrollPane and Button Components
		model = new DefaultListModel();
		setList();
		list = new JList(model);
		list.setBackground(new Color(255, 245, 238));
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 46, 293, 365);
		frmProfessorgui.getContentPane().add(scrollPane);

		list.setFont(new Font("Dialog", Font.PLAIN, 13));
		list.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));

		course = new JButton("View Course");
		course.setFont(new Font("Dialog", Font.PLAIN, 13));
		course.addActionListener(new profMainListener());

		course.setBounds(390, 207, 126, 22);
		frmProfessorgui.getContentPane().add(course);

		create = new JButton("Create Course");
		create.setFont(new Font("Dialog", Font.PLAIN, 13));
		create.setBounds(390, 250, 126, 22);
		frmProfessorgui.getContentPane().add(create);
		create.addActionListener(new profMainListener());

		activate = new JButton("Activate");
		activate.addActionListener(new ButtonPress()); 
		activate.setFont(new Font("Dialog", Font.PLAIN, 13));
		activate.setBounds(390, 296, 126, 22);
		frmProfessorgui.getContentPane().add(activate);

		deactivate = new JButton("Deactivate");
		deactivate.addActionListener(new profMainListener());

		deactivate.setFont(new Font("Dialog", Font.PLAIN, 13));
		deactivate.setBounds(390, 340, 126, 22);
		frmProfessorgui.getContentPane().add(deactivate);


		//Aesthetic Pieces
		JLabel lblCourseList = new JLabel("Course List");
		lblCourseList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseList.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCourseList.setBounds(21, 11, 261, 24);
		frmProfessorgui.getContentPane().add(lblCourseList);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 16));
		lblWelcome.setBounds(357, 49, 191, 46);
		frmProfessorgui.getContentPane().add(lblWelcome);

		JLabel lblNewLabel = new JLabel(prof.getFirstName() + " " + prof.getLastName());
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(335, 82, 239, 40);
		frmProfessorgui.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(367, 178, 174, 213);
		frmProfessorgui.getContentPane().add(panel);

		return frmProfessorgui;
	}

		public class profMainListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {

				//Activates Selected Course
				if(e.getSource() == activate) {
					if(list.getSelectedValue() != null) {
						Course c = (Course)list.getSelectedValue();
						client.courseStatus(c.getName(), "t");
						setList();
					}
				}

				//Deactivates Selected Course
				if(e.getSource() == deactivate) {
					if(list.getSelectedValue() != null) {
						Course c = (Course)list.getSelectedValue();
						client.courseStatus(c.getName(), "f");
						setList();
					}
				}
				
				//Opens Create Course GUI
				if(e.getSource() == create) {
					createCourse = new CreateCourseGUI();
					createCourse.create.addActionListener(new ButtonPress());
					createCourse.back.addActionListener(new ButtonPress());

					frameHolder.setVisible(false);
					frameHolder = createCourse.returnFrame();
					frameHolder.setVisible(true);
				}
				
				//Opens Course View GUI
				if(e.getSource() == course) {
					courseGUIsetup();
				}
			}
		}


		public class profCourseListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == courseGUI.rdbtnAssignments) {
					setAssignmentScroll();
				}
				if(e.getSource() == courseGUI.rdbtnStudents) {
					setStudentScroll();
				}
				if(e.getSource() == courseGUI.enroll) {
					String student = (String)courseGUI.list.getSelectedValue();
					client.studentEnrollment(student, courseGUI.course.getName(), "e");
					setStudentScroll();
				}
				if(e.getSource() == courseGUI.unenroll) {
					if(courseGUI.list.getSelectedValue() != null) {
						String student = (String)courseGUI.list.getSelectedValue();
						client.studentEnrollment(student, courseGUI.course.getName(), "u");
						setStudentScroll();
					}
				}
				if(e.getSource() == courseGUI.uploadAssignment) {
					dateAssign = new DateAssignerGUI();
					dateAssign.setListener(new ButtonPress());
					//client.upload(courseGUI.getCourse());
				}
				if(e.getSource() == courseGUI.email) {
					//TO DO
					//
					//
				}
				if(e.getSource() == courseGUI.grade) {
					//TO DO
					//
					//
				}
				if(e.getSource() == courseGUI.activateAssignment) {
					if(courseGUI.list.getSelectedValue() != null) {
						Assignment assignment = (Assignment)courseGUI.list.getSelectedValue();
						//assignment = assignment.replace("   (active)", "");
						client.assignmentStatus(assignment.getTitle(), "a");
						setAssignmentScroll();
					}
				}
				if(e.getSource() == courseGUI.deactivateAssignment) {
					if(courseGUI.list.getSelectedValue() != null) {
						Assignment assignment = (Assignment)courseGUI.list.getSelectedValue();
						//assignment = assignment.replace("   (active)", "");
						client.assignmentStatus(assignment.getTitle(), "d");
						setAssignmentScroll();
					}
				}
				if(e.getSource() == courseGUI.back) {
					frameHolder.setVisible(false);
					frameHolder = createFrame();
					frameHolder.setVisible(true);
				}
				if(e.getSource() == courseGUI.search) {
					search = new SearchGUI();
					search.back.addActionListener(new ButtonPress());
					search.search.addActionListener(new ButtonPress());
					popUpSearch = new JFrame();
					popUpSearch = search.returnFrame();
					popUpSearch.setVisible(true);
				}
			}
		}

		public class profSearchListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == search.search) {
					User searchResult = null;
					if(search.buttonGroup.getSelection().equals(search.LastName.getModel())) {
						searchResult = client.searchUser(search.searchText.getText(), 1);
					}
					else {
						searchResult = client.searchUser(search.searchText.getText(), 0);
					}
					if(searchResult != null) {
						courseGUI.model.removeAllElements();
						courseGUI.model.addElement(searchResult.getID() + " " + searchResult.getFirstName() + " " 
								+ searchResult.getLastName() + ",  " + searchResult.getEmail());
						courseGUI.list.setModel(courseGUI.model);
					}
					else {
						courseGUI.model.removeAllElements();
						courseGUI.model.addElement("No Matching Users Found!");
						courseGUI.list.setModel(courseGUI.model);
					}
					popUpSearch.setVisible(false);
				}
				if(e.getSource() == search.back) {
					popUpSearch.setVisible(false);
				}
			}
		}
		
		public class profCreateListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//CreateCourseGUI Back Button
				if(e.getSource() == createCourse.back) {
					frameHolder.setVisible(false);
					frameHolder = createFrame();
					frameHolder.setVisible(true);
				}

				//CreateCourseGUI Create Course
				if(e.getSource() == createCourse.create) {
					Course c = new Course(-1, prof.getID(), createCourse.courseName.getText(), 
							createCourse.getValidType()); //Constructed course w/ -1 as id because did not know better
					client.createCourse(c);
					frameHolder.setVisible(false);
					frameHolder = createFrame();
					frameHolder.setVisible(true);
					setList();
				}
			}
		}
		
		public class profDateListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == dateAssign.accept)
				{
					dateAssign.setDate();
					dateAssign.setInvisible();
					client.upload(courseGUI.getCourse(), dateAssign.getDate());
					setAssignmentScroll();
				}
			}
		}
		
			private void courseGUIsetup() {
				if(list.getSelectedValue()!= null)
				{
					Course c = (Course)list.getSelectedValue();
					courseGUI = new CourseGUI(c);
					courseGUI.setListeners(new profCourseListener());

					frameHolder.setVisible(false);
					frameHolder = courseGUI.returnFrame();
					frameHolder.setVisible(true);

					//Initializing Scroll List with Students
					setStudentScroll();
					courseGUI.list.setModel(courseGUI.model);
				}

			}

			private void setStudentScroll() {
				Vector<User> studentList = client.getStudentList();
				courseGUI.model.removeAllElements();
				if(studentList == null)return;
				for(int i = 0; i < studentList.size(); i++)
				{
					String info = studentList.get(i).getID() + " " + studentList.get(i).getFirstName() 
							+ " " + studentList.get(i).getLastName() + "   ";;
							if(client.isEnrolled(studentList.get(i), courseGUI.getCourse().getName()))
								info += ("		(enrolled)");
							courseGUI.model.addElement(info);
				}
				courseGUI.list.setModel(courseGUI.model);
			}

			private void setAssignmentScroll() {
				courseGUI.model.removeAllElements();
				Vector<Assignment> assignmentList = client.getAssignmentList(courseGUI.getCourse().getName());
				if(assignmentList == null)return;
				String s;
				for(int i = 0; i < assignmentList.size(); i++)
				{
					courseGUI.model.addElement(assignmentList.get(i));
				}
			}

		public void setList() 
		{
			Vector<Course> items = client.ProfessorCourseList(prof.getID());
			model.removeAllElements();
			if(items == null)return;
			String s;
			for(int i = 0; i < items.size(); i++)
			{
				s = items.get(i).getName();
				model.addElement(items.get(i));
			}
		}

	}