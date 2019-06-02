package controller.command.impl.common;

import controller.command.Command;
import model.dao.DAOFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class ViewSpecials implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("View specials command");
        request.getServletContext().setAttribute("all_specials",DAOFactory.getInstance().getSpecialDAO().findAll());
        return "/WEB-INF/specials.jsp";
    }
}
