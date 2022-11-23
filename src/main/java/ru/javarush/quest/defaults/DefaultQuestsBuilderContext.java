package ru.javarush.quest.defaults;

import lombok.Getter;
import ru.javarush.quest.entities.Answer;
import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.entities.Question;

import java.util.HashMap;
import java.util.Map;

final class DefaultQuestsBuilderContext {

    private int questKey;
    private int questionKey;
    private int answerKey;

    @Getter
    private final Map<Long, Quest> idToQuest = new HashMap<>();

    @Getter
    private final Map<Long, Question> idToQuestion = new HashMap<>();

    @Getter
    private final Map<Long, Answer> idToAnswer = new HashMap<>();

    public int newQuestId() {
        return ++questKey;
    }

    public int newQuestionId() {
        return ++questionKey;
    }

    public int newAnswerId() {
        return ++answerKey;
    }

    public void addQuest(Quest quest) {
        idToQuest.put(quest.getId(), quest);
    }

    public void addQuestion(Question question) {
        idToQuestion.put(question.getId(), question);
    }

    public void addAnswer(Answer answer) {
        idToAnswer.put(answer.getId(), answer);
    }
}