package model.util.impl;

import model.entities.Driver;
import model.util.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;

public class DriverValidator implements Validator<Driver> {
    private static final Logger LOGGER = LogManager.getLogger(DriverValidator.class);
    private ResourceBundle regexps;
    private static volatile DriverValidator instance;

    private DriverValidator() {
        regexps = ResourceBundle.getBundle("regexps");
    }

    public static DriverValidator getInstance() {
        if (instance == null) {
            synchronized (DriverValidator.class) {
                if (instance == null) {
                    instance = new DriverValidator();
                }
            }
        }
        return instance;
    }

    public boolean validateFirstNameEn(String s){
        LOGGER.debug("Validate first name en method");
        return (s!=null) && (s.matches(this.regexps.getString("regexp.f.name.en")));
    }

    public boolean validateFirstNameUa(String s){
        LOGGER.debug("Validate first name ua method");
        return (s!=null) && (s.matches(this.regexps.getString("regexp.f.name.ua")));
    }

    public boolean validateLastNameEn(String s){
        LOGGER.debug("Validate last name en method");
        return (s!=null) && (s.matches(this.regexps.getString("regexp.l.name.en")));
    }

    public boolean validateLastNameUa(String s){
        LOGGER.debug("Validate last name ua method");
        return (s!=null) && (s.matches(this.regexps.getString("regexp.l.name.ua")));
    }

    @Override
    public boolean validate (Driver driver) {
        LOGGER.info("Validate driver method");

        return driver != null
                && validateFirstNameEn(driver.getFirstNameEn())
                && validateFirstNameUa(driver.getFirstNameUa())
                && validateLastNameEn(driver.getLastNameEn())
                && validateLastNameUa(driver.getLastNameUa())
                && (driver.getCarType() != null);
    }
}
