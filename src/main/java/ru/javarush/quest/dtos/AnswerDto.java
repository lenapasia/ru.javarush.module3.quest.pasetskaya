package ru.javarush.quest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class AnswerDto {
    private long id;

    @NonNull
    private String text;
}