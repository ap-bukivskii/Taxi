package model.services;

import model.entities.Ride;
import model.entities.User;

import java.sql.SQLException;

public interface UserService {
    boolean updateUserAfterRide(Ride ride);
    int updatePersonalDiscount(User user);
}
