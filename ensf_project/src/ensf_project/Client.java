package ensf_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	Socket theSocket;
	ObjectOutputStream toServer;
	ObjectInputStream fromServer;
	
	BufferedReader socketIn;
	PrintWriter socketOut;
	
	public Client(String hostname, int port) {
		try {
		theSocket = new Socket(hostname, port);
		toServer = new ObjectOutputStream(theSocket.getOutputStream());
		fromServer = new ObjectInputStream(theSocket.getInputStream());
		socketIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
		socketOut = new PrintWriter(theSocket.getOutputStream(), true);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		Client client = new Client("localhost", 9909);
		LoginGUI login = new LoginGUI(client.socketOut, client.fromServer);
	}
}
