package ua.artcode.chat.Message;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 13.05.2015.
 */
public class Message implements Serializable{
    private String userName;
    private Date timeStamp;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }


}
