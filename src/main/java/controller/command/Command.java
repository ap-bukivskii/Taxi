package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *The main interface to compound particular commands.
 *Realization of the command pattern
 */
public interface Command {
    /**
     * Executes command logic.
     * @param request HttpServletRequest that should be processed
     * @param response HttpServletResponse server response to request
     * @return path to the appropriate page
     **/
    String execute(HttpServletRequest request, HttpServletResponse response);
}
