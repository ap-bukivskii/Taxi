package controller.command.impl.common;

import controller.command.Command;
import exception.NotFoundException;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entities.User;
import model.services.SessionManagerService;
import model.services.impl.ImplSessionManagerService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class LoginCommand implements Command {
    private UserDAO userDAO ;
    private SessionManagerService sessionManagerService;
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);


    public LoginCommand() {
        LOGGER.debug("Login command constructor");
        userDAO = DAOFactory.getInstance().getUserDAO();
        sessionManagerService = ImplSessionManagerService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) { //TODO refactor
        LOGGER.info("Login command start executing");
        User user ;
        String pass  = request.getParameter("password");
        String login = request.getParameter("login").trim();
        LOGGER.debug("user "+login+" "+pass+" is trying to log in");

        if(request.getSession().getAttribute("user") != null){
            LOGGER.debug("already logged in this session");
            return "/login.jsp";
        }

        if(sessionManagerService.isUserLogged(login)){
            LOGGER.debug("already logged somewhere else");
            request.setAttribute("login_error", "m.user.already.logged.in");
            return "/login.jsp";
        }


        try {
            user = userDAO.findByLogin(login);//TODO case sensitivity issue
        }catch (NotFoundException e){
            LOGGER.debug("wrong login");
            request.setAttribute("login_error", "m.wrong.login");
            return "/login.jsp";
        }

        if (BCrypt.checkpw(pass,user.getPassword())
                && sessionManagerService.addLoggedUser(user)){
            LOGGER.debug("successfully logged in");
            request.getSession().setAttribute("user", user);
            return "redirect:/action/home";
        }else{
            LOGGER.debug("wrong password");
            request.setAttribute("login_error", "m.wrong.pass");
            return "/login.jsp";
        }

    }
}
