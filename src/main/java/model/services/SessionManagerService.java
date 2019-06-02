package model.services;

import exception.NotFoundException;
import model.entities.Driver;
import model.entities.User;
import model.entities.prop.CarType;

import java.util.List;

public interface SessionManagerService {

    List<String> getAllLoggedUsersLogins();
    boolean isUserLogged(String login);
    boolean addLoggedUser(User user);
    int removeLoggedUser(User user);
    boolean addLoggedDriver(Driver driver);
    int removeLoggedDriver(Driver driver);
    List<Driver> getAllLoggedDrivers();
    Driver getNearestDriver(CarType carType, String userLocation) throws NotFoundException;
}
