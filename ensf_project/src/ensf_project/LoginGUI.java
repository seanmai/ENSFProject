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
	JTextField userID;
	JPasswordField password;
	JButton submit;
	PrintWriter socketOut;
	ObjectInputStream fromServer;
	
	public LoginGUI(PrintWriter out, ObjectInputStream in) {

		socketOut = out;
		fromServer = in;
		
		displayLogin();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private void displayLogin() {
		setTitle("Login");
//		setLayout(new GridLayout(1, 2));
		
		JPanel loginForm = new JPanel(new GridBagLayout());
		add(loginForm);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		loginForm.add(new JLabel("User ID: "), c);
		c.gridy++;
		loginForm.add(new JLabel("Password: "), c);
		
		c.gridy = 0;
		c.gridx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		userID = new JTextField(10);
		loginForm.add(userID, c);
		c.gridy++;
		password = new JPasswordField(10);
		loginForm.add(password, c);
		
		c.anchor = GridBagConstraints.LINE_END;
		c.gridy++;
		submit = new JButton("Login");
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				login();
			}
		});
		loginForm.add(submit, c);
	}
	
	public void login()
	{
		//Client client = new Client("localhost", 9099);
		String query = "VERIFY USER";
		int id = Integer.parseInt(userID.getText());
		
		@SuppressWarnings("deprecation")
		String Password = password.getText();
		
		User user = checkIfUser(query, id, Password);
		
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
	
	public User checkIfUser(String query, int id, String password)
	{
		socketOut.println(query);
		socketOut.print(id);
		socketOut.println(password);
		socketOut.flush();
		
		User user = null;
		try {
			user = (User)fromServer.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
