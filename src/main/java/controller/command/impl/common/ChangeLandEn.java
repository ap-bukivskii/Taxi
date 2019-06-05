package controller.command.impl.common;

import controller.command.Command;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
public class ChangeLandEn implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ChangeLandEn.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String referer;
        LOGGER.info("Change lang to english command");
        request.getSession().setAttribute("app_lang", "en");
        referer = (String) Optional.ofNullable( request.getHeader("referer")).orElse("/action/home");
//        try {
//            referer = new URI(request.getHeader("referer")).getPath().replace(request.getContextPath(),"");
//        }catch (URISyntaxException e){
//            referer = "/action/home";
//            LOGGER.error("Bad referer url. returning to HOME"+e);
//        }
        LOGGER.debug(referer);
        return "redirect:" + referer;
    }
}
