package ru.javarush.quest.repositories;

import ru.javarush.quest.entities.Question;

import java.util.Optional;

public interface QuestionRepository {

    Optional<Question> findById(Long id);
}
