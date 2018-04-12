package ensf_project.src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerListModel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.ComponentOrientation;

public class dateAssignerGUI {

	private JFrame frmAssignDueDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dateAssignerGUI window = new dateAssignerGUI();
					window.frmAssignDueDate.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dateAssignerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAssignDueDate = new JFrame();
		frmAssignDueDate.setTitle("Assign Due Date");
		frmAssignDueDate.getContentPane().setBackground(new Color(153, 204, 204));
		frmAssignDueDate.setBounds(100, 100, 427, 262);
		frmAssignDueDate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAssignDueDate.getContentPane().setLayout(null);
		
		JSpinner Month = new JSpinner();
		Month.setAutoscrolls(true);
		Month.setBorder(new LineBorder(new Color(0, 0, 0)));
		Month.setFont(new Font("Dialog", Font.PLAIN, 13));
		Month.setModel(new SpinnerListModel(new String[] {"  January", "  February", "  March", "  April", "  May", "  June ", "  July", "  August ", "September", "October", "November", "December"}));
		Month.setBounds(120, 57, 90, 24);
		frmAssignDueDate.getContentPane().add(Month);
		
		JSpinner Day = new JSpinner();
		Day.setBorder(new LineBorder(new Color(0, 0, 0)));
		Day.setAutoscrolls(true);
		Day.setFont(new Font("Dialog", Font.PLAIN, 13));
		Day.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		Day.setBounds(213, 57, 39, 24);
		frmAssignDueDate.getContentPane().add(Day);
		
		JSpinner Year = new JSpinner();
		Year.setBorder(new LineBorder(new Color(0, 0, 0)));
		Year.setAutoscrolls(true);
		Year.setFont(new Font("Dialog", Font.PLAIN, 13));
		Year.setModel(new SpinnerNumberModel(new Integer(2000), new Integer(2000), null, new Integer(1)));
		Year.setBounds(254, 57, 73, 24);
		frmAssignDueDate.getContentPane().add(Year);
		
		JSpinner Hour = new JSpinner();
		Hour.setBorder(new LineBorder(new Color(0, 0, 0)));
		Hour.setAutoscrolls(true);
		Hour.setFont(new Font("Dialog", Font.PLAIN, 13));
		Hour.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		Hour.setBounds(120, 119, 53, 24);
		frmAssignDueDate.getContentPane().add(Hour);
		
		JSpinner Minute = new JSpinner();
		Minute.setBorder(new LineBorder(new Color(0, 0, 0)));
		Minute.setAutoscrolls(true);
		Minute.setFont(new Font("Dialog", Font.PLAIN, 13));
		Minute.setBounds(187, 119, 53, 24);
		frmAssignDueDate.getContentPane().add(Minute);
		
		JSpinner ampm = new JSpinner();
		ampm.setBorder(new LineBorder(new Color(0, 0, 0)));
		ampm.setAutoscrolls(true);
		ampm.setModel(new SpinnerListModel(new String[] {" AM", " PM"}));
		ampm.setFont(new Font("Dialog", Font.PLAIN, 13));
		ampm.setBounds(250, 119, 53, 24);
		frmAssignDueDate.getContentPane().add(ampm);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(60, 31, 290, 137);
		frmAssignDueDate.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDate.setBounds(0, 30, 46, 14);
		panel.add(lblDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTime.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTime.setBounds(0, 93, 46, 14);
		panel.add(lblTime);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(97, 90, 46, 19);
		panel.add(label);
		
		JButton Accept = new JButton("Accept");
		Accept.setFont(new Font("Dialog", Font.PLAIN, 13));
		Accept.setBounds(161, 179, 102, 23);
		frmAssignDueDate.getContentPane().add(Accept);
	}
}
