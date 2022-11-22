package ru.javarush.quest.repositories.impl;

import ru.javarush.quest.entities.Answer;
import ru.javarush.quest.repositories.AnswerRepository;

import java.util.Map;

public class AnswerRepositoryImpl extends AbstractCrudRepository<Answer> implements AnswerRepository {

    public AnswerRepositoryImpl(Map<Long, Answer> idToEntity) {
        super(idToEntity);
    }
}
