package p2manual;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            Socket s = new Socket("localhost", 5001);

            System.out.print("Enter String to reverse: ");
            String input = sc.nextLine();
            
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(input);

            DataInputStream din = new DataInputStream(s.getInputStream());
            String reversed = din.readUTF();

            System.out.println("Reversed String: " + reversed);

            dout.close();
            din.close();
            s.close();
        }
    }
}
