package model.entities;

import java.util.concurrent.CopyOnWriteArrayList;

public class SessionManager {
    private static SessionManager instance;
    private CopyOnWriteArrayList<User> loggedUsers = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Driver> loggedDrivers = new CopyOnWriteArrayList<>();


    private SessionManager(){
    }

    public static SessionManager getInstance(){
        if(instance == null){
            synchronized (SessionManager.class){
                if(instance == null){
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }


    public CopyOnWriteArrayList<User> getLoggedUsers() {
        return loggedUsers;
    }

    public void setLoggedUsers(CopyOnWriteArrayList<User> loggedUsers) {
        this.loggedUsers = loggedUsers;
    }


    public CopyOnWriteArrayList<Driver> getLoggedDrivers() {
        return loggedDrivers;
    }

    public void setLoggedDrivers(CopyOnWriteArrayList<Driver> loggedDrivers) {
        this.loggedDrivers = loggedDrivers;
    }
}
