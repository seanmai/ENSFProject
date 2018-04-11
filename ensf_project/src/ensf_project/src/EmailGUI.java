package ensf_project.src;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;

public class EmailGUI {

	private JFrame frmEmail;
	JTextArea EmailMessage;
	JButton Send;
	JButton Back;

	public JFrame getFrame() {
		return frmEmail;
	}
	

	/**
	 * Constructor, Create the Frame.
	 */
	public EmailGUI() {
		frmEmail = new JFrame();
		frmEmail.setTitle("Email");
		frmEmail.getContentPane().setBackground(new Color(153, 204, 204));
		frmEmail.getContentPane().setLayout(null);
		
		
		//JTextArea, Button Components
		EmailMessage = new JTextArea();
		EmailMessage.setBorder(new LineBorder(new Color(0, 0, 0)));
		EmailMessage.setFont(new Font("Helvetica LT", Font.PLAIN, 13));
		EmailMessage.setBounds(10, 25, 372, 177);
		EmailMessage.setBackground(new Color(255, 245, 238));
		
		Send = new JButton("Send");
		Send.setFont(new Font("Dialog", Font.PLAIN, 13));
		Send.setBounds(256, 246, 89, 23);
		frmEmail.getContentPane().add(Send);
		
		Back = new JButton("Back");
		Back.setFont(new Font("Dialog", Font.PLAIN, 13));
		Back.setBounds(94, 246, 89, 23);
		frmEmail.getContentPane().add(Back);
		frmEmail.setBounds(100, 100, 450, 324);
		frmEmail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Aesthetic Pieces
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(21, 22, 392, 213);
		panel.add(EmailMessage);
		frmEmail.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMessage.setBounds(10, 0, 76, 25);
		panel.add(lblMessage);
	}
}
