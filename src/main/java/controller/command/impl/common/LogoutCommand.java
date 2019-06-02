package controller.command.impl.common;

import controller.command.Command;
import model.entities.User;
import model.services.SessionManagerService;
import model.services.impl.ImplSessionManagerService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
public class LogoutCommand implements Command {
    private SessionManagerService sessionManagerService;
    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    public LogoutCommand() {
        sessionManagerService = ImplSessionManagerService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Logout command");
        Optional<User> optionalUser =  Optional.ofNullable((User)request.getSession().getAttribute("user"));
        optionalUser.ifPresent(sessionManagerService::removeLoggedUser);
        request.getSession().invalidate();
        return "redirect:/action/index";
    }
}
