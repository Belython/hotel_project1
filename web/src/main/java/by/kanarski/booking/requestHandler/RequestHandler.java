package by.kanarski.booking.requestHandler;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.commands.factory.CommandFactory;
import by.kanarski.booking.constants.Parameter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestHandler {
    private RequestHandler() {
    }

    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        ICommand сommand = commandFactory.defineCommand(request);
        ServletAction servletAction = сommand.execute(request, response);
        String page = servletAction.getPage();
        try {
            switch (servletAction) {
                case FORWARD_PAGE: {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                    break;
                }
                case REDIRECT_PAGE: {
                    response.sendRedirect(request.getContextPath() + page);
                    break;
                }
                case AJAX_REQUEST: {
                    if (page != null) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                        dispatcher.forward(request, response);
                    }
                    break;
                }
                case CALL_COMMAND: {
                    String newCommandName = servletAction.getCommandName().toLowerCase();
                    request.setAttribute(Parameter.COMMAND, newCommandName);
                    processRequest(request, response);
                    break;
                }
            }
        } catch (IOException e) {
            // TODO: 21.07.2016 запилить
            e.printStackTrace();
        }

    }
}
