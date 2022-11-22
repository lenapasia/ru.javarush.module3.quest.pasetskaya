package ru.javarush.quest.servlets.adapters;

import ru.javarush.quest.services.adapters.SessionAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletSessionAdapter implements SessionAdapter {
    private final HttpSession session;

    public ServletSessionAdapter(HttpSession session) {
        this.session = session;
    }

    public ServletSessionAdapter(HttpServletRequest request) {
        this(request.getSession());
    }

    @Override
    public Object getAttribute(String name) {
        return session.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        session.setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        session.removeAttribute(name);
    }
}
