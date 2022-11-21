package ru.javarush.quest.entities;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class Question {

    @NonNull
    private long id;

    @NonNull
    private QuestElementType elementType;

    @NonNull
    private String text;

    @Singular
    private List<Long> answerIds;

    public boolean hasAnswer(long answerId) {
        return answerIds.contains(answerId);
    }
}
