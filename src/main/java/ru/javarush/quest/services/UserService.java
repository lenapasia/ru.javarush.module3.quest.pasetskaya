package ru.javarush.quest.services;

import ru.javarush.quest.entities.User;
import ru.javarush.quest.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(String name) {
        return userRepository.findByName(name).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}