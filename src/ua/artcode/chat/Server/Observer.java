package ua.artcode.chat.Server;

import ua.artcode.chat.Message.Message;

/**
 * Created by root on 14.05.2015.
 */
public interface Observer {
    void update (Message message);

}
