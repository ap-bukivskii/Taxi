package controller.command;

import controller.command.impl.admin.*;
import controller.command.impl.common.*;
import controller.command.impl.user.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);
    private static volatile CommandFactory instance;
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        //common commands
        commands.put("login_do", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("home", new RedirectHome());
        commands.put("lang_ua", new ChangeLangUa());
        commands.put("lang_en", new ChangeLandEn());
        commands.put("index", new RedirectIndex());
        commands.put("register_do", new RegisterCommand());
        commands.put("viewSpecials", new ViewSpecials());

        //user commands
        commands.put("findRide", new FindRideCommand());
        commands.put("cancellRide", new CancelRideCommand());
        commands.put("confirmRide", new ConfirmRideCommand());
        commands.put("rideHistory", new RideHistoryCommand());

        //admin commands
        commands.put("admin_register_user", new AdminRegisterUser());
        commands.put("admin_register_driver", new AdminRegisterDriver());
        commands.put("viewAllUsers", new ViewUsersCommand());
        commands.put("viewAllDrivers", new ViewDriversCommand());
        commands.put("viewDriverDetails", new ViewDriverDetails());
        commands.put("viewUserDetails", new ViewUserDetails());

    }

    public static CommandFactory getInstance(){
        if(instance == null){
            synchronized (CommandFactory.class){
                if(instance == null){
                    instance = new CommandFactory();
                }
            }
        }
        return instance;
    }

    public Command getCommand(String commandName){
        return commands.getOrDefault(commandName,new RedirectHome());
    }
}
