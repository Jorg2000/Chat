package ua.artcode.chat.Server;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 16.05.2015.
 */
public class ConnectedUsers {

    private List<String> users;
    public ConnectedUsers() {
        this.users = new LinkedList<String>();
    }

    public void addUser(String userName){
        users.add(userName);
    }


}
