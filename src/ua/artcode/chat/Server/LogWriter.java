package ua.artcode.chat.Server;

import ua.artcode.chat.Message.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by root on 14.05.2015.
 */
public class LogWriter implements Observer {

    private Message message;
    private FileWriter logWriter;
    private boolean notified;

    public LogWriter() {
        Date logStarted = new Date();
        String dt = (String.format("%1$ty_%1$tm_%1$td_%1$tH-%1$tM-%1$tS", logStarted));
        File logFile = new File("log_" + dt + ".txt");
        try {
            logWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public synchronized void update(Message message) {

        Date date = new Date();
        String dt = (String.format("%1$ty.%1$tm.%1$td %1$tH:%1$tM:%1$tS", date));
        try {
            logWriter.append(dt + " " + message.getUserName() + " " + message.getText() + "\n");
            logWriter.flush();
            System.out.println(message.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
