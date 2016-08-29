package by.kanarski.booking.commands;

import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    ServletAction execute(HttpServletRequest request, HttpServletResponse response);
}
