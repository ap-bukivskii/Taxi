package model.dao.impl;

import model.dao.UserDAO;
import model.entities.User;
import model.entities.prop.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImplUserDAOTest {

    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;

    private User user;

    @Before
    public void initialize() throws SQLException {
        when(dataSource.getConnection()).thenReturn(connection);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(statement.executeUpdate()).thenReturn(1);
        when(resultSet.next()).thenReturn(true,false);
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        user = new User();
        user.setId(1);
        user.setLogin("testuser");
        user.setPassword("cryptedpassword");
        user.setRole(Role.USER);
        user.setFirstNameEn("Test");
        user.setFirstNameUa("Тестовий");
        user.setLastNameEn("User");
        user.setLastNameUa("Користувач");
        user.setEmail("test@fmail.not");
        user.setPersonalDiscount(3);
        user.setMoneySpent(1200);
        user.setRidesCount(7);
        user.setAdditionalDiscount(0);

        when(resultSet.getInt("iduser")).thenReturn(user.getId());
        when(resultSet.getString("login")).thenReturn(user.getLogin());
        when(resultSet.getString("pass")).thenReturn(user.getPassword());
        when(resultSet.getString("role")).thenReturn(user.getRole().getName());
        when(resultSet.getString("user.email")).thenReturn(user.getEmail());
        when(resultSet.getString("user.first_name_en")).thenReturn(user.getFirstNameEn());
        when(resultSet.getString("user.first_name_ua")).thenReturn(user.getFirstNameUa());
        when(resultSet.getString("user.last_name_en")).thenReturn(user.getLastNameEn());
        when(resultSet.getString("user.last_name_ua")).thenReturn(user.getLastNameUa());
        when(resultSet.getInt("money_spent")).thenReturn(user.getMoneySpent());
        when(resultSet.getInt("user.rides_count")).thenReturn(user.getRidesCount());
        when(resultSet.getInt("personal_discount")).thenReturn(user.getPersonalDiscount());
        when(resultSet.getInt("additional_discount")).thenReturn(user.getAdditionalDiscount());
    }


    @Test
    public void create() {
        UserDAO userDAO = new ImplUserDAO(connection);
        assertTrue(userDAO.create(user));
    }

    @Test
    public void findById() {
        UserDAO userDAO = new ImplUserDAO(connection);
        assertEquals(user, userDAO.findById(user.getId()));
    }

    @Test
    public void findAll() {
        UserDAO userDAO = new ImplUserDAO(connection);
        List<User> users = new ArrayList<>();
        users.add(user);
        assertEquals(users, userDAO.findAll());
    }

    @Test
    public void update() {
        UserDAO userDAO = new ImplUserDAO(connection);
        assertTrue(userDAO.update(user));
    }

    @Test
    public void delete() {
        UserDAO userDAO = new ImplUserDAO(connection);
        assertTrue(userDAO.delete(user.getId()));
    }

    @Test
    public void findByLogin() {
        UserDAO userDAO = new ImplUserDAO(connection);
        assertEquals(user, userDAO.findByLogin(user.getLogin()));
    }
}