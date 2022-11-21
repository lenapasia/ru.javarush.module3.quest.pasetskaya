package ru.javarush.quest.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.dtos.AnswerDto;
import ru.javarush.quest.dtos.QuestDto;
import ru.javarush.quest.dtos.QuestionDto;
import ru.javarush.quest.entities.*;
import ru.javarush.quest.repositories.AnswerRepository;
import ru.javarush.quest.repositories.QuestRepository;
import ru.javarush.quest.repositories.QuestionRepository;
import ru.javarush.quest.repositories.UserRepository;
import ru.javarush.quest.util.SessionAttributesWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class QuestService {

    private static final Logger log = LogManager.getLogger(QuestService.class);

    public QuestService(AnswerRepository answerRepository, QuestionRepository questionRepository,
                        QuestRepository questRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.questRepository = questRepository;
        this.userRepository = userRepository;
    }

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestRepository questRepository;

    private final UserRepository userRepository;

    public Quest findQuest(long questId) {
        return questRepository.findById(questId).orElse(null);
    }

    public Question findQuestion(long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    public Answer findAnswer(long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }

    public void applyNextQuestion(HttpServletRequest request, Quest quest, long questionId,
                                              String questionNotFoundErrorMessage,
                                              String answerNotFoundErrorMessage) throws ServletException {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question == null)
            throw new ServletException(questionNotFoundErrorMessage);

        List<Answer> answers = new ArrayList<>();

        SessionAttributesWrapper sessionWrapper = new SessionAttributesWrapper(request);

        boolean completed = false;
        if (question.getElementType() == QuestElementType.QUESTION) {
            for (Long answerId : question.getAnswerIds()) {
                Answer answer = answerRepository.findById(answerId).orElse(null);

                if (answer == null)
                    throw new ServletException(answerNotFoundErrorMessage);

                answers.add(answer);
            }
        } else {
            completed = true;

            final User user = sessionWrapper.getUser();
            user.incrementPlayedGamesCount();
            sessionWrapper.setUser(user);

            userRepository.save(user);

            log.info("Quest [id='{}'] has been finished: {}.", quest.getId(), question.getElementType());
        }

        QuestionDto questionPayload = createQuestionPayload(question, answers);
        request.setAttribute("question", questionPayload);

        request.setAttribute("quest", new QuestDto(quest.getName(), completed));

        sessionWrapper.setQuestionId(questionId);
    }

    private QuestionDto createQuestionPayload(Question question, List<Answer> answers) {
        List<AnswerDto> answerPayloads = new ArrayList<>();
        for (Answer eachAnswer : answers) {
            AnswerDto answerDto = new AnswerDto(eachAnswer.getId(), eachAnswer.getText());
            answerPayloads.add(answerDto);
        }

        return new QuestionDto(question.getId(), question.getText(), answerPayloads);
    }
}