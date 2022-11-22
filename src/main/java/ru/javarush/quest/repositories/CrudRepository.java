package ru.javarush.quest.repositories;

import java.util.Optional;

public interface CrudRepository<E> {
    Optional<E> findById(Long id);
}