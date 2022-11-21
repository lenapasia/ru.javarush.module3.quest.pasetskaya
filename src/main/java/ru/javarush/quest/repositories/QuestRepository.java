package ru.javarush.quest.repositories;

import ru.javarush.quest.entities.Quest;

import java.util.Optional;

public interface QuestRepository {
    Optional<Quest> findById(Long id);
}
