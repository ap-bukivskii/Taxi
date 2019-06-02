package model.dao;

import model.entities.Ride;
import model.entities.Special;

import java.util.List;
/**
 * {@inheritDoc}
 */
public interface SpecialDAO extends GenericDAO<Special> {
    /**
     * Method to get all of the specials of the specified {@link Ride} from the DB
     * @param ride specifies the ride for which specials are to be found
     * @return returns {@link List} of {@link Ride}
     */
    List<Special> findByRide(Ride ride);

    /**
     * Method to create a link between the specified {@link Ride} and all of the {@link Special}
     * that are stored in the object
     * @param ride specifies the {@link Ride} to process
     * @return returns true if the operation was successfull
     */
    boolean createRideSpecialConnection(Ride ride);
}
