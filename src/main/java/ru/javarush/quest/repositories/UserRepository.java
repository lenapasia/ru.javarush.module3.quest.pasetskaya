package ru.javarush.quest.repositories;

import ru.javarush.quest.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByName(String name);
    void save (User user);
}
