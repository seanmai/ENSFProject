package ensf_project.src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Contains methods and fields to create a graphical user interface
 * for user login.
 * 
 * @author Wafa Anam, Sea Main, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class LoginGUI extends JFrame{
	
	/**
	 * The GUI frame
	 */
	private JFrame frame;
	
	/**
	 * Text field to input user ID
	 */
	private JTextField userID;
	
	/**
	 * Password field to input user password
	 */
	private JPasswordField password;
	
	/**
	 * Button to call method to handle login logic
	 */
	private JButton login;
	
	/**
	 * Button to exit the GUI
	 */
	private JButton exit;

	/**
	 * Client for communication with backend
	 */
	private Client client;
	
//	public static void main(String[] args) {
//		LoginGUI login = new LoginGUI();
//	}
	
	/**
	 * Initializes login GUI
	 */
	public LoginGUI() {
		client = new Client("localhost", 9909);
		displayLogin();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(true);
	}
	
	/**
	 * Displays and populates GUI frames
	 */
	private void displayLogin() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(100, 100, 620, 390);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//Text Field, Password and Button Components
		userID = new JTextField();
		userID.setFont(new Font("DialogInput", Font.PLAIN, 13));
		userID.setBackground(new Color(255, 255, 255));
		userID.setBounds(243, 148, 200, 26);
		frame.getContentPane().add(userID);
		userID.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("DialogInput", Font.PLAIN, 13));
		password.setBounds(243, 205, 200, 26);
		frame.getContentPane().add(password);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {login();}});
		login.setBackground(new Color(245, 245, 245));
		login.setFont(new Font("Monospac821 BT", Font.PLAIN, 13));
		login.setBounds(354, 289, 89, 23);
		frame.getContentPane().add(login);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {System.exit(1);}});
		exit.setBackground(new Color(245, 245, 245));
		exit.setFont(new Font("Monospac821 BT", Font.PLAIN, 13));
		exit.setBounds(183, 289, 89, 23);
		frame.getContentPane().add(exit);
		
		
		//Aesthetic Pieces
		JLabel lblUsername = new JLabel("User ID:");
		lblUsername.setFont(new Font("Monospac821 BT", Font.PLAIN, 14));
		lblUsername.setBounds(152, 147, 81, 26);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Monospac821 BT", Font.PLAIN, 14));
		lblPassword.setBounds(152, 205, 81, 26);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblLearningPlatform = new JLabel("Learning Platform");
		lblLearningPlatform.setFont(new Font("Monospac821 BT", Font.BOLD, 26));
		lblLearningPlatform.setBounds(183, 34, 300, 65);
		frame.getContentPane().add(lblLearningPlatform);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(127, 126, 357, 129);
		frame.getContentPane().add(panel);
	}
	
	/**
	 * Handles login logic
	 */
	public void login()
	{
		int id = Integer.parseInt(userID.getText());

		String Password = new String(password.getPassword());
		
		User user = checkIfUser(id);
		
		if(Password.equals(user.getPassword())){
			if(user.getType().equals("P"))
			{
				frame.dispose();
				ProfessorGUImain professorGUI = new ProfessorGUImain(user, client);
			}
			else if(user.getType().equals("S"))
			{
				frame.dispose();
				StudentGUImain studentGUI = new StudentGUImain(user, client);
			}
			
			else {
				System.exit(1);
			}
		} else {
			System.out.println("Incorrect password");
		}
	}
	
	/**
	 * Checks if the user exists in the database
	 * @param id User ID input into login
	 * @return The returned user matching the sql query of the id
	 */
	public User checkIfUser(int id)
	{
		String query = "SEARCH USER ID";
		
		client.getSocketOut().println(query);
		client.getSocketOut().println(Integer.toString(id));
		client.getSocketOut().flush();
		
		User user = null;
		try {
			user = (User)client.getFromServer().readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return user;
	}
}
