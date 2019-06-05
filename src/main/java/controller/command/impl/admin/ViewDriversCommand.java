package controller.command.impl.admin;

import controller.command.Command;
import model.dao.DAOFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDriversCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewDriversCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("View all drivers command");
        request.setAttribute("all_drivers", DAOFactory.getInstance().getDriverDAO().findAll());
        return "/WEB-INF/admin/allDrivers.jsp";
    }
}
