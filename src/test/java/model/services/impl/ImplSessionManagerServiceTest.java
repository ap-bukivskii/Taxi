package model.services.impl;

import model.entities.Driver;
import model.entities.User;
import model.entities.prop.CarType;
import model.entities.prop.Role;
import model.services.SessionManagerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ImplSessionManagerServiceTest {

    private User user;
    private Driver driver;
    private SessionManagerService sessionManagerService;

    @Before
    public void initialize(){
        sessionManagerService = ImplSessionManagerService.getInstance();

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

        driver = new Driver();
        driver.setId(1);
        driver.setFirstNameEn("Test");
        driver.setFirstNameUa("Тестовий");
        driver.setLastNameEn("Driver");
        driver.setLastNameUa("Водій");
        driver.setEmail("testdriver@fmail.not");
        driver.setRidesCount(7);
        driver.setCarType(CarType.SEDAN);
        driver.setRating(4.8);
    }

    @After
    public void destroy(){
        sessionManagerService = null;
    }


    @Test
    public void getAllLoggedUsersLogins() {
        sessionManagerService.addLoggedUser(user);
        List<String> userLogins = new ArrayList<>();
        userLogins.add(user.getLogin());
        assertEquals(userLogins, sessionManagerService.getAllLoggedUsersLogins());
    }

    @Test
    public void isUserLogged() {
        sessionManagerService.addLoggedUser(user);
        assertTrue(sessionManagerService.isUserLogged(user.getLogin()));
    }

//    @Test
//    public void addLoggedUser() {
//        assertTrue(sessionManagerService.addLoggedUser(user));
//    }

    @Test
    public void addTwiceLoggedUser() {
        sessionManagerService.addLoggedUser(user);
        assertFalse(sessionManagerService.addLoggedUser(user));
    }

    @Test
    public void removeLoggedUser() {
        sessionManagerService.addLoggedUser(user);
        sessionManagerService.removeLoggedUser(user);
        assertFalse(sessionManagerService.getAllLoggedUsersLogins().contains(user.getLogin()));
    }

    @Test
    public void addLoggedDriver() {
        assertTrue(sessionManagerService.addLoggedDriver(driver));
    }

    @Test
    public void removeLoggedDriver() {
        SessionManagerService sessionManagerService = ImplSessionManagerService.getInstance();
        sessionManagerService.addLoggedDriver(driver);
        sessionManagerService.removeLoggedDriver(driver);
        assertFalse(sessionManagerService.getAllLoggedDrivers().contains(driver));
    }

}