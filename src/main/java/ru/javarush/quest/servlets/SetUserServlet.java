package ru.javarush.quest.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.entities.User;
import ru.javarush.quest.services.UserService;
import ru.javarush.quest.services.util.SessionAttributes;
import ru.javarush.quest.util.JspNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SetUserServlet", value = "/user")
public class SetUserServlet extends ApplicationServlet {

    private static final Logger log = LogManager.getLogger(SetUserServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        userService = getApplicationContext().getService(UserService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = getUsername(request);

        log.trace("Processing user: {} ...", username);

        final User user = userService.findOrCreateUser(username);

        restartSession(request);

        saveInSession(request, user);

        redirectToStartQuestPage(request, response);
    }

    private String getUsername(HttpServletRequest request) throws ServletException {
        final String username = request.getParameter("name");

        if (username.isBlank())
            throw new ServletException("User name shouldn't be empty");

        return username;
    }

    private void restartSession(HttpServletRequest request) {
        request.getSession().invalidate();
        request.getSession(true);
    }

    private void saveInSession(HttpServletRequest request, User user) {
        SessionAttributes sessionAttributes = new SessionAttributes(createSessionAdapter(request));
        sessionAttributes.setUser(user);
    }

    private void redirectToStartQuestPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JspNames.INDEX + ".jsp");
        requestDispatcher.forward(request, response);
    }
}