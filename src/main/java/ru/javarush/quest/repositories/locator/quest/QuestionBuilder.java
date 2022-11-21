package ru.javarush.quest.repositories.locator.quest;

import ru.javarush.quest.entities.QuestElementType;
import ru.javarush.quest.entities.Question;

import java.util.ArrayList;
import java.util.List;

final class QuestionBuilder {
    private final DefaultQuestsBuilderContext context;

    private QuestElementType elementType;
    private String text;
    private List<Long> answerIds;

    public QuestionBuilder(DefaultQuestsBuilderContext context) {
        this.context = context;
    }

    public Question build() {
        if (elementType == null) {
            if (answerIds != null && !answerIds.isEmpty())
                elementType = QuestElementType.QUESTION;
            else
                throw new RuntimeException("Element type is not defined.");
        }

        Question question = new Question(context.newQuestionId(), elementType, text, answerIds);
        context.addQuestion(question);
        return question;
    }

    public QuestionBuilder elementType(QuestElementType elementType) {
        this.elementType = elementType;
        return this;
    }

    public QuestionBuilder text(String text) {
        this.text = text;
        return this;
    }

    public QuestionBuilder answerIds(List<Long> answerIds) {
        this.answerIds = answerIds;
        return this;
    }

    public QuestionBuilder answerId(long answerId) {
        if (this.answerIds == null)
            this.answerIds = new ArrayList<>();
        this.answerIds.add(answerId);
        return this;
    }
}
