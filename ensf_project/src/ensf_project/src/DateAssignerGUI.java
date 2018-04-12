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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import ensf_project.src.ProfessorGUImain.profDateListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.ComponentOrientation;

public class DateAssignerGUI {

	private JFrame frmAssignDueDate;
	
	private JSpinner month, day, year, hour, minute, ampm;
	private String date;
	JButton accept;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateAssignerGUI window = new DateAssignerGUI();
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
	public DateAssignerGUI() {
		initialize();
		frmAssignDueDate.setVisible(true);
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
		
		month = new JSpinner();
		month.setAutoscrolls(true);
		month.setBorder(new LineBorder(new Color(0, 0, 0)));
		month.setFont(new Font("Dialog", Font.PLAIN, 13));
		month.setModel(new SpinnerListModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		month.setBounds(120, 57, 90, 24);
		frmAssignDueDate.getContentPane().add(month);
		
		day = new JSpinner();
		day.setBorder(new LineBorder(new Color(0, 0, 0)));
		day.setAutoscrolls(true);
		day.setFont(new Font("Dialog", Font.PLAIN, 13));
		day.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		day.setBounds(213, 57, 39, 24);
		frmAssignDueDate.getContentPane().add(day);
		
		year = new JSpinner();
		year.setBorder(new LineBorder(new Color(0, 0, 0)));
		year.setAutoscrolls(true);
		year.setFont(new Font("Dialog", Font.PLAIN, 13));
		year.setModel(new SpinnerNumberModel(new Integer(2000), new Integer(2000), null, new Integer(1)));
		year.setBounds(254, 57, 73, 24);
		frmAssignDueDate.getContentPane().add(year);
		
		hour = new JSpinner();
		hour.setBorder(new LineBorder(new Color(0, 0, 0)));
		hour.setAutoscrolls(true);
		hour.setFont(new Font("Dialog", Font.PLAIN, 13));
		hour.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		hour.setBounds(120, 119, 53, 24);
		frmAssignDueDate.getContentPane().add(hour);
		
		minute = new JSpinner();
		minute.setBorder(new LineBorder(new Color(0, 0, 0)));
		minute.setAutoscrolls(true);
		minute.setFont(new Font("Dialog", Font.PLAIN, 13));
		minute.setBounds(187, 119, 53, 24);
		frmAssignDueDate.getContentPane().add(minute);
		
		ampm = new JSpinner();
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
		
		accept = new JButton("accept");
		accept.setFont(new Font("Dialog", Font.PLAIN, 13));
		accept.setBounds(161, 179, 102, 23);
		frmAssignDueDate.getContentPane().add(accept);
	}
	
	public void setDate()
	{
		date = ((String)month.getValue() + " " + day.getValue() + ", " + hour.getValue() + ":");
		String min = minute.getValue().toString();
		if(Integer.parseInt(min) < 10)
			min = "0" + min;
		date += min + ampm.getValue();
	}
	
	public void setInvisible()
	{
		frmAssignDueDate.setVisible(false);
	}
	
	public void setListener(profDateListener b)
	{
		accept.addActionListener(b);
	}
	
	public String getDate()
	{
		return date;
	}
	
}