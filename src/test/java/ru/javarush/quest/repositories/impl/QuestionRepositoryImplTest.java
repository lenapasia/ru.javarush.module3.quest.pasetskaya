package ru.javarush.quest.repositories.impl;

import ru.javarush.quest.entities.QuestElementType;
import ru.javarush.quest.entities.Question;
import ru.javarush.quest.repositories.QuestionRepository;

import java.util.Map;

class QuestionRepositoryImplTest extends CrudRepositoryTest<Question, QuestionRepository> {
    @Override
    protected Question createEntity(long id) {
        return new Question(id, QuestElementType.QUESTION, "Question1", null);
    }

    @Override
    protected QuestionRepository createRepository(Map<Long, Question> idToEntity) {
        return new QuestionRepositoryImpl(idToEntity);
    }
}