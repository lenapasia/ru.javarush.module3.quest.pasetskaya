package ru.javarush.quest.context;

import ru.javarush.quest.defaults.DefaultQuestsBuilder;
import ru.javarush.quest.entities.Answer;
import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.entities.Question;
import ru.javarush.quest.repositories.AnswerRepository;
import ru.javarush.quest.repositories.QuestRepository;
import ru.javarush.quest.repositories.QuestionRepository;
import ru.javarush.quest.repositories.UserRepository;
import ru.javarush.quest.repositories.impl.AnswerRepositoryImpl;
import ru.javarush.quest.repositories.impl.QuestRepositoryImpl;
import ru.javarush.quest.repositories.impl.QuestionRepositoryImpl;
import ru.javarush.quest.repositories.impl.UserRepositoryImpl;
import ru.javarush.quest.services.QuestService;
import ru.javarush.quest.services.UserService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

class ApplicationContextInitializer {

    private AnswerRepository answerRepository;
    private QuestRepository questRepository;
    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    public void init() {
        initRepositories();
    }

    private void initRepositories() {
        userRepository = new UserRepositoryImpl(new HashMap<>());

        initRepositoriesWithDefaults();
    }

    private void initRepositoriesWithDefaults() {
        DefaultQuestsBuilder builder = new DefaultQuestsBuilder();
        builder.build();

        Map<Long, Quest> idToQuest = builder.getIdToQuest();
        Map<Long, Question> idToQuestion = builder.getIdToQuestion();
        Map<Long, Answer> idToAnswer = builder.getIdToAnswer();

        answerRepository = new AnswerRepositoryImpl(idToAnswer);
        questionRepository = new QuestionRepositoryImpl(idToQuestion);
        questRepository = new QuestRepositoryImpl(idToQuest);
    }

    public Object lookup(Type serviceType) {
        if (serviceType == QuestService.class) {
            return new QuestService(answerRepository, questionRepository,
                    questRepository, userRepository);
        }

        if (serviceType == UserService.class) {
            return new UserService(userRepository);
        }

        throw new IllegalArgumentException(String.format(
                "Unregistered service with class type '%s'.", serviceType));
    }
}