package by.kanarski.booking.listeners;

import by.kanarski.booking.constants.Attribute;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.i18n.l10n.filler.Filler;
import by.kanarski.booking.i18n.l10n.filler.FillerFactory;


import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Используется для заполнения контентом jsp-страниц при изменении
 * локали, находящейся в сессии
 * */

public class LocaleListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        someBlock(servletRequestAttributeEvent);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        someBlock(servletRequestAttributeEvent);
    }

    private void someBlock(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String attributeName = servletRequestAttributeEvent.getName();
        HttpServletRequest request = (HttpServletRequest) servletRequestAttributeEvent.getServletRequest();
        switch (attributeName) {
            case Parameter.CURRENT_PAGE_PATH: {
                String pagePath = (String) servletRequestAttributeEvent.getValue();
                Filler filler = FillerFactory.getInstance().defineFiller(pagePath);
                filler.fill(request);
            }
        }
    }
}
