package ru.javarush.quest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class QuestDto {
    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private boolean completed;
}
