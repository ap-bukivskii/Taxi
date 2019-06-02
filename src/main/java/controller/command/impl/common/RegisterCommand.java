package controller.command.impl.common;

import controller.command.Command;
import exception.WrongInputDataException;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.mapper.Mapper;
import model.dao.mapper.impl.ImplUserMapper;
import model.entities.User;
import model.util.Validator;
import model.util.impl.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class RegisterCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Registration command");
        String URI;
        User user;
        Mapper<User> userMapper = new ImplUserMapper();
        Validator<User> userValidator = UserValidator.getInstance();
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        user = userMapper.parseFromRequest(request);

        if (!userValidator.validate(user)
                && !((UserValidator) userValidator).validatePass(request.getParameter("password"))) { //have to validate password here because it's getting crypted on the next step
            LOGGER.debug("user validation failed");
            request.setAttribute("registration_error", "m.wrong.registration.data");
            //request.setAttribute("activeTab", "register");
            URI = "/login.jsp";
        }else if(! userDAO.create(userMapper.parseFromRequest(request))) {
            LOGGER.debug("user already exists");
            request.setAttribute("registration_error", "m.user.already.exist");
            //request.setAttribute("activeTab", "register");
            URI = "/login.jsp";
        }else {
            LOGGER.debug("user successfully registered");
            //request.setAttribute("activeTab", "login");
            URI = "/WEB-INF/reg_success.jsp";
        }
        return URI;
    }
}