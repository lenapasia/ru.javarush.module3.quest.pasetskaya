package ru.javarush.quest.repositories;

import ru.javarush.quest.entities.Answer;

import java.util.Optional;

public interface AnswerRepository {
    Optional<Answer> findById(Long id);
}
