package p5;

import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client5 {
    public static void main(String args[]) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            
            // Get the MAC address input from the user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the Physical Address (MAC): ");
            String macAddress = reader.readLine();
            
            // Send the MAC address to the server
            byte[] sendData = macAddress.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1308);
            clientSocket.send(sendPacket);
            
            // Receive the IP address from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            
            // Print the received logical address (IP)
            String logicalAddress = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("The Logical Address is: " + logicalAddress);
            
            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
