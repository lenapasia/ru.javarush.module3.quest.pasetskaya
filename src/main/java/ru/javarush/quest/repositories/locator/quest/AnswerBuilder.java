package ru.javarush.quest.repositories.locator.quest;

import ru.javarush.quest.entities.Answer;

final class AnswerBuilder {
    private final DefaultQuestsBuilderContext context;
    private String text;
    private long nextQuestionId;

    public AnswerBuilder(DefaultQuestsBuilderContext context) {
        this.context = context;
    }

    public Answer build() {
        Answer answer = new Answer(context.newAnswerId(), text, nextQuestionId);
        context.addAnswer(answer);
        return answer;
    }

    public AnswerBuilder text(String text) {
        this.text = text;
        return this;
    }

    public AnswerBuilder nextQuestionId(Long nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
        return this;
    }
}
