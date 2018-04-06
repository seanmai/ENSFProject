package ensf_project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class LoginGUI extends JFrame{
	JFrame frame;
	JTextField userID;
	JPasswordField password;
	JButton login;
	JButton exit;
	
	PrintWriter socketOut;
	ObjectInputStream fromServer;
	
	public LoginGUI(PrintWriter out, ObjectInputStream in) {

		socketOut = out;
		fromServer = in;
		
		displayLogin();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(true);
	}
	
	private void displayLogin() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(100, 100, 620, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userID = new JTextField();
		userID.setFont(new Font("Monospac821 BT", Font.PLAIN, 13));
		userID.setBackground(new Color(255, 255, 255));
		userID.setBounds(243, 148, 200, 26);
		frame.getContentPane().add(userID);
		userID.setColumns(10);
		
		JLabel lblUsername = new JLabel("User ID:");
		lblUsername.setFont(new Font("Monospac821 BT", Font.PLAIN, 14));
		lblUsername.setBounds(152, 147, 81, 26);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Monospac821 BT", Font.PLAIN, 14));
		lblPassword.setBounds(152, 205, 81, 26);
		frame.getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Monospac821 BT", Font.PLAIN, 13));
		password.setBounds(243, 205, 200, 26);
		frame.getContentPane().add(password);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(127, 126, 357, 129);
		frame.getContentPane().add(panel);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		login.setBackground(new Color(245, 245, 245));
		login.setFont(new Font("Monospac821 BT", Font.PLAIN, 14));
		login.setBounds(354, 289, 89, 23);
		frame.getContentPane().add(login);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		exit.setBackground(new Color(245, 245, 245));
		exit.setFont(new Font("Monospac821 BT", Font.PLAIN, 14));
		exit.setBounds(183, 289, 89, 23);
		frame.getContentPane().add(exit);
		
		JLabel lblLearningPlatform = new JLabel("Learning Platform");
		lblLearningPlatform.setFont(new Font("Monospac821 BT", Font.BOLD, 26));
		lblLearningPlatform.setBounds(167, 32, 300, 65);
		frame.getContentPane().add(lblLearningPlatform);
		frame.setVisible(true);
	}
	
	public void login()
	{
		int id = Integer.parseInt(userID.getText());

		String Password = new String(password.getPassword());
		
		User user = checkIfUser(id, Password);
		
		if(user.getType().equals("P"))
		{
			ProfessorGUImain gui = new ProfessorGUImain(user);
			setVisible(false);
			
		}
		else if(user.getType().equals("S"))
		{
			
		}
		else {
			System.exit(1);
		}
	}
	
	public User checkIfUser(int id, String password)
	{
		String query = "SEARCH USER ID";
		socketOut.println(query);
		socketOut.println(id);
		socketOut.flush();
		
		User user = null;
		try {
			user = (User)fromServer.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return user;
	}

}
