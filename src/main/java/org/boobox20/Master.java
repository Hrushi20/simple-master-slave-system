package org.boobox20;

import java.io.IOException;
import java.net.*;

public class Master {
    public static void main(String[] args){
       System.out.println("Started Master Application");

       String PORT = System.getProperty("appPort");
       String[] slavePorts = System.getProperty("slavePorts").split(",");

        // Spin a thread to Ping slave servers.
       new HeartBeat(slavePorts,"MASTER").start();;

       Socket socket;
       Thread worker;
       try(ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT))) {
           System.out.println("Created Socket on Port: " + PORT);
            while(true){
               socket = serverSocket.accept();
                System.out.println("Accepted connection from: " + socket.getLocalPort());

               worker = new Worker(socket);
               worker.start();
            }

       }catch (IOException e){
           System.out.println("Couldn't create Server Socket on Port: " + PORT);
       }
    }
}