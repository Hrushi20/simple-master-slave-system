package org.boobox20;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Slave {
    public static void main(String[] args) {
        System.out.println("Started Slave Application");

        String PORT = System.getProperty("appPort");
        String[] masterPort = System.getProperty("masterPort").split(",");

        // Spin a thread to Ping the Master Server.
        new HeartBeat(masterPort,"SLAVE").start();;

        Socket socket;
        try(ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT))){
            while (true){
                socket = serverSocket.accept();

                new Worker(socket).start();
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
