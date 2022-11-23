package ru.javarush.quest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionDto {
    private long id;

    @NonNull
    private String text;

    private List<AnswerDto> answers;
}
