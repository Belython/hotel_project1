package by.kanarski.booking.listeners.request;

import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ThreadLocalListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ThreadLocalUtil.destroy();
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
