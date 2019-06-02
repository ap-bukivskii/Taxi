package model.dao.mapper.impl;

import exception.WrongInputDataException;
import model.dao.mapper.Mapper;
import model.entities.User;
import model.entities.prop.Role;
import model.services.impl.ImplUserService;
import model.util.Validator;
import model.util.impl.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ImplUserMapper implements Mapper<User> {
    private static final Logger LOGGER = LogManager.getLogger(ImplUserMapper.class);
    @Override
    public User parseFromResultSet(ResultSet resultSet) throws SQLException {
        LOGGER.info("Parsing user from resultset");

        User user = new User();

        user.setId(resultSet.getInt("iduser"));
        user.setFirstNameEn(resultSet.getString("user.first_name_en")); // yes, I have done something stupid while projecting the DB
        user.setFirstNameUa(resultSet.getString("user.first_name_ua")); // yes, I can rename the columns now but I dont want to...
        user.setLastNameEn(resultSet.getString("user.last_name_en"));
        user.setLastNameUa(resultSet.getString("user.last_name_ua"));
        user.setEmail(resultSet.getString("user.email"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("pass"));
        user.setMoneySpent(resultSet.getInt("money_spent"));
        user.setRidesCount(resultSet.getInt("user.rides_count"));
        user.setPersonalDiscount(resultSet.getInt("personal_discount"));
        user.setAdditionalDiscount(resultSet.getInt("additional_discount"));
        user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));

        return user;
    }

    @Override
    public User parseFromRequest(HttpServletRequest request) throws WrongInputDataException {
        LOGGER.info("Parsing user from request");

        User user = new User();

        user.setFirstNameEn(request.getParameter("f-name-en"));
        user.setFirstNameUa(request.getParameter("f-name-ua"));
        user.setLastNameEn(request.getParameter("l-name-en"));
        user.setLastNameUa(request.getParameter("l-name-ua"));
        user.setEmail(request.getParameter("email"));
        user.setLogin(request.getParameter("login"));
        user.setPassword(BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt()));

        if (request.getParameter("user-role") != null){
            user.setRole(Role.valueOf(request.getParameter("user-role")));
        }else{
            user.setRole(Role.USER);
        }
        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
