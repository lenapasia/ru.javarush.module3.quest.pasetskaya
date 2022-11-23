package ru.javarush.quest.repositories.impl;

import ru.javarush.quest.repositories.CrudRepository;

import java.util.Map;
import java.util.Optional;

public abstract class AbstractCrudRepository<E> implements CrudRepository<E> {

    protected final Map<Long, E> idToEntity;

    protected AbstractCrudRepository(Map<Long, E> idToEntity) {
        this.idToEntity = idToEntity;
    }

    @Override
    public Optional<E> findById(Long id) {
        return Optional.ofNullable(idToEntity.get(id));
    }
}