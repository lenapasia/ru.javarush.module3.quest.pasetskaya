package ru.javarush.quest.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Answer {

    @NonNull
    private long id;

    @NonNull
    private String text;

    @NonNull
    private long nextQuestionId;
}
