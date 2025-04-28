package p4;

import java.net.*;
import java.io.*;

public class Client4 {
    @SuppressWarnings("resource")
    public static void main(String args[]) throws Exception {
        byte b[] = new byte[1024]; // Buffer to store file data
        System.out.println("Connecting to UDP Server ....!");

        // Open the file to be sent 
        // create a.txt in the desktop 
        FileInputStream fin = new FileInputStream("/home/afsar786/Desktop/a.txt");

        // Create DatagramSocket to send data
        DatagramSocket dsoc = new DatagramSocket();

        int i = 0;
        // Read file and send it byte by byte
        while (fin.available() != 0) {
            b[i] = (byte) fin.read();
            i++;
        }

        fin.close();

        // Send data to the server (ensure the server is listening on port 5003)
        dsoc.send(new DatagramPacket(b, i, InetAddress.getLocalHost(), 5067)); // Port 5003 must match server
        System.out.println("File sent successfully!");

        dsoc.close(); // Close the socket after operation
    }
}
