package model.util.impl;
import model.entities.User;
import model.util.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class UserValidator implements Validator<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    private ResourceBundle regexps;

    private static volatile UserValidator instance;

    private UserValidator() {
        regexps = ResourceBundle.getBundle("regexps");
    }

    public static UserValidator getInstance() {
        if (instance == null) {
            synchronized (UserValidator.class) {
                if (instance == null) {
                    instance = new UserValidator();
                }
            }
        }
        return instance;
    }

    public boolean validatePass(String s){
        LOGGER.debug("Validate password method");
        return (s!=null) && (s.matches(this.regexps.getString("regexp.pass")));
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

    public boolean validateLogin(String s){
        LOGGER.debug("Validate login method");
        return (s!=null) && (s.matches(this.regexps.getString("regexp.login")));
    }

    public boolean validateRole(String s){
        LOGGER.debug("Validate role method");
        return (s!=null) && (s.matches(this.regexps.getString("regexp.role")));
    }


    @Override
    public boolean validate (User user) {
        LOGGER.info("Validate user method");

        return user != null
                //&&validatePass(user.getPassword()) //pass is already crypted. have to validate earlier
                && validateLogin(user.getLogin())
                && validateFirstNameEn(user.getFirstNameEn())
                && validateFirstNameUa(user.getFirstNameUa())
                && validateLastNameEn(user.getLastNameEn())
                && validateLastNameUa(user.getLastNameUa())
                && (user.getRole() != null);
    }
}
