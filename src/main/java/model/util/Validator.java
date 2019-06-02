package model.util;

import model.entities.User;

import javax.servlet.http.HttpServletRequest;

public interface Validator<T> {
    boolean validate(T entity);
}
