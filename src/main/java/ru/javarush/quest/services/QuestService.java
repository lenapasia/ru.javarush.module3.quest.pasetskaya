package ru.javarush.quest.services;

import lombok.AllArgsConstructor;
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
import ru.javarush.quest.services.adapters.RequestAdapter;
import ru.javarush.quest.services.adapters.SessionAdapter;
import ru.javarush.quest.services.exceptions.ServiceException;
import ru.javarush.quest.services.util.RequestAttributes;
import ru.javarush.quest.services.util.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private Quest findQuest(long questId) throws ServiceException {
        Optional<Quest> quest = questRepository.findById(questId);
        if (quest.isEmpty())
            throw new ServiceException("Quest not found.");
        return quest.get();
    }

    private Question findQuestion(long questionId) throws ServiceException {
        return findQuestion(questionId, "Question not found.");
    }

    private Question findQuestion(long questionId, String questionNotFoundErrorMessage) throws ServiceException {
        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isEmpty())
            throw new ServiceException(questionNotFoundErrorMessage);
        return question.get();
    }

    private Answer findAnswer(long answerId) throws ServiceException {
        return findAnswer(answerId, "Answer not found.");
    }

    private Answer findAnswer(long answerId, String answerNotFoundErrorMessage) throws ServiceException {
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (answer.isEmpty())
            throw new ServiceException(answerNotFoundErrorMessage);
        return answer.get();
    }

    public void setAvailableQuest(RequestAdapter requestAdapter, long questId) throws ServiceException {
        final Quest quest = findQuest(questId);
        final RequestAttributes requestAttributes = new RequestAttributes(requestAdapter);
        requestAttributes.setAvailableQuest(new QuestDto(quest.getName(), quest.getDescription(), false));
    }

    public void startQuest(RequestAdapter requestAdapter, SessionAdapter sessionAdapter, long questId)
            throws ServiceException {

        final SessionAttributes sessionAttributes = new SessionAttributes(sessionAdapter);

        clearSession(sessionAttributes);

        final Quest quest = findQuest(questId);

        applyNextQuestion(new ApplyNextQuestionParams(requestAdapter, sessionAdapter, quest, quest.getFirstQuestionId(),
                "Malformed quest: first question not found",
                "Malformed quest: answers for first question not found"));

        sessionAttributes.setQuestId(questId);

        log.info("Quest [id='{}'] has been started", questId);
    }

    private void clearSession(SessionAttributes sessionAttributes) {
        sessionAttributes.setQuestionId(null);
        sessionAttributes.setQuestId(null);
    }

    public void applyAnswer(RequestAdapter requestAdapter, SessionAdapter sessionAdapter, long answerId)
            throws ServiceException {

        final SessionAttributes sessionAttributes = new SessionAttributes(sessionAdapter);

        final Long questId = sessionAttributes.getQuestId();
        if (questId == null)
            throw new ServiceException("Has no active quest.");

        final Long questionId = sessionAttributes.getQuestionId();
        if (questionId == null)
            throw new ServiceException("Has no active question.");

        final Quest quest = findQuest(questId);

        final Question question = findQuestion(questionId);

        if (!question.hasAnswer(answerId))
            throw new ServiceException("Answer doesn't belong to question.");

        final Answer answer = findAnswer(answerId);

        final boolean completed = applyNextQuestion(new ApplyNextQuestionParams(
                requestAdapter, sessionAdapter, quest, answer.getNextQuestionId(),
                "Next question not found.",
                "Next answer not found."));

        if (!completed) {
            log.debug("Applied answer [id='{}'] to question [id='{}']: next question id = '{}'",
                    answerId, questionId, answer.getNextQuestionId());
        }
    }

    private boolean applyNextQuestion(ApplyNextQuestionParams params) throws ServiceException {
        final Question question = findQuestion(params.questionId, params.questionNotFoundErrorMessage);

        final SessionAttributes sessionAttributes = new SessionAttributes(params.sessionAdapter);

        boolean completed = false;
        List<Answer> answers = new ArrayList<>();

        if (question.getElementType() == QuestElementType.QUESTION) {
            answers = findAnswers(question.getAnswerIds(), params.answerNotFoundErrorMessage);
        } else {
            completed = true;

            incrementPlayedGamesCount(sessionAttributes);

            log.info("Quest [id='{}'] has been finished: {}.", params.quest.getId(), question.getElementType());
        }

        final RequestAttributes requestAttributes = new RequestAttributes(params.requestAdapter);
        requestAttributes.setQuestion(createQuestionPayload(question, answers));
        requestAttributes.setQuest(new QuestDto(params.quest.getName(), params.quest.getDescription(), completed));

        sessionAttributes.setQuestionId(params.questionId);

        return completed;
    }

    private List<Answer> findAnswers(List<Long> answerIds, String answerNotFoundErrorMessage) throws ServiceException {
        final List<Answer> answers = new ArrayList<>();
        for (Long answerId : answerIds) {
            final Answer answer = findAnswer(answerId, answerNotFoundErrorMessage);
            answers.add(answer);
        }
        return answers;
    }

    private void incrementPlayedGamesCount(SessionAttributes sessionAttributes) {
        final User user = sessionAttributes.getUser();
        user.incrementPlayedGamesCount();
        sessionAttributes.setUser(user);

        userRepository.save(user);
    }

    private QuestionDto createQuestionPayload(Question question, List<Answer> answers) {
        List<AnswerDto> answerPayloads = new ArrayList<>();
        for (Answer eachAnswer : answers) {
            AnswerDto answerDto = new AnswerDto(eachAnswer.getId(), eachAnswer.getText());
            answerPayloads.add(answerDto);
        }

        return new QuestionDto(question.getId(), question.getText(), answerPayloads);
    }

    @AllArgsConstructor
    private final class ApplyNextQuestionParams {
        private final RequestAdapter requestAdapter;
        private final SessionAdapter sessionAdapter;
        private final Quest quest;
        private final long questionId;
        private final String questionNotFoundErrorMessage;
        private final String answerNotFoundErrorMessage;
    }
}