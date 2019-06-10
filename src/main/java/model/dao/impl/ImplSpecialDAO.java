package model.dao.impl;

import model.exception.NotFoundException;
import model.exception.WrongInputDataException;
import model.dao.SpecialDAO;
import model.dao.mapper.Mapper;
import model.dao.mapper.impl.ImplSpecialMapper;
import model.entities.Ride;
import model.entities.Special;
import model.util.RideUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * {@inheritDoc}
 */
public class ImplSpecialDAO implements SpecialDAO {
    private static final Logger LOGGER = LogManager.getLogger(ImplSpecialDAO.class);

    private Connection connection;
    private Mapper<Special> specialMapper;
    private ResourceBundle queries;

    public ImplSpecialDAO(Connection connection) {
        this.connection = connection;
        this.specialMapper = new ImplSpecialMapper();

        this.queries = ResourceBundle.getBundle("sql_queries");
    }

    @Override
    public boolean create(Special entity) throws WrongInputDataException { //TODO make admin able to add specials
        LOGGER.error("Create special method. Always false!");
        return false;
    }

    @Override
    public Special findById(int id) throws NotFoundException {
        LOGGER.info("Find special by id method");
        Special result = null;
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.special.by.id"))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = specialMapper.parseFromResultSet(resultSet);
            LOGGER.debug("special found successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException:" + e);
            throw new NotFoundException("Could not found special id: \""+id+"\" .", e);
        }
        return result;
    }

    @Override
    public List<Special> findAll() {
        LOGGER.info("finf all specials method");
        List<Special> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.all.specials"))) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Special special = specialMapper.parseFromResultSet(resultSet);
                result.add(special);
            }
            LOGGER.debug("specials found successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException:" + e);
        }
        return result;
    }

    @Override
    public boolean update(Special entity) {
        LOGGER.error("Update special method. Always false!");
        return false;
    } //TODO make admin able to set specials active/inactive

    @Override
    public boolean delete(int id) {
        LOGGER.error("Delete special method. Always false!");
        return false;
    }

    @Override
    public List<Special> findByRide(Ride ride) {
        LOGGER.info("find specials by ride method");
        List<Special> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.specials.by.ride"))) {
            statement.setInt(1, ride.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Special special = specialMapper.parseFromResultSet(resultSet);
                result.add(special);
            }
            LOGGER.debug("specials found successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException:" + e);
            throw new NotFoundException("Could not found specials for ride id: \""+ride.getId()+"\" .", e);
        }
        return result;
    }

    @Override
    public boolean createRideSpecialConnection(Ride ride) {
        LOGGER.info("create connection between ride and its specials method");
        boolean resultFlag = false;
            try (PreparedStatement statement = connection.prepareStatement(RideUtils.generateSQLLinkRS(ride))) {
                if(statement.executeUpdate() > 0){
                    LOGGER.debug("connection created successfully");
                    resultFlag = true;
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                LOGGER.error("Error while creating link ride - special: not unique ", e);
                throw new WrongInputDataException(e.getMessage(), e);
            } catch (SQLException e) {
                LOGGER.error("Threw an SQLException:" + e);
                throw new RuntimeException(e);
            }
        return resultFlag;

    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
