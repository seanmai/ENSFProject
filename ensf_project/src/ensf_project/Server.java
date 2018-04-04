package ensf_project;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	ServerSocket serverSocket;
	ExecutorService threadPool;
	
	
	public Server(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			threadPool = Executors.newCachedThreadPool();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void communicate() {
		try {
			while(true) {
				Worker worker = new Worker(serverSocket.accept());
				threadPool.execute(worker);
			}
		}catch(Exception e) {
			e.printStackTrace();
			threadPool.shutdown();
		}
	}
	
	public static void main(String[] args) {
		Server test = new Server(9909);
		System.out.println("Server up");
		test.communicate();
	}
}
