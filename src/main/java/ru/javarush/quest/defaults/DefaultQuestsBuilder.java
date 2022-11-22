package ru.javarush.quest.defaults;

import ru.javarush.quest.entities.Answer;
import ru.javarush.quest.entities.Quest;
import ru.javarush.quest.entities.QuestElementType;
import ru.javarush.quest.entities.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DefaultQuestsBuilder {

    private final DefaultQuestsBuilderContext context = new DefaultQuestsBuilderContext();

    public Map<Long, Quest> getIdToQuest() {
        return context.getIdToQuest();
    }

    public Map<Long, Question> getIdToQuestion() {
        return context.getIdToQuestion();
    }

    public Map<Long, Answer> getIdToAnswer() {
        return context.getIdToAnswer();
    }

    private QuestBuilder newQuest() {
        return new QuestBuilder(context);
    }

    private QuestionBuilder newQuestion() {
        return new QuestionBuilder(context);
    }

    private AnswerBuilder newAnswer() {
        return new AnswerBuilder(context);
    }

    public List<Quest> build() {
        List<Quest> quests = new ArrayList<>();
        //quests.add(quest1());
        quests.add(quest2());
        return quests;
    }

    private Quest quest1() {
        Question q6Win = newQuestion()
                .elementType(QuestElementType.WIN)
                .text("Win after question 5")
                .build();

        Answer q6a1 = newAnswer()
                .text("Answer 5 --> END WIN")
                .nextQuestionId(q6Win.getId())
                .build();
        Question q6 = newQuestion()
                .text("Question 5")
                .answerId(q6a1.getId())
                .build();

        Question q5Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("Defeat after question 4")
                .build();

        Answer q5a1 = newAnswer()
                .text("Answer 4 --> 5. Пример текста на русском языке. Russian language example.")
                .nextQuestionId(q6.getId())
                .build();
        Answer q5a2 = newAnswer()
                .text("Answer 4 --> END DEFEAT")
                .nextQuestionId(q5Defeat.getId())
                .build();
        Question q5 = newQuestion()
                .text("Question 4")
                .answerId(q5a1.getId())
                .answerId(q5a2.getId())
                .build();

        Question q4Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("Defeat after question 3")
                .build();

        Answer q4a1 = newAnswer()
                .text("Answer 3 --> 4")
                .nextQuestionId(q5.getId())
                .build();
        Answer q4a2 = newAnswer()
                .text("Answer 3 --> END DEFEAT")
                .nextQuestionId(q4Defeat.getId())
                .build();
        Question q4 = newQuestion()
                .text("Question 3")
                .answerId(q4a1.getId())
                .answerId(q4a2.getId())
                .build();

        Question q3Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("Defeat after question 2")
                .build();

        Answer q3a1 = newAnswer()
                .text("Answer 2 --> 3")
                .nextQuestionId(q4.getId())
                .build();
        Answer q3a2 = newAnswer()
                .text("Answer 2 --> END DEFEAT")
                .nextQuestionId(q3Defeat.getId())
                .build();
        Question q3 = newQuestion()
                .text("Question 2")
                .answerId(q3a1.getId())
                .answerId(q3a2.getId())
                .build();

        Question q2Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("Defeat after question 1")
                .build();

        Question q2Win = newQuestion()
                .elementType(QuestElementType.WIN)
                .text("Win after question 1")
                .build();

        Answer q2a1 = newAnswer()
                .text("Answer 1 --> 2")
                .nextQuestionId(q3.getId())
                .build();
        Answer q2a2 = newAnswer()
                .text("Answer 1 --> END DEFEAT")
                .nextQuestionId(q2Defeat.getId())
                .build();
        Answer q2a3 = newAnswer()
                .text("Answer 1 --> END WIN")
                .nextQuestionId(q2Win.getId())
                .build();
        Question q2 = newQuestion()
                .text("Question 1")
                .answerId(q2a1.getId())
                .answerId(q2a2.getId())
                .answerId(q2a3.getId())
                .build();

        Answer q1a1 = newAnswer()
                .text("Init 0 --> 1")
                .nextQuestionId(q2.getId())
                .build();
        Question q1 = newQuestion()
                .text("Start")
                .answerId(q1a1.getId())
                .build();

        return newQuest()
                .name("First Quest")
                .description("Game description bla-bla-bla")
                .firstQuestionId(q1.getId())
                .build();
    }

    private Quest quest2() {
        Question q3Win = newQuestion()
                .elementType(QuestElementType.WIN)
                .text("Тебя вернули домой. Победа")
                .build();

        Question q3Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("Твою ложь разоблачили. Поражение.")
                .build();

        Answer q3a1 = newAnswer()
                .text("Рассказать правду о себе")
                .nextQuestionId(q3Win.getId())
                .build();
        Answer q3a2 = newAnswer()
                .text("Солгать о себе")
                .nextQuestionId(q3Defeat.getId())
                .build();
        Question q3 = newQuestion()
                .text("Ты поднялся на мостик. Ты кто?")
                .answerId(q3a1.getId())
                .answerId(q3a2.getId())
                .build();

        Question q2Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("Ты не пошел на переговоры. Поражение")
                .build();

        Answer q2a1 = newAnswer()
                .text("Подняться на мостик")
                .nextQuestionId(q3.getId())
                .build();
        Answer q2a2 = newAnswer()
                .text("Отказаться подниматься на мостик")
                .nextQuestionId(q2Defeat.getId())
                .build();
        Question q2 = newQuestion()
                .text("Ты принял вызов. Поднимешься на мостик к капитану?")
                .answerId(q2a1.getId())
                .answerId(q2a2.getId())
                .build();

        Question q1Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("Ты отклонил вызов. Поражение")
                .build();

        Answer q1a2 = newAnswer()
                .text("Принять вызов")
                .nextQuestionId(q2.getId())
                .build();
        Answer q1a1 = newAnswer()
                .text("Отклонить вызов")
                .nextQuestionId(q1Defeat.getId())
                .build();
        Question q1 = newQuestion()
                .text("Ты потерял память. Принять вызов НЛО?")
                .answerId(q1a1.getId())
                .answerId(q1a2.getId())
                .build();

        return newQuest()
                .name("Приключения уфолога")
                .description("Однажды в один необычный день ...")
                .firstQuestionId(q1.getId())
                .build();
    }

}