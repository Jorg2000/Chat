package ua.artcode.chat.Server; /**
 * Created by root on 12.05.2015.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;


/**
 * Created by serhii on 11.05.15.
 */
public class Server {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        File propFile = new File("config.txt");
        FileInputStream fis = new FileInputStream(propFile);
        ServerSocket ss = new ServerSocket(Integer.parseInt(properties.getProperty("port","8888")));

        LogWriter logWriter = new LogWriter();
        OutputClientMessageSender outputClientMessageSender = new OutputClientMessageSender();

        System.out.println("Hi! Chat is running on port " + ss.getLocalPort());


        while(true){
            try {
                Socket client = ss.accept();
                outputClientMessageSender.addNewStream(client);



                InputClientMessageThread inputClientMessageThread = new InputClientMessageThread(client);
                inputClientMessageThread.registerObserver(logWriter);
                inputClientMessageThread.registerObserver(outputClientMessageSender);
                Thread clientReadTread = new Thread(inputClientMessageThread);

                clientReadTread.start();

                String message = String.format("New client connection ip %s, port %s\n",
                        client.getInetAddress(),
                        client.getPort());

                System.out.println(message);

            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void writeProperties() {
        Properties properties = new Properties();
        File propFile = new File("config.txt");
        properties.setProperty("port","8888");
        properties.setProperty("maxUserSize","10");
        properties.setProperty("banList", "banList.txt");

        try {
            FileOutputStream fos = new FileOutputStream(propFile);
            properties.store(fos,"test");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}






