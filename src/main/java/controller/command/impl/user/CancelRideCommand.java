package controller.command.impl.user;

import controller.command.Command;
import controller.command.impl.common.LoginCommand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class CancelRideCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CancelRideCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Cancelling ride command");
        request.getSession().setAttribute("current_ride", null);
        return "redirect:/action/home";
    }
}
