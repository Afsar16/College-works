package p1;

import java.io.*;
import java.net.*; 
public class server {

	public static void main(String[] args) {
		try {
			System.out.println("server is running "); 
			ServerSocket ss = new ServerSocket(6666);  
			Socket s = ss.accept(); 
			DataInputStream din = new DataInputStream(s.getInputStream()); 
			String str = (String) din.readUTF(); 
			StringBuilder revstr = new StringBuilder(str).reverse(); 
			System.out.println(revstr);
			ss.close(); 
			
		} catch (Exception e) {
			System.out.println("e");
		}
	}

}

