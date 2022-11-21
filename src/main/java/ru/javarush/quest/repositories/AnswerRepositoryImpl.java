package ru.javarush.quest.repositories;

import ru.javarush.quest.entities.Answer;

import java.util.Map;
import java.util.Optional;

public class AnswerRepositoryImpl implements AnswerRepository {

    private final Map<Long, Answer> idToAnswer;

    public AnswerRepositoryImpl(Map<Long, Answer> idToAnswer) {
        this.idToAnswer = idToAnswer;
    }
    @Override
    public Optional<Answer> findById(Long id) {
        return Optional.ofNullable(idToAnswer.get(id));
    }
}
