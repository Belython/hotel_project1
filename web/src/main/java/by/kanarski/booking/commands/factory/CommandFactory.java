package by.kanarski.booking.commands.factory;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.commands.impl.user.LoginUserCommand;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static CommandFactory instance;

    private CommandFactory() {
    }

    public static synchronized CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public ICommand defineCommand(HttpServletRequest request) {
        ICommand current = null;
        try {
            CommandType type = RequestParser.parseCommandType(request);
            current = type.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = new LoginUserCommand();
        }
        return current;
    }
}
