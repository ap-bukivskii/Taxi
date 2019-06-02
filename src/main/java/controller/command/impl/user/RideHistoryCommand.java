package controller.command.impl.user;

import controller.command.Command;
import model.dao.DAOFactory;
import model.entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class RideHistoryCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RideHistoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("ride history command");
        request.getSession().setAttribute("ride_history", DAOFactory.getInstance().getRideDAO().findByUser((User)request.getSession().getAttribute("user")));
        return "/WEB-INF/user/rideHistory.jsp";
    }
}
