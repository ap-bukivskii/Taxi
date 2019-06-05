package controller.command.impl.admin;

import controller.command.Command;
import model.dao.DAOFactory;
import model.entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewUserDetails implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewUserDetails.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("View user details command");
        User user;
        try {
            user = DAOFactory.getInstance().getUserDAO().findById(Integer.parseInt(request.getParameter("userid")));
            user.setRidesHistory(DAOFactory.getInstance().getRideDAO().findByUser(user));
        }catch (RuntimeException e){
            request.setAttribute("error_message", e.getMessage());
            return "/WEB-INF/error.jsp";
        }

        request.getSession().setAttribute("user_info", user);
        return "/WEB-INF/admin/userDetails.jsp";
    }
}
