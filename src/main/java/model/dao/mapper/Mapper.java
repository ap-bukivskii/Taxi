package model.dao.mapper;

import model.exception.WrongInputDataException;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface Mapper<T> {
    T parseFromResultSet(ResultSet rs) throws SQLException;
    T parseFromRequest(HttpServletRequest request) throws WrongInputDataException;

    T makeUnique(Map<Integer, T> cache, T entity);

}