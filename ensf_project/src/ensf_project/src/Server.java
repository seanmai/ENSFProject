package ensf_project.src;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Contains methods and fields to retrieve communicate with User
 * through Sockets
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
public class Server {
	/**
	 * ServerSocket used to communicate
	 */
	ServerSocket serverSocket;
	
	/**
	 * Thread Pool used to assign workers
	 */
	ExecutorService threadPool;
	
	/**
	 * 
	 * @param portNumber
	 */
	public Server(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			threadPool = Executors.newCachedThreadPool();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Begins Listening for connecting Clients
	 * and Assigning threads
	 */
	public void communicate() {
		try {
			while(true) {
				Worker worker = new Worker(serverSocket.accept());
				if(worker != null) {
					threadPool.execute(worker);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			threadPool.shutdown();
		}
	}
	
	/**
	 * Initiates the Server Side
	 * @param args
	 */
	public static void main(String[] args) {
		Server test = new Server(9909);
		System.out.println("Server up");
		test.communicate();
	}
}

