package model.dao.impl;

import model.dao.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ImplDAOFactory extends DAOFactory {
    private static final Logger LOGGER = LogManager.getLogger(ImplDAOFactory.class);


    @Override
    public DriverDAO getDriverDAO() {
        LOGGER.debug("getting driver dao");
        return new ImplDriverDAO(getConnection());
    }

    @Override
    public RideDAO getRideDAO() {
        LOGGER.debug("getting ride dao");
        return new ImplRideDAO(getConnection());
    }

    @Override
    public SpecialDAO getSpecialDAO() {
        LOGGER.debug("getting special dao");
        return new ImplSpecialDAO(getConnection());
    }

    @Override
    public UserDAO getUserDAO() {
        LOGGER.debug("getting user dao");
        return new ImplUserDAO(getConnection());
    }

    private Connection getConnection(){
        LOGGER.debug("establishing connection");

        try {
            LOGGER.debug("connection established successfully");
            return ConnectionPool.getInstance().getConnection();
        }catch (SQLException e){
            LOGGER.error("could not establish connection"+ e);
            throw new RuntimeException(e);
        }
    }
}
