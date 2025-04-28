package p6;

import java.net.*;

import java.net.*;
import java.util.Random;

public class DhcpServer {
    public static void main(String args[]) {
        try {
            // Start the server and listen on port 67 (DHCP port)
            System.out.println("DHCP Server Running...!");
            DatagramSocket sdsoc = new DatagramSocket(5050);
            
            while (true) {
                byte[] sendByte = new byte[1204];
                byte[] receiveByte = new byte[1204];
                
                // Wait for a request packet from the client
                DatagramPacket recePak = new DatagramPacket(receiveByte, receiveByte.length);
                sdsoc.receive(recePak);
                
                // Extract the MAC address from the packet
                byte[] mac = recePak.getData();
                System.out.print("Server allocating IP Address to: ");
                for (int i = 0; i <= 5; i++) {
                    System.out.format("%02X%s", mac[i], (i < 5) ? "-" : " ");
                }
                
                // Prepare to send an IP address to the client
                InetAddress addr = recePak.getAddress();
                int port = recePak.getPort();
                
                // Predefined set of IP addresses for allocation
                String[] ip = { "10.0.3.186", "10.0.3.187", "10.0.3.188", "10.0.3.189", "10.0.3.190" };
                
                // Randomly choose an IP from the list
                Random rand = new Random();
                int i = rand.nextInt(4);
                ip[i] = ip[i] + " lease time -> 24 hrs";
                
                System.out.println("-> " + ip[i]);
                
                // Send the IP address to the client
                sendByte = ip[i].getBytes();
                DatagramPacket sendPak = new DatagramPacket(sendByte, sendByte.length, addr, port);
                sdsoc.send(sendPak);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}




