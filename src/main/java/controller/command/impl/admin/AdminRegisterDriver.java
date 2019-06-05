package controller.command.impl.admin;

import controller.command.Command;
import model.dao.DAOFactory;
import model.dao.DriverDAO;
import model.dao.mapper.Mapper;
import model.dao.mapper.impl.ImplDriverMapper;
import model.entities.Driver;
import model.util.impl.DriverValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class AdminRegisterDriver implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AdminRegisterDriver.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Admin registration driver command");
        String URI;
        Driver driver;
        Mapper<Driver> driverMapper = new ImplDriverMapper();
        DriverValidator driverValidator = DriverValidator.getInstance();
        DriverDAO driverDAO = DAOFactory.getInstance().getDriverDAO();

        driver = driverMapper.parseFromRequest(request);

        if (!driverValidator.validate(driver)) {
            LOGGER.debug("driver validation failed");
            request.setAttribute("registration_error", "m.wrong.registration.data");
            URI = "/WEB-INF/admin/home.jsp";
        }else if(! driverDAO.create(driver)) {
            LOGGER.debug("driver already exists");
            request.setAttribute("registration_error", "m.driver.already.exist");
            URI = "/WEB-INF/admin/home.jsp";
        }else {
            LOGGER.debug("driver successfully registered");
            request.setAttribute("operation_message","m.reg.driver.success");
            URI = "/WEB-INF/admin/success.jsp";
        }
        return URI;
    }
}