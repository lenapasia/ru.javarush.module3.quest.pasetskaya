package ru.javarush.quest.servlets;

import ru.javarush.quest.context.ApplicationContext;
import ru.javarush.quest.services.adapters.RequestAdapter;
import ru.javarush.quest.servlets.adapters.ServletRequestAdapter;
import ru.javarush.quest.servlets.adapters.ServletSessionAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import static ru.javarush.quest.servlets.context.ServletContextAttributes.APP_CONTEXT;

public abstract class ApplicationServlet extends HttpServlet {
    private static final long DEFAULT_QUEST_ID = 1;

    private ApplicationContext applicationContext;

    protected ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void init() throws ServletException {
        super.init();

        applicationContext = (ApplicationContext) getServletContext().getAttribute(APP_CONTEXT);
    }

    protected RequestAdapter createRequestAdapter(HttpServletRequest request) {
        return new ServletRequestAdapter(request);
    }

    protected ServletSessionAdapter createSessionAdapter(HttpServletRequest request) {
        return new ServletSessionAdapter(request);
    }

    protected long getQuestId() {
        return DEFAULT_QUEST_ID;
    }
}
