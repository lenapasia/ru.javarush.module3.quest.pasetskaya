package ru.javarush.quest.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@Builder
public class Quest {

    @NonNull
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private long firstQuestionId;
}