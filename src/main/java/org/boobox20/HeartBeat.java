package org.boobox20;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class HeartBeat extends Thread {

    String[] ports;
    String nodeType;

    public HeartBeat(String[] ports, String nodeType){
        this.ports = ports;
        this.nodeType = nodeType;
    }

    @Override
    public void run() {

        PrintWriter printWriter;
        try {
            while (true) {

                System.out.println("Start Pinging Nodes");
                for(String port : ports){
                    try(Socket socket = new Socket("localhost",Integer.parseInt(port))){
                       printWriter = new PrintWriter(socket.getOutputStream(),true);
                       printWriter.println("HEARTBEAT from " + nodeType);
                    } catch (IOException e) {
                        System.out.println("Server at PORT " + port + " is down");
                    }
                }
                System.out.println("Finished Pinging");
                Thread.sleep(5000);
            }
        }catch (InterruptedException e){
            System.out.println("Interrupted Pinging");
        }
    }
}
