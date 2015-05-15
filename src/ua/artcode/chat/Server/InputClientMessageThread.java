package ua.artcode.chat.Server;

import ua.artcode.chat.Message.Message;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 13.05.2015.
 */
class InputClientMessageThread implements Runnable, Observable {

    private OutputClientMessageSender observer;
    private BufferedReader bf;
    private String ip;
    private int port;
    private ObjectInputStream oin;
    private InputStream is;
    private Message msg;
    private Socket client;
    private List <Observer> observers;
    private int messagesCounter;


    public InputClientMessageThread(String ip, int port,InputStream inputStream) {
        this.ip = ip;
        this.port = port;
        bf = new BufferedReader(new InputStreamReader(inputStream));
        this.is = inputStream;
        this.messagesCounter = 0;
        //ua.artcode.chat.Message.Message msg = new ua.artcode.chat.Message.Message();

    }

    public InputClientMessageThread(OutputClientMessageSender observer, Socket client) {
        this.observer = observer;
        this.client = client;
        this.ip = client.getInetAddress().toString();
        this.port = client.getPort();
        this.observers = new LinkedList<Observer>();
        try {
            this.bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.is = client.getInputStream();
            this.oin = new ObjectInputStream(this.is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputClientMessageThread(Socket client) {
        this.observer = observer;
        this.client = client;
        this.ip = client.getInetAddress().toString();
        this.port = client.getPort();
        this.observers = new LinkedList<Observer>();
        try {
            this.bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.is = client.getInputStream();
            this.oin = new ObjectInputStream(this.is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                msg = new Message();
                msg = (Message)oin.readObject();
                if(msg != null){
                    //String message = ip + ":" + ":" + port + " -> " + s;
                    notifyObservers();
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >=0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(msg);

       }
    }
}
