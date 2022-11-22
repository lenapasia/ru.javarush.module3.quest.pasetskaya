package ru.javarush.quest.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.javarush.quest.entities.User;
import ru.javarush.quest.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;

class UserServiceTest {

    @ParameterizedTest
    @ValueSource(strings = {"User1"})
    void findOrCreateUser(String username) {
        final UserService userService = new UserService(Mockito.mock(UserRepository.class));
        final User actual = userService.findOrCreateUser(username);

        assertSame(username, actual.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"FindUser1"})
    void find(String username) {
        final User expectedUser = new User(username);
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final Optional<User> optional = Optional.of(expectedUser);
        Mockito.doReturn(optional).when(userRepository).findByName(username);

        final UserService userService = new UserService(userRepository);
        final User actualUser = userService.find(username);

        assertSame(expectedUser, actualUser);
    }

    @ParameterizedTest
    @ValueSource(strings = {"SaveUser1"})
    void save(String username) {
        final User user = new User(username);

        final UserRepository userRepository = Mockito.mock(UserRepository.class);

        final UserService userService = new UserService(userRepository);
        userService.save(user);

        Mockito.verify(userRepository).save(user);
    }
}