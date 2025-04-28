package p3;

import java.io.*;
import java.nio.file.Files;
import java.net.*;

public class Client3 {
	public static void main(String[] args) throws Exception {
        // Connect to server on localhost and port 4002
        Socket clientSocket = new Socket("localhost", 4002);
        System.out.println("Connected to Server...!");

        // Set up the file output stream to save the received file
        // you will automatically get a.txt file in the desktop 
        FileOutputStream fileOut = new FileOutputStream("/home/afsar786/Desktop/a.txt");

        // DataInputStream to read data from the server
        DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());

        int byteRead;

        // Read each byte from the input stream and write it to the file
        while ((byteRead = dataIn.read()) != -1) {
            fileOut.write(byteRead);
        }

        System.out.println("File received successfully!");

        // Close resources after transfer
        fileOut.close();
        dataIn.close();
        clientSocket.close();
    }
}


