package ru.javarush.quest.repositories;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.javarush.quest.entities.User;
import ru.javarush.quest.repositories.impl.UserRepositoryImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;

class UserRepositoryTest {

    @Test
    void findByName() {
        final String username = "FindUser1";
        final User expected = new User(username);
        final Map<String, User> nameToUser = new HashMap<>();
        nameToUser.put(expected.getName(), expected);

        final UserRepository userRepository = new UserRepositoryImpl(nameToUser);

        final Optional<User> actual = userRepository.findByName(username);

        assertSame(expected, actual.orElseGet(null));
    }

    @Test
    void save() {
        final String username = "SaveUser1";
        final User user = new User(username);

        final Map<String, User> nameToUser = Mockito.mock(HashMap.class);

        final UserRepository userRepository = new UserRepositoryImpl(nameToUser);
        userRepository.save(user);

        Mockito.verify(nameToUser).put(username, user);
    }
}