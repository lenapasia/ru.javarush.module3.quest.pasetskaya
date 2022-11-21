package ru.javarush.quest.repositories.locator.quest;

import ru.javarush.quest.entities.Quest;

final class QuestBuilder {
    private final DefaultQuestsBuilderContext context;
    private String name;
    private String description;
    private Long firstQuestionId;

    public QuestBuilder(DefaultQuestsBuilderContext context) {
        this.context = context;
    }

    public Quest build() {
        Quest quest = Quest.builder()
                .id(context.newQuestId())
                .name(name)
                .description(description)
                .firstQuestionId(firstQuestionId)
                .build();
        context.addQuest(quest);
        return quest;
    }

    public QuestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public QuestBuilder description(String description) {
        this.description = description;
        return this;
    }

    public QuestBuilder firstQuestionId(Long firstQuestionId) {
        this.firstQuestionId = firstQuestionId;
        return this;
    }
}
