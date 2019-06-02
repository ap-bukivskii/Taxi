package model.dao;

import exception.NotFoundException;
import exception.WrongInputDataException;

import java.util.List;

/**
 * The general interface that specifies basic CRUD operations with specified entities
 * @param <T> specifies the entity class in the implementations
 */

public interface GenericDAO<T> extends AutoCloseable{
    /**
     * Method to write entity to the DB
     * @param entity specifies the entity to be written to DB
     * @return returns true if the entity was written successfully
     */
    boolean create(T entity);// throws WrongInputDataException;

    /**
     * Method to get the entity from the DB
     * @param id specifies the entity to get from the DB
     * @return returns an object of the entity class
     * @throws NotFoundException if the id is not found in the DB
     */
    T findById(int id) throws NotFoundException;

    /**
     *
     * Method to get all of the entities from the DB
     * @return returns the {@link List} of entities
     */
    List<T> findAll();

    /**
     * Method to update an entity in the DB
     * @param entity specifies the entity to be updated
     * @return returns true if update was successful
     */
    boolean update(T entity);

    /**
     * Method to delete an entity in the DB
     * @param id specifies the id of the entity to be deleted
     * @return returns true if deletion was successful
     */
    boolean delete(int id);
    //TODO booleans to void + exceptions
}
