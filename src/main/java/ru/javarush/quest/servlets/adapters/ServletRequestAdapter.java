package ru.javarush.quest.servlets.adapters;

import ru.javarush.quest.services.adapters.RequestAdapter;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestAdapter implements RequestAdapter {
    private final HttpServletRequest request;

    public ServletRequestAdapter(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }
}
