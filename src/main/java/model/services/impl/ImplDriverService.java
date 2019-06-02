package model.services.impl;

import model.entities.Driver;
import model.entities.Ride;
import model.services.DriverService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ImplDriverService implements DriverService {
    private static final Logger LOGGER = LogManager.getLogger(ImplDriverService.class);
    private ImplDriverService(){}
    private static volatile DriverService instance;

    public static DriverService getInstance() {
        if (instance == null) {
            synchronized (ImplDriverService.class) {
                if (instance == null) {
                    instance = new ImplDriverService();
                }
            }
        }
        return instance;
    }


    @Override
    public boolean checkIfAvailable(Driver driver) { // some drivers can be occupied at the moment
        return Math.random() < 0.8; // 80% chance that driver can take the order
    }

    @Override
    public int checkDistance(Driver driver, String userLocation) {
        return (int)(Math.random()*50);
    }

    @Override
    public int getTimeToWait(Driver driver, String userLocation) {
            return (int)(Math.random()*20);
    }

    @Override
    public boolean updateDriverAfterRide(Ride ride) {
        LOGGER.info("Update driver after ride method");
        boolean resultFlag = false;
        try{
            Driver driver = ride.getDriver();
            driver.setRidesCount(driver.getRidesCount()+1);
            resultFlag = true;
            LOGGER.debug("driver info updated successfully");
        }catch (Exception e){
            LOGGER.error("Threw an Exception:" + e);
        }
        return resultFlag;
    }
}
