package ru.javarush.quest.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void constructorThrowsExceptionWithNullUsername() {
        assertThrows(NullPointerException.class, () -> new User(null));
    }
}