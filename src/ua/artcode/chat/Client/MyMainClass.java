package ua.artcode.chat.Client;
import java.io.*;
import java.net.Socket;

/**
 * Created by root on 11.05.2015.
 */
public class MyMainClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8888);

        ReceiverThread rt = new ReceiverThread(socket);
        SenderThread st = new SenderThread(socket);

        Thread Receive = new Thread(rt);
        Thread Sender = new Thread(st);

        Receive.start();
        Sender.start();

    }
}
