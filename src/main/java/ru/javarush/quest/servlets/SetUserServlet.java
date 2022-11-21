package ru.javarush.quest.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.entities.User;
import ru.javarush.quest.services.UserService;
import ru.javarush.quest.services.locator.ServiceLocator;
import ru.javarush.quest.util.JspNames;
import ru.javarush.quest.util.SessionAttributesWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SetUserServlet", value = "/user")
public class SetUserServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(SetUserServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        userService = ServiceLocator.getService(UserService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("name");

        log.trace("Processing user: {} ...", username);

        if (username.isBlank())
            throw new ServletException("User name shouldn't be empty");

        User user = userService.find(username);

        // create user if not exists
        if (user == null) {
            user = new User(username);
            userService.save(user);
        }

        // clear possible old session and create new session
        request.getSession().invalidate();
        request.getSession(true);

        // save user object into session
        SessionAttributesWrapper sessionWrapper = new SessionAttributesWrapper(request);
        sessionWrapper.setUser(user);

        // redirect to page where to start quest
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JspNames.INDEX + ".jsp");
        requestDispatcher.forward(request, response);
    }
}