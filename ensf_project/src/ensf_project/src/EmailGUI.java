package ensf_project.src;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ensf_project.src.StudentGUImain.emailListener;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Rectangle;

public class EmailGUI {

	JFrame frmEmail;
	JTextArea SubjectMessage;
	JTextArea EmailMessage;
	JButton Send;

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
	
	public void setListeners(emailListener listener) {
		Send.addActionListener(listener);
	}
	
	public String getSubject() {
		return SubjectMessage.getText();
	}
	
	public String getMessage() {
		return EmailMessage.getText();
	}
	
	public JButton getSend() {
		return Send;
	}
	
	public static void main(String[] args)
	{
		EmailGUI e = new EmailGUI();
		e.frmEmail.setVisible(true);
	}
}
