package ensf_project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfessorGUImain window = new ProfessorGUImain();
					window.frmProfessorgui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProfessorGUImain() {
		initialize();
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
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setBackground(new Color(204, 255, 255));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setFont(new Font("Bell MT", Font.PLAIN, 11));
		list.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		JLabel lblCourseList = DefaultComponentFactory.getInstance().createTitle("Course List");
		lblCourseList.setFont(new Font("Bell MT", Font.BOLD, 14));
		lblCourseList.setBounds(84, 11, 76, 14);
		lblCourseList.setLabelFor(list);
		frmProfessorgui.getContentPane().add(lblCourseList);
		
		Button button = new Button("View Course");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(263, 73, 84, 22);
		frmProfessorgui.getContentPane().add(button);
		
		Button button_1 = new Button("Create Course");
		button_1.setBounds(263, 101, 84, 22);
		frmProfessorgui.getContentPane().add(button_1);
		
		Button button_2 = new Button("Activate");
		button_2.setBounds(263, 129, 84, 22);
		frmProfessorgui.getContentPane().add(button_2);
		
		Button button_3 = new Button("Deactivate");
		button_3.setBounds(263, 157, 84, 22);
		frmProfessorgui.getContentPane().add(button_3);
	}
}
