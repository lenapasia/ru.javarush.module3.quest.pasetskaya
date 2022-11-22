package ru.javarush.quest.servlets.context;

import ru.javarush.quest.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static ru.javarush.quest.servlets.context.ServletContextAttributes.APP_CONTEXT;

@WebListener
public class ApplicationServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext appContext = new ApplicationContext();

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(APP_CONTEXT, appContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(APP_CONTEXT);
    }
}