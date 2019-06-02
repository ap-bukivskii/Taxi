package model.services.impl;

import exception.NotFoundException;
import model.dao.DAOFactory;
import model.entities.Driver;
import model.entities.SessionManager;
import model.entities.User;
import model.entities.prop.CarType;
import model.services.DriverService;
import model.services.SessionManagerService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ImplSessionManagerService implements SessionManagerService {
    private static final Logger LOGGER = LogManager.getLogger(ImplSessionManagerService.class);

    private static volatile SessionManagerService instance;
    private SessionManager sessionManager;
    private DriverService driverService = ImplDriverService.getInstance();

    private ImplSessionManagerService() {
        this.sessionManager = SessionManager.getInstance();
        DAOFactory.getInstance().getDriverDAO().findAll().forEach(this::addLoggedDriver);
        // dont have real people as drivers so just autologin them all
    }

    public static SessionManagerService getInstance(){
        if(instance == null){
            synchronized (ImplSessionManagerService.class){
                if(instance == null){
                    instance = new ImplSessionManagerService();
                }
            }
        }
        return instance;
    }


    @Override
    public List<String> getAllLoggedUsersLogins() {
        LOGGER.info("get all logged users logins method");

        List<String>  res = new ArrayList<>();
        for (User user: sessionManager.getLoggedUsers()) {
            res.add(user.getLogin());
        }
        return res;
    }


    @Override
    public boolean isUserLogged(String login) {
        LOGGER.info("is user logged method");

        for (User user: sessionManager.getLoggedUsers()) {
            if (user.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addLoggedUser(User user) {
        LOGGER.info("add logged user method");
        return sessionManager.getLoggedUsers().addIfAbsent(user);
    }

    @Override
    public int removeLoggedUser(User user) {
        LOGGER.info("remove logged user method");
        int id = user.getId();
        sessionManager.getLoggedUsers().remove(user);
        return id;
    }

    @Override
    public boolean addLoggedDriver(Driver driver) {
        LOGGER.info("add logged driver method");
        return sessionManager.getLoggedDrivers().addIfAbsent(driver);
    }

    @Override
    public int removeLoggedDriver(Driver driver) {
        LOGGER.info("remove logged driver method");
        int id = driver.getId();
        sessionManager.getLoggedDrivers().remove(driver);
        return id;
    }

    @Override
    public List<Driver> getAllLoggedDrivers() {
        LOGGER.info("get all logged drivers method");
        return sessionManager.getLoggedDrivers();
    }

    @Override
    public Driver getNearestDriver(CarType carType, String userLocation) throws NotFoundException {

        return this.getAllLoggedDrivers().stream()
                .filter(driverService::checkIfAvailable)
                .filter(d->d.getCarType() == carType)
                .min(Comparator.comparingInt(d -> driverService.checkDistance(d, userLocation)))
                .orElseThrow(NotFoundException::new);
    }

}
