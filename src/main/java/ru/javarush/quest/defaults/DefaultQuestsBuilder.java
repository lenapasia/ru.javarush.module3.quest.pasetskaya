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
        quests.add(quest1());
        return quests;
    }

    private Quest quest1() {
        Question q3Win = newQuestion()
                .elementType(QuestElementType.WIN)
                .text("You have been returned home. Victory.")
                .build();

        Question q3Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("Your lies have been exposed. Defeat.")
                .build();

        Answer q3a1 = newAnswer()
                .text("Tell the truth about yourself.")
                .nextQuestionId(q3Win.getId())
                .build();
        Answer q3a2 = newAnswer()
                .text("Lie about yourself.")
                .nextQuestionId(q3Defeat.getId())
                .build();
        Question q3 = newQuestion()
                .text("You have climbed the bridge. Who are you?")
                .answerId(q3a1.getId())
                .answerId(q3a2.getId())
                .build();

        Question q2Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("You didn't negotiate. Defeat.")
                .build();

        Answer q2a1 = newAnswer()
                .text("Climb to the bridge.")
                .nextQuestionId(q3.getId())
                .build();
        Answer q2a2 = newAnswer()
                .text("Refuse to climb the bridge.")
                .nextQuestionId(q2Defeat.getId())
                .build();
        Question q2 = newQuestion()
                .text("You accepted the challenge. Will you go up to the captain's bridge?")
                .answerId(q2a1.getId())
                .answerId(q2a2.getId())
                .build();

        Question q1Defeat = newQuestion()
                .elementType(QuestElementType.DEFEAT)
                .text("You rejected the challenge. Defeat.")
                .build();

        Answer q1a2 = newAnswer()
                .text("Accept the challenge.")
                .nextQuestionId(q2.getId())
                .build();
        Answer q1a1 = newAnswer()
                .text("Reject the challenge.")
                .nextQuestionId(q1Defeat.getId())
                .build();
        Question q1 = newQuestion()
                .text("You've lost your memory. Accept the UFO challenge?")
                .answerId(q1a1.getId())
                .answerId(q1a2.getId())
                .build();

        return newQuest()
                .name("Adventures of an ufologist")
                .description("<h4><b>Introduction</b></h4><p>You are standing in the space port and ready to board your ship. Didn't you dream about it? Become the captain of a galactic ship with a crew that will perform feats under your command. So go ahead!</p><h4><b>Getting to know the crew</b></h4><p>When you boarded the ship, you were greeted by a girl with a black stick in her hands: - Hello, commander! I'm Michelle - your assistant. You see, there in the corner our navigator, Sergeant Peregarny Shleif, is drinking coffee, our flight mechanic, Zeleny, is sleeping under the helm, and Sergei Steel Grasp, our navigator, is photographing him.</p>")
                .firstQuestionId(q1.getId())
                .build();
    }

}