package ru.javarush.quest.repositories.impl;

import ru.javarush.quest.entities.Answer;
import ru.javarush.quest.repositories.AnswerRepository;

import java.util.Map;

class AnswerRepositoryImplTest extends CrudRepositoryTest<Answer, AnswerRepository> {
    @Override
    protected Answer createEntity(long id) {
        return new Answer(id, "Answer1", 14);
    }

    @Override
    protected AnswerRepository createRepository(Map<Long, Answer> idToEntity) {
        return new AnswerRepositoryImpl(idToEntity);
    }
}