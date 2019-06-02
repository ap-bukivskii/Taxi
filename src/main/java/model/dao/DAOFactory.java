package model.dao;

import model.dao.impl.ImplDAOFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class DAOFactory {
    private static final Logger LOGGER = LogManager.getLogger(DAOFactory.class);

    private static volatile DAOFactory instance;

    public abstract DriverDAO getDriverDAO();

    public abstract RideDAO getRideDAO();

    public abstract SpecialDAO getSpecialDAO();

    public abstract UserDAO getUserDAO();

    public static DAOFactory getInstance() {
        LOGGER.info("getting dao factory instance");
        if (instance == null) {
            synchronized (DAOFactory.class) {
                if (instance == null) {
                    instance = new ImplDAOFactory();
                }
            }
        }
        return instance;
    }
}
