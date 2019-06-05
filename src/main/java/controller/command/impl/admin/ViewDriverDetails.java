package controller.command.impl.admin;

import controller.command.Command;
import model.dao.DAOFactory;
import model.entities.Driver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDriverDetails implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewDriverDetails.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("View driver details command");
        Driver driver;
        try {
            driver = DAOFactory.getInstance().getDriverDAO().findById(Integer.parseInt(request.getParameter("driverid")));
            driver.setRidesHistory(DAOFactory.getInstance().getRideDAO().findByDriver(driver));
        }catch (RuntimeException e){
            request.setAttribute("error_message", e.getMessage());
            return "/WEB-INF/error.jsp";
        }

        request.getSession().setAttribute("driver_info", driver);
        return "/WEB-INF/admin/driverDetails.jsp";
    }
}
