package p3;

import java.io.*;
import java.net.*;
public class Server3 {
	public static void main(String[] args) throws Exception {
        // Create a ServerSocket to listen for incoming connections on port 4002
        ServerSocket serverSocket = new ServerSocket(4002);
        System.out.println("Server Running...!");

        // Accept the incoming client connection
        Socket serverConnection = serverSocket.accept();

        // Set up the file input stream to read the file that will be sent 
        // create a file name output.txt in the desktop 
        FileInputStream fileIn = new FileInputStream("/home/afsar786/Desktop/output.txt");

        // Set up the DataOutputStream to send data to the client
        DataOutputStream dataOut = new DataOutputStream(serverConnection.getOutputStream());

        int byteRead;
        int byteCount = 0;

        // Read the file byte by byte and send it to the client
        while ((byteRead = fileIn.read()) != -1) {
            dataOut.write(byteRead);
            byteCount++;
        }

        System.out.println("The file capacity is " + byteCount + " bytes.");
        System.out.println("File transfer completed....!");

        // Close all the streams and connections
        fileIn.close();
        serverConnection.close();
        serverSocket.close();
    }
} 
