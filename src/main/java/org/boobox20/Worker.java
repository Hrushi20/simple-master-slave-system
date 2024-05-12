package org.boobox20;

import java.io.*;
import java.net.Socket;

public class Worker extends Thread{

    Socket socket;

    public Worker(Socket socket){
       this.socket = socket;
        System.out.println("Initialized Worker Thread");
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while(true){
                line = bufferedReader.readLine();
                if (line == null){
                    System.out.println("Closing the socket");
                    socket.close();
                    return;
                }

                System.out.println("Worker Thread: Reading data from Socket: " + line);
            }

        }catch (IOException e){
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
