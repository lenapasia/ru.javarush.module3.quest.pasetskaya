package ru.javarush.quest.repositories.impl;

import org.junit.jupiter.api.Test;
import ru.javarush.quest.entities.Answer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCrudRepositoryTest {

    @Test
    void findById() {
        final long id = 1;
        final Answer expected = new Answer(id, "answer 1", 10);

        final Map<Long, Answer> idToEntity = new HashMap<>();
        idToEntity.put(id, expected);

        final AbstractCrudRepository<Answer> repository = new AnswerRepositoryImpl(idToEntity);
        final Optional<Answer> actual = repository.findById(id);

        assertSame(expected, actual.orElseGet(null));
    }
}