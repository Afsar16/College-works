package p1;
import java.util.*;
import java.io.*; 
import java.net.*; 
public class client {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        try {
        	System.out.println("write a message ");
        	Socket s = new Socket("localhost", 6666); 
        	DataOutputStream dout = new  DataOutputStream(s.getOutputStream());
        	dout.writeUTF(sc.nextLine()); 
        	dout.flush(); 
        	dout.close(); s.close(); 
        } catch (Exception e) {
        	System.out.println(e); 
        }
    }

}
