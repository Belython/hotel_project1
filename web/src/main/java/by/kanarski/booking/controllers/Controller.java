/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.kanarski.booking.controllers;

import by.kanarski.booking.constants.Value;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.requestHandler.RequestHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author khudnitsky
 * @version 1.0
 */
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestHandler.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestHandler.processRequest(request, response);
    }

}
