package controller.command.impl.user;

import controller.command.Command;
import model.dao.DAOFactory;
import model.entities.Ride;
import model.services.DriverService;
import model.services.UserService;
import model.services.impl.ImplDriverService;
import model.services.impl.ImplUserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class ConfirmRideCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ConfirmRideCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Confirm ride command");
        UserService userService = ImplUserService.getInstance();
        DriverService driverService = ImplDriverService.getInstance();
        Ride currentRide = (Ride) request.getSession().getAttribute("current_ride");
        String URI;

        if(currentRide != null
                && userService.updateUserAfterRide(currentRide)
                && driverService.updateDriverAfterRide(currentRide)){
            DAOFactory.getInstance().getRideDAO().create((Ride)request.getSession().getAttribute("current_ride"));
            request.getSession().setAttribute("current_ride", null);
            LOGGER.debug("ride confirmed  successfully");
            URI = "/WEB-INF/user/success_ride.jsp";
        }else{
            LOGGER.debug("ride not confirmed due to the error");
            request.setAttribute("error_message", "e.unknown");
            URI = "/WEB-INF/error.jsp";
        }
        return URI;
    }
}
