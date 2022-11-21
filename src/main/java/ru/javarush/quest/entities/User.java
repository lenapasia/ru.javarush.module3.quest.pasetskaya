package ru.javarush.quest.entities;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @NonNull
    private String name;

    private int playedGamesCount;

    public User(@NonNull String name) {
        this.name = name;
    }

    public void incrementPlayedGamesCount() {
        playedGamesCount++;
    }
}
