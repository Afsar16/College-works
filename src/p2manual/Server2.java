package p2manual;

import java.net.*;
import java.io.*;

public class Server2 {
    public static void main(String[] args) throws Exception {
        int count = 1;
        System.out.println("Server Running...");

        ServerSocket ss = new ServerSocket(5001);

        while (true) {
            Socket socket = ss.accept();
            System.out.println(count + " Client connected.");
            new RevThread(socket, count).start();
            count++;
        }
    }
}

class RevThread extends Thread {
    private Socket s;
    private int clientNumber;

    public RevThread(Socket socket, int number) {
        this.s = socket;
        this.clientNumber = number;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Receiving from client " + clientNumber);
                DataInputStream din = new DataInputStream(s.getInputStream());
                String str = din.readUTF();

                System.out.println("Processing data of Client " + clientNumber);
                String revStr = new StringBuilder(str).reverse().toString();

                System.out.println("Sending to client " + clientNumber);
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                dout.writeUTF(revStr);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
