package model.services;

import model.entities.Driver;
import model.entities.Ride;


public interface DriverService {

    boolean checkIfAvailable(Driver driver);
    int checkDistance(Driver driver, String userLocation);
    int getTimeToWait(Driver driver, String userLocation);
    boolean updateDriverAfterRide(Ride ride);

}
