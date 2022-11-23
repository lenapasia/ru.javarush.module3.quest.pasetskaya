package ru.javarush.quest.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.javarush.quest.entities.Answer;
import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.entities.QuestElementType;
import ru.javarush.quest.entities.Question;
import ru.javarush.quest.repositories.AnswerRepository;
import ru.javarush.quest.repositories.QuestRepository;
import ru.javarush.quest.repositories.QuestionRepository;
import ru.javarush.quest.repositories.UserRepository;
import ru.javarush.quest.services.adapters.RequestAdapter;
import ru.javarush.quest.services.adapters.SessionAdapter;
import ru.javarush.quest.services.exceptions.ServiceException;
import ru.javarush.quest.services.util.SessionAttributes;

import java.util.Arrays;
import java.util.Optional;

class QuestServiceTest {

    @Test
    void setAvailableQuest() throws ServiceException {
        final long questId = 1;
        final Quest quest = new Quest(questId, "Quest1", "Description1", 1);

        final QuestRepository questRepository = Mockito.mock(QuestRepository.class);
        Mockito.doReturn(Optional.of(quest)).when(questRepository).findById(questId);

        final QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
        final AnswerRepository answerRepository = Mockito.mock(AnswerRepository.class);
        final UserRepository userRepository = Mockito.mock(UserRepository.class);

        final QuestService questService = new QuestService(answerRepository, questionRepository,
                questRepository, userRepository);

        final RequestAdapter requestAdapter = Mockito.mock(RequestAdapter.class);

        questService.setAvailableQuest(requestAdapter, questId);
        Mockito.verify(requestAdapter).setAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    void startQuest() throws ServiceException {
        final long questId = 1;
        final long firstQuestionId = 100;
        final long firstAnswerId = 58;

        final Quest quest = new Quest(questId, "Quest1", "Description1", firstQuestionId);

        final Question firstQuestion = new Question(firstQuestionId, QuestElementType.QUESTION, "Question1",
                Arrays.asList(firstAnswerId));

        final Answer firstAnswer = new Answer(firstAnswerId, "Answer1", 31);

        final QuestRepository questRepository = Mockito.mock(QuestRepository.class);
        Mockito.doReturn(Optional.of(quest)).when(questRepository).findById(questId);

        final QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
        Mockito.doReturn(Optional.of(firstQuestion)).when(questionRepository).findById(firstQuestionId);

        final AnswerRepository answerRepository = Mockito.mock(AnswerRepository.class);
        Mockito.doReturn(Optional.of(firstAnswer)).when(answerRepository).findById(firstAnswerId);

        final UserRepository userRepository = Mockito.mock(UserRepository.class);

        final QuestService questService = new QuestService(answerRepository, questionRepository,
                questRepository, userRepository);

        final RequestAdapter requestAdapter = Mockito.mock(RequestAdapter.class);
        final SessionAdapter sessionAdapter = Mockito.mock(SessionAdapter.class);

        questService.startQuest(requestAdapter, sessionAdapter, questId);

        Mockito.verify(requestAdapter, Mockito.atLeastOnce()).setAttribute(Mockito.anyString(), Mockito.any());
        Mockito.verify(sessionAdapter, Mockito.atLeastOnce()).setAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    void applyAnswer() throws ServiceException {
        final long questId = 1;
        final long firstQuestionId = 100;
        final long secondQuestionId = 105;
        final long firstAnswerId = 58;
        final long secondAnswerId = 60;

        final Quest quest = new Quest(questId, "Quest1", "Description1", firstQuestionId);

        final Question firstQuestion = new Question(firstQuestionId, QuestElementType.QUESTION, "Question1",
                Arrays.asList(firstAnswerId));

        final Answer firstAnswer = new Answer(firstAnswerId, "Answer1", secondQuestionId);

        final Question secondQuestion = new Question(secondQuestionId, QuestElementType.QUESTION, "Question2",
                Arrays.asList(secondAnswerId));

        final Answer secondAnswer = new Answer(secondAnswerId, "Answer2", 67);

        final QuestRepository questRepository = Mockito.mock(QuestRepository.class);
        Mockito.doReturn(Optional.of(quest)).when(questRepository).findById(questId);

        final QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
        Mockito.doReturn(Optional.of(firstQuestion)).when(questionRepository).findById(firstQuestionId);
        Mockito.doReturn(Optional.of(secondQuestion)).when(questionRepository).findById(secondQuestionId);

        final AnswerRepository answerRepository = Mockito.mock(AnswerRepository.class);
        Mockito.doReturn(Optional.of(firstAnswer)).when(answerRepository).findById(firstAnswerId);
        Mockito.doReturn(Optional.of(secondAnswer)).when(answerRepository).findById(secondAnswerId);

        final UserRepository userRepository = Mockito.mock(UserRepository.class);

        final QuestService questService = new QuestService(answerRepository, questionRepository,
                questRepository, userRepository);

        final RequestAdapter requestAdapter = Mockito.mock(RequestAdapter.class);

        final SessionAdapter sessionAdapter = Mockito.mock(SessionAdapter.class);
        Mockito.doReturn(questId).when(sessionAdapter).getAttribute(SessionAttributes.QUEST_ID);
        Mockito.doReturn(firstQuestionId).when(sessionAdapter).getAttribute(SessionAttributes.QUESTION_ID);

        questService.applyAnswer(requestAdapter, sessionAdapter, firstAnswerId);

        Mockito.verify(requestAdapter, Mockito.atLeastOnce()).setAttribute(Mockito.anyString(), Mockito.any());
        Mockito.verify(sessionAdapter, Mockito.atLeastOnce()).setAttribute(Mockito.anyString(), Mockito.any());
    }
}