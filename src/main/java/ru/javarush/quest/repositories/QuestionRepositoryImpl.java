package ru.javarush.quest.repositories;

import ru.javarush.quest.entities.Question;

import java.util.Map;
import java.util.Optional;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final Map<Long, Question> idToQuestion;

    public QuestionRepositoryImpl(Map<Long, Question> idToQuestion) {
        this.idToQuestion = idToQuestion;
    }

    @Override
    public Optional<Question> findById(Long id) {
        return Optional.ofNullable(idToQuestion.get(id));
    }
}