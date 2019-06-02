package model.dao.impl;

import exception.NotFoundException;
import exception.WrongInputDataException;
import model.dao.UserDAO;
import model.dao.mapper.Mapper;
import model.dao.mapper.impl.ImplUserMapper;
import model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.entities.prop.CarType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * {@inheritDoc}
 */
public class ImplUserDAO implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(ImplUserDAO.class);
    private Connection connection;
    private Mapper<User> userMapper;
    private ResourceBundle queries;

    public ImplUserDAO(Connection connection){
        this.userMapper = new ImplUserMapper();
        this.connection = connection;
        this.queries = ResourceBundle.getBundle("sql_queries");
    }


    @Override
    public boolean create(User entity) {//throws WrongInputDataException {
        LOGGER.info("Create user method");
        boolean resultFlag = false;
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.insert.user"))) {
            statement.setString(1, entity.getFirstNameUa());
            statement.setString(2, entity.getLastNameUa());
            statement.setString(3, entity.getFirstNameEn());
            statement.setString(4, entity.getLastNameEn());
            statement.setString(5, entity.getEmail());
            statement.setString(6, entity.getLogin());
            statement.setString(7, entity.getPassword());
            statement.setInt(8, entity.getMoneySpent());
            statement.setInt(9, entity.getMoneySpent());
            statement.setInt(10, entity.getPersonalDiscount());
            statement.setInt(11, entity.getAdditionalDiscount());
            statement.setString(12, entity.getRole().getName());
            if (statement.executeUpdate() > 0) {
                resultFlag = true;
                LOGGER.debug("created user successfully");
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            LOGGER.info("User already exist");
            //throw new WrongInputDataException("User already exist", e);
        }catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Threw an SQLException:" + e);
            //throw new RuntimeException(e);
        }
        return resultFlag;
    }

    @Override
    public User findById(int id) throws NotFoundException{
        User result = null;
        LOGGER.info("find user by id method. Id = " + id);
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.user.by.id"))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = userMapper.parseFromResultSet(resultSet);
            LOGGER.debug("user found successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException: " + e);
            throw new NotFoundException("User id: \""+id+"\" is not registered.", e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Find all users method");
        List<User> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.all.users"))){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = userMapper.parseFromResultSet(resultSet);
                result.add(user);
            }
            LOGGER.debug("found all users successful");
        }catch (SQLException e){
            LOGGER.error("Threw an SQLException: " + e);
            //throw new NotFoundException("Could not found all users:",e);
        }
        return result;
    }

    @Override
    public boolean update(User entity) {
        boolean resultFlag = false;
        LOGGER.info("update user method");
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.update.user"))) {

            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getMoneySpent());
            statement.setInt(4, entity.getRidesCount());
            statement.setInt(5, entity.getPersonalDiscount());
            statement.setInt(6, entity.getAdditionalDiscount());
            statement.setInt(7,entity.getId());

            if (statement.executeUpdate() > 0) {
                resultFlag = true;
            }

            LOGGER.debug("user updated successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException: " + e);
        }
        return resultFlag;
    }

    @Override
    public boolean delete(int id) { //TODO delete also all rides? Or just mark as deleted?
        boolean resultFlag = false;
        LOGGER.info("delete user method");
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.delete.user.by.id"))) {
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("user deleted successfully");
        } catch (SQLException e) {
            LOGGER.error("Threw an SQLException: " + e);
        }
        return resultFlag;
    }

    @Override
    public User findByLogin(String login) {
        LOGGER.info("find user by login method. Login = " + login);
        User user;
        try (PreparedStatement statement = connection.prepareStatement(queries.getString("q.get.user.by.login"))) {
            statement.setString(1, login); //TODO case sensitivity issue
            ResultSet result = statement.executeQuery();
            result.next();
            user = userMapper.parseFromResultSet(result);
            LOGGER.debug("user found successfully");
        } catch (SQLException e) {
            LOGGER.debug("user not found");
            throw new NotFoundException("User: \""+login+"\" is not registered.", e);
        }
        return user;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
