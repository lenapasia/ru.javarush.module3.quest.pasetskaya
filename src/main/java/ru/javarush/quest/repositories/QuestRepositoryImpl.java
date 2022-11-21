package ru.javarush.quest.repositories;

import ru.javarush.quest.entities.Quest;

import java.util.Map;
import java.util.Optional;

public class QuestRepositoryImpl implements QuestRepository {

    private final Map<Long, Quest> idToQuest;

    public QuestRepositoryImpl(Map<Long, Quest> idToQuest) {
        this.idToQuest = idToQuest;
    }
    @Override
    public Optional<Quest> findById(Long id) {
        return Optional.ofNullable(idToQuest.get(id));
    }
}
