package ensf_project.src;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ensf_project.src.ProfessorGUImain.mailListener;
import ensf_project.src.StudentGUImain.emailListener;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Rectangle;

/**
 * Contains methods and fields to retrieve user input for an email
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class EmailGUI {
	
	/**
	 * The Frame
	 */
	private JFrame frmEmail;
	
	/**
	 * JTextArea with the Email Subject
	 */
	private JTextArea SubjectMessage;
	
	/**
	 * JTextArea with the Email Body
	 */
	private JTextArea EmailMessage;
	
	/**
	 * JButton that sends the email
	 */
	private JButton Send;
	
	/**
	 * Gets the frame
	 * @return frmEmail
	 */
	public JFrame getFrame() {
		return frmEmail;
	}
	

	/**
	 * Constructor, Create the Frame.
	 */
	public EmailGUI() {
		frmEmail = new JFrame();
		frmEmail.getContentPane().setBounds(720, 100, 600, 475);
		frmEmail.setTitle("Email");
		frmEmail.getContentPane().setBackground(new Color(153, 204, 204));
		frmEmail.getContentPane().setLayout(null);
		
		
		//JTextArea, Button Components
		EmailMessage = new JTextArea();
		EmailMessage.setBorder(new LineBorder(new Color(0, 0, 0)));
		EmailMessage.setFont(new Font("Helvetica LT", Font.PLAIN, 13));
		EmailMessage.setBounds(10, 68, 372, 134);
		EmailMessage.setBackground(new Color(255, 245, 238));
		
		SubjectMessage = new JTextArea();
		SubjectMessage.setFont(new Font("Dialog", Font.PLAIN, 13));
		SubjectMessage.setBorder(new LineBorder(new Color(0, 0, 0)));
		SubjectMessage.setBackground(new Color(255, 245, 238));
		SubjectMessage.setBounds(10, 24, 372, 20);
		
		Send = new JButton("Send");
		Send.setFont(new Font("Dialog", Font.PLAIN, 13));
		Send.setBounds(173, 241, 89, 23);
		frmEmail.getContentPane().add(Send);
		frmEmail.setBounds(100, 100, 450, 317);
		frmEmail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//Aesthetic Pieces
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(21, 22, 392, 213);
		panel.add(EmailMessage);
		panel.add(SubjectMessage);
		frmEmail.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMessage.setBounds(10, 41, 76, 25);
		panel.add(lblMessage);
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSubject.setBounds(10, 0, 76, 25);
		panel.add(lblSubject);
	}
	
	/**
	 * Sets the Button Listener (from StudentGUI)
	 * @param emaillistener
	 */
	public void setListeners(emailListener emaillistener) {
		Send.addActionListener(emaillistener);
	}
	
	/**
	 * Sets the Button Listener (from ProfGUI)
	 * @param mailListener
	 */
	public void setListeners(mailListener mailListener) {
		Send.addActionListener(mailListener);
	}
	
	/**
	 * Gets the Subject of the Email
	 * @return SubjectMessage.getText()
	 */
	public String getSubject() {
		return SubjectMessage.getText();
	}
	
	/**
	 * Gets the Message of the Email
	 * @return EmailMessage.getText()
	 */
	public String getMessage() {
		return EmailMessage.getText();
	}
	
	/**
	 * Gets the Send JButton
	 * @return Send
	 */
	public JButton getSend() {
		return Send;
	}
	
	/**
	 * Starts the GUI (for testing)
	 * @param args
	 */
	public static void main(String[] args)
	{
		EmailGUI e = new EmailGUI();
		e.frmEmail.setVisible(true);
	}

}
