package ru.javarush.quest.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.services.QuestService;
import ru.javarush.quest.services.locator.ServiceLocator;
import ru.javarush.quest.util.JspNames;
import ru.javarush.quest.util.ServletUtil;
import ru.javarush.quest.util.SessionAttributesWrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StartQuestServlet", value = "/start-quest")
public class StartQuestServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(StartQuestServlet.class);

    private QuestService questService;

    @Override
    public void init() throws ServletException {
        super.init();

        questService = ServiceLocator.getService(QuestService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace("Starting quest ...");

        SessionAttributesWrapper sessionWrapper = new SessionAttributesWrapper(request);
        sessionWrapper.setQuestionId(null);
        sessionWrapper.setQuestId(null);

        //TODO possible improvement: add second quest and allow user to select quest from dropdown menu
        final long questId = 1L;

        final Quest quest = questService.findQuest(questId);
        if (quest == null)
            throw new ServletException("Quest not found");

        questService.applyNextQuestion(request, quest, quest.getFirstQuestionId(),
                "Malformed quest: first question not found",
                "Malformed quest: answers for first question not found");

        sessionWrapper.setQuestId(questId);

        log.info("Quest [id='{}'] has been started", questId);

        ServletUtil.reqRespForward(request, response, JspNames.QUEST);
    }
}