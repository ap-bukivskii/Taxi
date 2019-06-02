package controller.listeners;

import model.entities.User;
import model.services.SessionManagerService;
import model.services.impl.ImplSessionManagerService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.SessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Optional;

public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionListener.class);

    private SessionManagerService sessionManagerService = ImplSessionManagerService.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Optional<User> optionalUser =  Optional.ofNullable((User)httpSessionEvent.getSession().getAttribute("user"));
        optionalUser.ifPresent(sessionManagerService::removeLoggedUser);
        LOGGER.info("User session expired");
    }
}


