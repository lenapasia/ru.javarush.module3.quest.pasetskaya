package ru.javarush.quest.repositories.locator;

import lombok.Getter;
import ru.javarush.quest.entities.Answer;
import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.entities.Question;
import ru.javarush.quest.repositories.*;
import ru.javarush.quest.repositories.locator.quest.DefaultQuestsBuilder;

import java.util.Map;

public final class Repositories {

    private static volatile Repositories instance;

    public static Repositories get() {
        if (instance != null)
            return instance;

        synchronized (Repositories.class) {
            if (instance == null)
                instance = new Repositories();

            instance.init();

            return instance;
        }
    }

    @Getter
    private AnswerRepository answer;

    @Getter
    private QuestRepository quest;

    @Getter
    private QuestionRepository question;

    @Getter
    private UserRepository user;

    private Repositories() {
        init();
    }

    private void init() {
        user = new UserRepositoryImpl();

        //TODO load from json, if not exists then generate from defaults
        initWithDefaults();
    }

    private void initWithDefaults() {
        DefaultQuestsBuilder builder = new DefaultQuestsBuilder();
        builder.build();

        Map<Long, Quest> idToQuest = builder.getIdToQuest();
        Map<Long, Question> idToQuestion = builder.getIdToQuestion();
        Map<Long, Answer> idToAnswer = builder.getIdToAnswer();

        answer = new AnswerRepositoryImpl(idToAnswer);
        question = new QuestionRepositoryImpl(idToQuestion);
        quest = new QuestRepositoryImpl(idToQuest);
    }
}