package model.dao.impl;

import model.exception.NotFoundException;
import model.exception.WrongInputDataException;
import model.dao.*;
import model.dao.mapper.Mapper;
import model.dao.mapper.impl.ImplRideMapper;
import model.entities.Driver;
import model.entities.Ride;
import model.entities.User;
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
public class ImplRideDAO implements RideDAO {
    private static final Logger LOGGER = LogManager.getLogger(ImplRideDAO.class);
    private Connection connection;
    private Mapper<Ride> rideMapper;
    private ResourceBundle queries;

    public ImplRideDAO(Connection connection) {
        this.connection = connection;
        this.rideMapper = new ImplRideMapper();
        this.queries = ResourceBundle.getBundle("sql_queries");
    }

    @Override
    public boolean create(Ride entity) throws WrongInputDataException {
        LOGGER.info("Create ride method");

        String linkRSSQL = RideUtils.generateSQLLinkRS(entity);
        boolean resultFlag = false;
        try (PreparedStatement rideStatement = connection.prepareStatement(queries.getString("q.insert.ride"),  Statement.RETURN_GENERATED_KEYS);
             PreparedStatement userStatement = connection.prepareStatement(queries.getString("q.update.user"));
             PreparedStatement driverStatement = connection.prepareStatement(queries.getString("q.update.driver"));
             PreparedStatement specialStatement = connection.prepareStatement(linkRSSQL)) {

            //connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            System.out.println("start transaction");

            rideStatement.setInt(1, entity.getUser().getId());
            rideStatement.setInt(2, entity.getDriver().getId());
            rideStatement.setString(3, entity.getAddressFrom());
            rideStatement.setString(4, entity.getAddressTo());
            rideStatement.setInt(5, entity.getDistance());
            rideStatement.setInt(6, entity.getCost());
            rideStatement.setTimestamp(7, Timestamp.valueOf(entity.getDateTime()));

            userStatement.setString(1, entity.getUser().getEmail());
            userStatement.setString(2, entity.getUser().getPassword());
            userStatement.setInt(3, entity.getUser().getMoneySpent());
            userStatement.setInt(4, entity.getUser().getRidesCount());
            userStatement.setInt(5, entity.getUser().getPersonalDiscount());
            userStatement.setInt(6, entity.getUser().getAdditionalDiscount());
            userStatement.setInt(7, entity.getUser().getId());

            driverStatement.setString(1, entity.getDriver().getEmail());
            driverStatement.setString(2, entity.getDriver().getCarType().getName());
            driverStatement.setDouble(3, entity.getDriver().getRating());
            driverStatement.setInt(4, entity.getDriver().getRidesCount());
            driverStatement.setInt(5, entity.getDriver().getId());


            boolean rideSTMFlag = rideStatement.executeUpdate() > 0;
            ResultSet resultSet = rideStatement.getGeneratedKeys();
            resultSet.next();
            entity.setId(resultSet.getInt(1)); //TODO not very nice but works

            for(int i=1; i<=entity.getSpecials().size(); i++){
                specialStatement.setInt(i,entity.getId());
            }

            boolean specialSTMFlag = linkRSSQL.isEmpty() || specialStatement.executeUpdate() > 0;
            System.out.println("user start to db");

            if(rideSTMFlag
                    && userStatement.executeUpdate() > 0
                    && driverStatement.executeUpdate() > 0
                    && specialSTMFlag ){
                System.out.println("comiting");
                connection.commit();
                LOGGER.debug("created ride successfully");

            }else{
                System.out.println("rolling back");
                connection.rollback();
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            LOGGER.info("Ride already exist", e);
            throw new WrongInputDataException(e.getMessage(), e);
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException:" + e);
            throw new RuntimeException(e);
        }

        return resultFlag;
    }

    @Override
    public Ride findById(int id) throws NotFoundException{
        LOGGER.info("Find ride by id method");

        Ride result = null;
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.ride.by.id"))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = rideMapper.parseFromResultSet(resultSet);
            LOGGER.debug("found ride successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException:" + e);
            throw new NotFoundException("Cant find ride with id: \""+id+"\" ", e);
        }
        return result;
    }

    @Override
    public List<Ride> findAll() {
        return null; //Do we really need that?
    }

    @Override
    public boolean update(Ride entity) {
        return false; //Can`t rewrite history
    }

    @Override
    public boolean delete(int id) {
        return false; //Can`t rewrite history
    }

    @Override
    public List<Ride> findByDriver(Driver driver) {
        LOGGER.info("Find ride by driver method");

        List<Ride> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.rides.by.driver"))){
            statement.setInt(1,driver.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ride ride = rideMapper.parseFromResultSet(resultSet);
                result.add(ride);
            }
            LOGGER.debug("found ride successfully");
        }catch (SQLException e){
            LOGGER.error("Threw an SQLException:" + e);
        }
        return result;
    }

    @Override
    public List<Ride> findByUser(User user) {
        LOGGER.info("Find ride by user method");

        List<Ride> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.rides.by.user"))){
            statement.setInt(1,user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ride ride = rideMapper.parseFromResultSet(resultSet);
                result.add(ride);
            }
            LOGGER.debug("found ride successfully");
        }catch (SQLException e){
            LOGGER.error("Threw an SQLException:" + e);
        }
        return result;
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
