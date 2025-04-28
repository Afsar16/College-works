package p6;

import java.net.*;
import java.util.Enumeration;

public class DhcpClient {
    public static void main(String args[]) {
        try {
            // Create a socket to communicate
            DatagramSocket cdsoc = new DatagramSocket();
            
            // Create byte arrays for sending and receiving data
            byte[] sendByte = new byte[1204];
            byte[] receiveByte = new byte[1024];
            
            System.out.println("Sending the MAC address to server:");

            // Get all available network interfaces
            Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
            NetworkInterface ni = null;
            
            // Loop through the network interfaces to find one with a valid MAC address
            while (nics.hasMoreElements()) {
                ni = nics.nextElement();
                if (ni != null && ni.getHardwareAddress() != null) {
                    break;  // Exit when a valid network interface is found
                }
            }

            if (ni == null || ni.getHardwareAddress() == null) {
                System.out.println("No valid network interface found!");
                return;
            }
            
            // Retrieve the MAC address from the network interface
            sendByte = ni.getHardwareAddress();
            
            // Display the MAC address
            for (int i = 0; i < sendByte.length; i++) {
                System.out.format("%02X%s", sendByte[i], (i < sendByte.length - 1) ? "-" : " ");
            }
            
            // Get the server address (localhost in this case)
            InetAddress address = InetAddress.getByName("localhost");
            
            // Create a packet and send the MAC address to the server (port 67)
            DatagramPacket sender = new DatagramPacket(sendByte, sendByte.length, address, 67);
            cdsoc.send(sender);
            
            // Receive the IP address response from the server
            DatagramPacket recePak = new DatagramPacket(receiveByte, receiveByte.length);
            cdsoc.receive(recePak);
            
            // Convert the response to string and print the assigned logical address (IP)
            String s = new String(recePak.getData());
            System.out.println("\nThe Logical Address is: " + s.trim());
            
            // Close the socket
            cdsoc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
