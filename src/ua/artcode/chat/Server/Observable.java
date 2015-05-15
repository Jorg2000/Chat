package ua.artcode.chat.Server;

/**
 * Created by root on 14.05.2015.
 */
public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
