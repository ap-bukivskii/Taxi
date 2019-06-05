package controller.command.impl.admin;

import controller.command.Command;
import model.dao.DAOFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewUsersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewUsersCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("View all users command");
        request.setAttribute("all_users", DAOFactory.getInstance().getUserDAO().findAll());
        return "/WEB-INF/admin/allUsers.jsp";
    }
}
