package ru.javarush.quest.repositories.impl;

import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.repositories.QuestRepository;

import java.util.Map;

public class QuestRepositoryImpl extends AbstractCrudRepository<Quest> implements QuestRepository {

    public QuestRepositoryImpl(Map<Long, Quest> idToEntity) {
        super(idToEntity);
    }
}
