package ru.javarush.quest.repositories.impl;

import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.repositories.QuestRepository;

import java.util.Map;

class QuestRepositoryImplTest extends CrudRepositoryTest<Quest, QuestRepository> {
    @Override
    protected Quest createEntity(long id) {
        return new Quest(id, "Quest1", "Description1", 1);
    }

    @Override
    protected QuestRepository createRepository(Map<Long, Quest> idToEntity) {
        return new QuestRepositoryImpl(idToEntity);
    }
}