package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Дмитрий on 20.07.2016.
 */
public class CalculCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        Integer num1 = Integer.valueOf(request.getParameter("num1"));
        Integer num2 = Integer.valueOf(request.getParameter("num2"));
        Integer summ = num1 + num2;
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        out.println(summ);
        out.write(summ.toString() /*+ "\n" + "govno"*/);
        out.flush();
        out.close();
        return null;
    }
}
