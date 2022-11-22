package ru.javarush.quest.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.services.QuestService;
import ru.javarush.quest.services.exceptions.ServiceException;
import ru.javarush.quest.util.JspNames;
import ru.javarush.quest.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplyAnswerServlet", value = "/apply-answer")
public class ApplyAnswerServlet extends ApplicationServlet {

    private static final Logger log = LogManager.getLogger(ApplyAnswerServlet.class);

    private QuestService questService;

    @Override
    public void init() throws ServletException {
        super.init();

        questService = getApplicationContext().getService(QuestService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace("Processing answer ...");

        final Long answerId = ServletUtil.getId(request);
        if (answerId == null)
            throw new ServletException("Parameter 'id' is missing.");

        try {
            questService.applyAnswer(createRequestAdapter(request), createSessionAdapter(request), answerId);
        } catch (ServiceException e) {
            log.error(e);
            throw new ServletException(e.getMessage());
        }
        ServletUtil.reqRespForward(request, response, JspNames.QUEST);
    }
}
