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

@WebServlet(name = "StartQuestServlet", value = "/start-quest")
public class StartQuestServlet extends ApplicationServlet {

    private static final Logger log = LogManager.getLogger(StartQuestServlet.class);

    private QuestService questService;

    @Override
    public void init() throws ServletException {
        super.init();

        questService = getApplicationContext().getService(QuestService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace("Starting quest ...");

        //TODO possible improvement: add second quest and allow user to select quest from dropdown menu
        final long questId = 1L;

        try {
            questService.startQuest(createRequestAdapter(request), createSessionAdapter(request), questId);
        } catch (ServiceException e) {
            log.error(e);
            throw new ServletException(e.getMessage());
        }

        ServletUtil.reqRespForward(request, response, JspNames.QUEST);
    }
}