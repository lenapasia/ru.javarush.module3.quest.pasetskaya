package ru.javarush.quest.repositories.impl;

import org.junit.jupiter.api.Test;
import ru.javarush.quest.repositories.CrudRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;

abstract class CrudRepositoryTest<E, R extends CrudRepository<E>> {

    protected abstract E createEntity(long id);

    protected abstract R createRepository(Map<Long, E> idToEntity);

    @Test
    public void findById() {
        final long id = 1;
        final E expected = createEntity(id);

        final Map<Long, E> idToEntity = new HashMap<>();
        idToEntity.put(id, expected);

        final CrudRepository<E> repository = createRepository(idToEntity);
        final Optional<E> actual = repository.findById(id);

        assertSame(expected, actual.orElseGet(null));
    }
}
