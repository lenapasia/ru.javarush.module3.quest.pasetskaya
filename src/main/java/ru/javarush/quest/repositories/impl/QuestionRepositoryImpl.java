package ru.javarush.quest.repositories.impl;

import ru.javarush.quest.entities.Question;
import ru.javarush.quest.repositories.QuestionRepository;

import java.util.Map;

public class QuestionRepositoryImpl extends AbstractCrudRepository<Question> implements QuestionRepository {

    public QuestionRepositoryImpl(Map<Long, Question> idToEntity) {
        super(idToEntity);
    }
}