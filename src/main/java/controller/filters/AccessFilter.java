package controller.filters;

import exception.PermissionDeniedException;
import model.entities.User;
import model.entities.prop.Role;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 *
 */

public class AccessFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AccessFilter.class);
    private Map<Role,List> permissions = new HashMap<>();
    private List<String> commonCommands = new ArrayList<>();
    private List<String> userCommands = new ArrayList<>();
    private List<String> adminCommands = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Access filter initializing");

        commonCommands.add("login_do");
        commonCommands.add("logout");
        commonCommands.add("home");
        commonCommands.add("lang_ua");
        commonCommands.add("lang_en");
        commonCommands.add("index");
        commonCommands.add("register_do");
        commonCommands.add("viewSpecials");

        userCommands.addAll(commonCommands);
        userCommands.add("findRide");
        userCommands.add("cancellRide");
        userCommands.add("confirmRide");
        userCommands.add("rideHistory");

        adminCommands.addAll(userCommands);
        adminCommands.add("admin_register_user");
        adminCommands.add("admin_register_driver");
        adminCommands.add("viewAllUsers");
        adminCommands.add("viewAllDrivers");
        adminCommands.add("viewDriverDetails");
        adminCommands.add("viewUserDetails");

        permissions.put(Role.UNAUTHORIZED,commonCommands);
        permissions.put(Role.USER,userCommands);
        permissions.put(Role.ADMIN, adminCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Access Filter start");
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        User user = (User) Optional.ofNullable(req.getSession().getAttribute("user")).orElse(new User()) ;
        Role role = (Role) Optional.ofNullable(user.getRole()).orElse(Role.UNAUTHORIZED) ;

        String uri = req.getRequestURI();
        LOGGER.debug(uri);
        String command = uri.replaceAll(".*/action/", "");

        if(uri.contains("action")
                && ! permissions.get(role).contains(command)){
            LOGGER.error("ILLEGAL ACCESS ATTEMPT: "+command);
            throw new PermissionDeniedException("Wrong permissions access attempt!");
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
