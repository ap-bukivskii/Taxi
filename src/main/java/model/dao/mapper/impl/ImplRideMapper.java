package model.dao.mapper.impl;

import model.exception.WrongInputDataException;
import model.dao.mapper.Mapper;
import model.entities.Driver;
import model.entities.Ride;
import model.entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class ImplRideMapper implements Mapper<Ride> {
    private static final Logger LOGGER = LogManager.getLogger(ImplRideMapper.class);


    @Override
    public Ride parseFromResultSet(ResultSet resultSet) throws SQLException {
        LOGGER.info("Parsing ride from resultset");

        Ride ride = new Ride();
        Mapper<User> userMapper = new ImplUserMapper();
        Mapper<Driver> driverMapper = new ImplDriverMapper();
        //Mapper<Special> specialMapper = new ImplSpecialMapper();

        ride.setId(resultSet.getInt("idride"));
        ride.setAddressFrom(resultSet.getString("from_addr"));
        ride.setAddressTo(resultSet.getString("to_addr"));
        ride.setDistance(resultSet.getInt("dist"));
        ride.setCost(resultSet.getInt("cost"));
        ride.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());
        ride.setUser(userMapper.parseFromResultSet(resultSet));
        ride.setDriver(driverMapper.parseFromResultSet(resultSet));
        //ride.setSpecials();

        return ride;
    }

    @Override
    public Ride parseFromRequest(HttpServletRequest request) throws WrongInputDataException {
        LOGGER.info("Parsing ride from request");

        Ride ride = new Ride();
        ride.setAddressFrom(request.getParameter("routeStart"));
        ride.setAddressTo(request.getParameter("routeEnd"));
        ride.setDistance((int)Double.parseDouble(request.getParameter("distance")));
        ride.setDateTime(LocalDateTime.now());
        //ride.setUser((User)request.getSession().getAttribute("user"));
        return ride;
    }

    @Override
    public Ride makeUnique(Map<Integer, Ride> cache, Ride entity) {

        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
