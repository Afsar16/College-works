package p5;

import java.net.*;

public class Server5 {
    public static void main(String args[]) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1308);
            System.out.println("ARP Server Running...!");
            
            while (true) {
                // Create a DatagramPacket to receive the data
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                
                // Receive the data
                serverSocket.receive(receivePacket);
                
                String macAddress = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                
                // Define the known MAC and corresponding IP
                String knownMac = "D4:3D:7E:12:A3:D9";
                String ip = "10.0.3.186";
                
                // Check if the received MAC matches the known MAC address
                if (macAddress.equals(knownMac)) {
                    byte[] sendData = ip.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                    serverSocket.send(sendPacket);
                }
                
                // Exit after handling one request (you can remove this to handle more requests)
                break;
            }
            
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
