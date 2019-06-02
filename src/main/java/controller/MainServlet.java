package controller;

import controller.command.Command;
import controller.command.CommandFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller functional. Receives and proceeds http requests from Tomcat
 */
public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);


    @Override
    public void init() throws ServletException { //TODO command factory
        LOGGER.info("Initializing main servlet");
    }

    private void processUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("PROCESS USER METHOD");
        String path = req.getRequestURI();
        path = path.replaceAll(".*/action/", "");
        Command command = CommandFactory.getInstance().getCommand(path);
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
