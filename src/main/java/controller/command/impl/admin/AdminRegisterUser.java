package controller.command.impl.admin;

import controller.command.Command;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.mapper.Mapper;
import model.dao.mapper.impl.ImplUserMapper;
import model.entities.User;
import model.util.impl.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class AdminRegisterUser implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AdminRegisterUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Admin registration user command");
        String URI;
        User user;
        Mapper<User> userMapper = new ImplUserMapper();
        UserValidator userValidator = UserValidator.getInstance();
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

        user = userMapper.parseFromRequest(request);

        if (!userValidator.validate(user)
                && !userValidator.validatePass(request.getParameter("password"))) {
            LOGGER.debug("user validation failed");
            request.setAttribute("registration_error", "m.wrong.registration.data");
            URI = "/WEB-INF/admin/home.jsp";
        }else if(! userDAO.create(user)) {
            LOGGER.debug("user already exists");
            request.setAttribute("registration_error", "m.user.already.exist");
            URI = "/WEB-INF/admin/home.jsp";
        }else {
            LOGGER.debug("user successfully registered");
            request.setAttribute("operation_message","m.reg.user.success");
            URI = "/WEB-INF/admin/success.jsp";
        }
        return URI;
    }
}