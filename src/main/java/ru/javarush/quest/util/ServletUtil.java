package ru.javarush.quest.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Formatter;

public final class ServletUtil {

    private ServletUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void reqRespForward(HttpServletRequest request, HttpServletResponse response, String jspName)
            throws ServletException, IOException {
        String path = new Formatter().format("WEB-INF/%s.jsp", jspName).toString();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    public static Long getId(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        boolean isNumeric = id.chars().allMatch(Character::isDigit);
        return isNumeric ? Long.parseLong(id) : null;
    }

}
