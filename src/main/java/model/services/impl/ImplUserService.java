package model.services.impl;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.impl.ImplDAOFactory;
import model.entities.Ride;
import model.entities.User;
import model.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class ImplUserService implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(ImplUserService.class);

    private static volatile UserService instance;
    private ImplUserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (ImplUserService.class) {
                if (instance == null) {
                    instance = new ImplUserService();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean updateUserAfterRide(Ride ride) {
        LOGGER.info("Update user after ride method");

        boolean resultFlag = false;
        try{
            User user = ride.getUser();
            user.setRidesCount(user.getRidesCount()+1);
            user.setMoneySpent(user.getMoneySpent()+ride.getCost());
            user.setPersonalDiscount(updatePersonalDiscount(user));
            resultFlag = true;
            LOGGER.debug("user info updated successfully");
        }catch (Exception e){
            LOGGER.error("Threw an Exception:" + e);
        }

        return resultFlag;
    }

    @Override
    public int updatePersonalDiscount(User user) {
        LOGGER.info("Update personal discount method");

        int discount = 0;
        if(user.getMoneySpent()>1000){
            discount = 3;
        }
        if(user.getMoneySpent()>5000){
            discount = 5;
        }
        if(user.getMoneySpent()>10000){
            discount = 7;
        }

        if(user.getPersonalDiscount()>discount){
            LOGGER.error("present discount is greater than calculated. Not changing");
            discount = user.getPersonalDiscount();
        }
        return discount;
    }
}
