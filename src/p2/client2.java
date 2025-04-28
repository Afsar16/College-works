package p2;

import java.io.*;
import java.net.*;
import java.util.*; 

public class client2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter the message");
			Socket s = new Socket("localhost", 6666); 
			DataOutputStream dout = new DataOutputStream(s.getOutputStream()); 
			dout.writeUTF(sc.nextLine()); 
			dout.flush(); 
			s.close(); 
			dout.close(); 
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
