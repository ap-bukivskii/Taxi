package model.dao;

import model.entities.Driver;
import model.entities.Ride;
import model.entities.User;

import java.util.List;
/**
 * {@inheritDoc}
 */
public interface RideDAO extends GenericDAO<Ride> {
    /**
     * Method to get all of the {@link Ride} from the DB for specified driver
     * @param driver specifies the driver all of the rides for which are to be found
     * @return returns the {@link List} of {@link Ride}
     */
    List<Ride> findByDriver(Driver driver);
    /**
     * Method to get all of the {@link Ride} from the DB for specified user
     * @param user specifies the user all of the rides for which are to be found
     * @return returns the {@link List} of {@link Ride}
     */
    List<Ride> findByUser(User user);
}
