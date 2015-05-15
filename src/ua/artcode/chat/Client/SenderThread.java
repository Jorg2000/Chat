package ua.artcode.chat.Client;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import ua.artcode.chat.Message.*;

/**
 * Created by root on 11.05.2015.
 */
public class SenderThread implements Runnable {

    private Socket socket;
    private Message message;
    public SenderThread(Socket socket) {
        this.socket = socket;
        this.message = new Message();
    }

    @Override

    public void run() {

        BufferedReader br;
        try {
            OutputStream os = socket.getOutputStream();
           // PrintWriter pw = new PrintWriter(os);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            while(true) {
            Scanner scn = new Scanner(System.in);
            while (scn.hasNext())
            {
                message.setText(scn.nextLine());
                oos.writeObject(message);
                oos.flush();
                oos.reset();

              //  pw.println(scn.nextLine());
              //  pw.flush();
            }
        }

        } catch (IOException e) {
            e.printStackTrace();
            }
        }


}

