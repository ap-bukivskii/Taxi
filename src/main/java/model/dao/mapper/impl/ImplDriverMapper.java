package model.dao.mapper.impl;

import exception.WrongInputDataException;
import model.dao.mapper.Mapper;
import model.entities.Driver;
import model.entities.prop.CarType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ImplDriverMapper implements Mapper<Driver> {
    private static final Logger LOGGER = LogManager.getLogger(ImplDriverMapper.class);

    @Override
    public Driver parseFromResultSet(ResultSet resultSet) throws SQLException {

        LOGGER.info("Parsing driver from resultset");
        Driver driver = new Driver();

        driver.setId(resultSet.getInt("iddriver"));
        driver.setFirstNameEn(resultSet.getString("driver.first_name_en"));// yes, I have done something stupid while projecting the DB
        driver.setFirstNameUa(resultSet.getString("driver.first_name_ua"));// yes, I can rename the columns now but I dont want to...
        driver.setLastNameEn(resultSet.getString("driver.last_name_en"));
        driver.setLastNameUa(resultSet.getString("driver.last_name_ua"));
        driver.setEmail(resultSet.getString("driver.email"));
        driver.setCarType(CarType.valueOf(resultSet.getString("car_type").toUpperCase()));
        driver.setRating(resultSet.getDouble("rating"));
        driver.setRidesCount(resultSet.getInt("driver.rides_count"));

        return driver;
    }


    @Override
    public Driver parseFromRequest(HttpServletRequest request) throws WrongInputDataException {

        LOGGER.info("Parsing driver from request");

        Driver driver = new Driver();

        driver.setFirstNameEn(request.getParameter("f-name-en"));
        driver.setFirstNameUa(request.getParameter("f-name-ua"));
        driver.setLastNameEn(request.getParameter("l-name-en"));
        driver.setLastNameUa(request.getParameter("l-name-ua"));
        driver.setEmail(request.getParameter("email"));
        driver.setCarType(CarType.valueOf(request.getParameter("car_type")));

        return driver;
    }

    @Override
    public Driver makeUnique(Map<Integer, Driver> cache, Driver entity) {

        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
