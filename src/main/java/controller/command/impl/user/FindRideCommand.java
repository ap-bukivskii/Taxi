package controller.command.impl.user;

import controller.command.Command;
import exception.NotFoundException;
import model.dao.mapper.Mapper;
import model.dao.mapper.impl.ImplRideMapper;
import model.entities.Ride;
import model.entities.User;
import model.entities.prop.CarType;
import model.services.DriverService;
import model.services.RideService;
import model.services.SessionManagerService;
import model.services.SpecialService;
import model.services.impl.ImplDriverService;
import model.services.impl.ImplRideService;
import model.services.impl.ImplSessionManagerService;
import model.services.impl.ImplSpecialService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@inheritDoc}
 */
public class FindRideCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindRideCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Find a ride command start execution");
        String URI;
        User user = (User) request.getSession().getAttribute("user");
        Ride ride;
        Mapper<Ride> rideMapper;
        SessionManagerService sessionManagerService = ImplSessionManagerService.getInstance();
        RideService rideService = ImplRideService.getInstance();
        SpecialService specialService = ImplSpecialService.getInstance();
        DriverService driverService = ImplDriverService.getInstance();


        if (user == null){
            LOGGER.debug("null user");
            request.setAttribute("error_message", "e.null.user");
            URI = "/WEB-INF/error.jsp";
        }else if(request.getSession().getAttribute("current_ride") == null){
            LOGGER.debug("start obtaining a ride");
            rideMapper = new ImplRideMapper();
            ride = rideMapper.parseFromRequest(request);
            ride.setUser(user);
            try{
                CarType wantedCarType = CarType.valueOf(request.getParameter("carType"));
                LOGGER.debug("setting driver");
                ride.setDriver(sessionManagerService.getNearestDriver(wantedCarType,ride.getAddressFrom()));
                LOGGER.debug("setting specials");
                ride.setSpecials(specialService.getApplicableSpecials(ride));
                LOGGER.debug("setting specials");
                ride.setCost(rideService.calculateCost(ride));
                LOGGER.debug("setting waiting time");
                request.getSession().setAttribute("time_to_wait", String.valueOf( driverService.getTimeToWait(ride.getDriver(), ride.getAddressFrom())));
                LOGGER.debug("setting the ride");
                request.getSession().setAttribute("current_ride", ride);
                LOGGER.debug("ride obtained successfully");
                URI = "/WEB-INF/user/confirm_ride.jsp"; //only user can order a ride
            }catch (NotFoundException e){
                request.setAttribute("get_ride_error", "e.no.driver");
                URI = "/WEB-INF/user/home.jsp";
            }catch (Exception e){
                request.setAttribute("error_message", "e.unknown"); //should catch more exceptions to tell user what`s wrong
                URI = "/WEB-INF/error.jsp";
            }

        }else {
            LOGGER.debug("continuing with unfinished ride order");
            URI = "/WEB-INF/user/confirm_ride.jsp";
        }
        return URI;
    }
}
