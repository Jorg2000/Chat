package ua.artcode.chat.Server;

import ua.artcode.chat.Message.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 12.05.2015.
 */
class OutputClientMessageSender implements Observer{

    private List<PrintWriter> clientOutputStreamList = new LinkedList<>();

    public void addNewStream(Socket client) {
        try {
            clientOutputStreamList.add(new PrintWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        Date date = new Date();
        String dt = (String.format("%1$ty.%1$tm.%1$td %1$tH:%1$tM:%1$tS", date));
        for (PrintWriter pw : clientOutputStreamList){
            pw.println(dt + " User: " + message.getUserName() + " Says " + message.getText());
            pw.flush();
        }
    }
}