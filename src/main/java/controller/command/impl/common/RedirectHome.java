package controller.command.impl.common;

import controller.command.Command;
import model.entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class RedirectHome implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RedirectHome.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Redirect home command");
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            LOGGER.debug("not logged in, redirecting to login page");
            return "redirect:/login.jsp";
        }
        LOGGER.debug("redirecting to home page");
        return "/WEB-INF/"+user.getRole().getName()+"/home.jsp";
    }
}
