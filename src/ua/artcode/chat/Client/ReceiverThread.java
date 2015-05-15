package ua.artcode.chat.Client;
import java.io.*;
import java.net.Socket;

/**
 * Created by root on 11.05.2015.
 */
public class ReceiverThread implements Runnable {

    private Socket socket;
    public ReceiverThread(Socket socket) {
        this.socket = socket;
    }
    @Override

    public void run() {
        BufferedReader br;
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader receiver = new InputStreamReader(is);
            br = new BufferedReader(receiver);

        while(true) {
            String remoteMessage = br.readLine();
            System.out.println(remoteMessage);
            Thread.sleep(250);
        }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
