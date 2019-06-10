package model.dao.mapper.impl;


import model.exception.WrongInputDataException;
import model.dao.mapper.Mapper;
import model.entities.Special;
import model.entities.prop.DiscountType;
import model.entities.prop.special.SpecialType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ImplSpecialMapper implements Mapper<Special> {
    private static final Logger LOGGER = LogManager.getLogger(ImplSpecialMapper.class);


    @Override
    public Special parseFromResultSet(ResultSet resultSet) throws SQLException {
        LOGGER.info("Parsing special from resultset");

        Special special = new Special();

        special.setId(resultSet.getInt("idspecial"));
        special.setNameEn(resultSet.getString("name_en"));
        special.setNameUa(resultSet.getString("name_ua"));
        special.setDescriptionUa(resultSet.getString("desc_ua"));
        special.setDescriptionEn(resultSet.getString("desc_en"));
        special.setDiscountType(DiscountType.valueOf(resultSet.getString("discount_type").toUpperCase()));
        special.setDiscountAmount(resultSet.getInt("discount_amount"));
        special.setSpecialType(SpecialType.valueOf(resultSet.getString("special_type").toUpperCase()));
        special.setRule(resultSet.getString("rule"));
        special.setActive(resultSet.getBoolean("active"));

        return special;
    }

    @Override
    public Special parseFromRequest(HttpServletRequest request) throws WrongInputDataException {
        LOGGER.error("Parsing special from request. always null");

        return null;
    }

    @Override
    public Special makeUnique(Map<Integer, Special> cache, Special entity) {

        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
