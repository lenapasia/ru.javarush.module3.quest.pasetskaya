package ru.javarush.quest.repositories;

import ru.javarush.quest.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> nameToUser;

    public UserRepositoryImpl() {
        nameToUser = new HashMap<>();
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
