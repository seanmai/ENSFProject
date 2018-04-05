package ensf_project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	Socket theSocket;
	ObjectOutputStream toServer;
	ObjectInputStream fromServer;
	
	public Client(String hostname, int port) {
		try {
		theSocket = new Socket(hostname, port);
		toServer = new ObjectOutputStream(theSocket.getOutputStream());
		fromServer = new ObjectInputStream(theSocket.getInputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		Client test = new Client("localhost", 9909);
		LoginGUI login = new LoginGUI();
	}
}
