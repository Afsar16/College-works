package p2;

import java.io.*;
import java.net.*;
import java.util.*; 

public class server2 {
	public static ServerSocket server; 
	
	public static void main(String[] args) throws Exception {
		server = new ServerSocket(6666); 
		SocketServerThread m1 = new SocketServerThread();
		SocketServerThread m2 = new SocketServerThread();
		m1.run();
		m2.run();
	}
	
	static class SocketServerThread extends Thread {
		 
		public void run() {
			try {
				while(true) {
					Socket s = server.accept(); 
					DataInputStream din = new DataInputStream(s.getInputStream());
					String str = (String) din.readUTF(); 
					StringBuilder revStr = new StringBuilder(str).reverse();
					System.out.println(revStr); 
					s.close(); 
				}
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	}

}
