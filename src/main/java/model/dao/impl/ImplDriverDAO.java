package model.dao.impl;

import model.exception.NotFoundException;
import model.exception.WrongInputDataException;
import model.dao.DriverDAO;
import model.dao.mapper.Mapper;
import model.dao.mapper.impl.ImplDriverMapper;
import model.entities.Driver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * {@inheritDoc}
 */
public class ImplDriverDAO implements DriverDAO {
    private static final Logger LOGGER = LogManager.getLogger(ImplDriverDAO.class);
    private Connection connection;
    private Mapper<Driver> driverMapper;
    private ResourceBundle queries;

    public ImplDriverDAO(Connection connection) {
        this.connection = connection;
        this.queries = ResourceBundle.getBundle("sql_queries");
        this.driverMapper = new ImplDriverMapper();
    }

    @Override
    public boolean create(Driver entity) throws WrongInputDataException {
        LOGGER.info("Create driver method");

        boolean resultFlag = false;
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.insert.driver"))) {
            statement.setString(1, entity.getFirstNameUa());
            statement.setString(2, entity.getLastNameUa());
            statement.setString(3, entity.getFirstNameEn());
            statement.setString(4, entity.getLastNameEn());
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getCarType().getName());
            statement.setDouble(7, entity.getRating());
            statement.setInt(8, entity.getRidesCount());
            if (statement.executeUpdate() > 0) {
                LOGGER.debug("created driver successfully");
                resultFlag = true;
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            LOGGER.info("Driver already exist");
            throw new WrongInputDataException("Driver already exist", e);
        }catch (SQLException e) {
            LOGGER.error("Threw an SQLException:" + e);
        }
        return resultFlag;
    }

    @Override
    public Driver findById(int id) throws NotFoundException {
        Driver result;
        LOGGER.info("Find driver by id method");
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.driver.by.id"))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = driverMapper.parseFromResultSet(resultSet);
            LOGGER.debug("found driver successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException:" + e);
            throw new NotFoundException("Driver id: \""+id+"\" is not registered.", e);
        }
        return result;
    }

    @Override
    public List<Driver> findAll() {
        LOGGER.info("Find all drivers method");

        List<Driver> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.all.drivers"))){
            //LOGGER.debug("Try to find all speakers");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Driver driver = driverMapper.parseFromResultSet(resultSet);
                result.add(driver);
            }
            LOGGER.debug("found drivers successfully");
        }catch (SQLException e){
            LOGGER.error("Threw an SQLException:" + e);
        }
        return result;
    }

    @Override
    public boolean update(Driver entity) {
        boolean resultFlag = false;
        LOGGER.info("Update driver method");
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.update.driver"))) {

            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getCarType().getName());
            statement.setDouble(3, entity.getRating());
            statement.setInt(4, entity.getRidesCount());
            statement.setInt(5, entity.getId());

            if (statement.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("updated driver successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException:" + e);
        }
        return resultFlag;
    }

    @Override
    public boolean delete(int id) {
        boolean resultFlag = false;
        LOGGER.info("Delete driver method");
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.delete.driver.by.id"))) {
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                LOGGER.debug("driver deleted successfully");
                resultFlag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Threw an SQLException:" + e);
        }
        return resultFlag;
    }

    @Override
    public void close() throws RuntimeException{
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
