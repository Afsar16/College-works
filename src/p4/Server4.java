package p4;

import java.net.*;
import java.io.*;

public class Server4 {
    @SuppressWarnings("resource")
    public static void main(String args[]) throws IOException {
        byte b[] = new byte[2048]; // Buffer to store received data
        System.out.println("UDP Server Running ....!");

        // DatagramSocket to listen on port 5003
        DatagramSocket dsoc = new DatagramSocket(5067);

        // FileOutputStream to write the received file
        // created.txt will be created in the desktop 
        FileOutputStream fout = new FileOutputStream("/home/afsar786/Desktop/created.txt");

        // DatagramPacket to store incoming data
        DatagramPacket dp = new DatagramPacket(b, b.length);

        // Receive data from the client
        dsoc.receive(dp);

        // Write the received data directly to the file (no need to convert to String)
        fout.write(dp.getData(), 0, dp.getLength());

        System.out.println("File transfer completed ....!");
        fout.close();
        dsoc.close(); // Close the socket after operation
    }
}

