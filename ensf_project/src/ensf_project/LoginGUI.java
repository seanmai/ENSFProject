package ensf_project;
import java.awt.*;
import javax.swing.*;

public class LoginGUI extends JFrame{
	JTextField userID;
	JTextField password;
	JButton submit;
	
	public LoginGUI() {
		displayLogin();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Change when adding other GUI
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
		password = new JTextField(10);
		loginForm.add(password, c);
		
		c.anchor = GridBagConstraints.LINE_END;
		c.gridy++;
		submit = new JButton("Login");
		loginForm.add(submit, c);
		
	}
	public static void main(String[] args) {
		LoginGUI login = new LoginGUI();

	}

}
