package controller;

import controller.command.Command;
import controller.command.impl.common.*;
import controller.command.impl.user.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller functional. Receives and proceeds http requests from Tomcat
 */

public class MainServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);


    @Override
    public void init() throws ServletException { //TODO command factory
        LOGGER.info("Initializing main servlet");
        //common commands
        commands.put("login_do", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("home", new RedirectHome());
        commands.put("lang_ua", new ChangeLangUa());
        commands.put("lang_en", new ChangeLandEn());
        commands.put("index", new RedirectIndex());
        commands.put("register_do", new RegisterCommand());
        commands.put("viewSpecials", new ViewSpecials());


        //user commands
        commands.put("findRide", new FindRideCommand());
        commands.put("cancellRide", new CancelRideCommand());
        commands.put("confirmRide", new ConfirmRideCommand());
        commands.put("rideHistory", new RideHistoryCommand());



        //admin commands


    }

    private void processUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("PROCESS USER METHOD");
        String path = req.getRequestURI();
        path = path.replaceAll(".*/action/", "");
        Command command = commands.getOrDefault(path,new RedirectHome());
        String page = command.execute(req, resp);

        LOGGER.debug("responce page="+page);
        if (page.contains("redirect:")) {
            resp.sendRedirect(req.getContextPath() + page.replace("redirect:", ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("DO GET METHOD");
        processUser(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("DO POST METHOD");
        processUser(req, resp);
    }

}
