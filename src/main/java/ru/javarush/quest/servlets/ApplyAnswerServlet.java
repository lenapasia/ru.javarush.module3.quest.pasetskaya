package ru.javarush.quest.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.entities.Answer;
import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.entities.Question;
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

@WebServlet(name = "ApplyAnswerServlet", value = "/apply-answer")
public class ApplyAnswerServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ApplyAnswerServlet.class);

    private QuestService questService;

    @Override
    public void init() throws ServletException {
        super.init();

        questService = ServiceLocator.getService(QuestService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace("Processing answer ...");

        final Long answerId = ServletUtil.getId(request);
        if (answerId == null)
            throw new ServletException("Parameter 'id' is missing.");

        SessionAttributesWrapper sessionWrapper = new SessionAttributesWrapper(request);
        final Long questionId = sessionWrapper.getQuestionId();

        if (questionId == null)
            throw new ServletException("Has no active question.");

        final Quest quest = questService.findQuest(sessionWrapper.getQuestId());

        applyAnswer(request, quest, questionId, answerId);

        ServletUtil.reqRespForward(request, response, JspNames.QUEST);
    }

    private void applyAnswer(HttpServletRequest request, Quest quest, long questionId, long answerId) throws ServletException {
        Question question = questService.findQuestion(questionId);

        if (question == null)
            throw new ServletException("Question doesn't exist.");

        // validate that answer belongs to question
        if (!question.hasAnswer(answerId))
            throw new ServletException("Answer doesn't belong to question.");

        Answer answer = questService.findAnswer(answerId);

        if (answer == null)
            throw new ServletException("Answer not found.");

        questService.applyNextQuestion(request, quest, answer.getNextQuestionId(),
                "Next question not found.",
                "Next answer not found.");

        log.debug("Applied answer [id='{}'] to question [id='{}']: next question id = '{}'",
                answerId, questionId, answer.getNextQuestionId());
    }
}
