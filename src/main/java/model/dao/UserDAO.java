package model.dao;

import model.entities.User;
/**
 * {@inheritDoc}
 */
public interface UserDAO extends GenericDAO<User> {
    /**
     * Method to get the {@link User} entity from the DB by the login
     * @param login specifies the login of the user
     * @return returns an object of the user class
     */
    User findByLogin(String login);
}
