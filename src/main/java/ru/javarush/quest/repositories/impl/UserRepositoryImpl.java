package ru.javarush.quest.repositories.impl;

import ru.javarush.quest.entities.User;
import ru.javarush.quest.repositories.UserRepository;

import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> nameToUser;

    public UserRepositoryImpl(Map<String, User> nameToUser) {
        this.nameToUser = nameToUser;
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(nameToUser.get(name));
    }

    @Override
    public void save(User user) {
        nameToUser.put(user.getName(), user);
    }
}
