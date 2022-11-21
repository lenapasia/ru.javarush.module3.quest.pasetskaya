package ru.javarush.quest.services.locator;

import ru.javarush.quest.repositories.locator.Repositories;
import ru.javarush.quest.services.QuestService;
import ru.javarush.quest.services.UserService;

import java.lang.reflect.Type;
import java.util.Formatter;

final class ServiceLocatorContext {
    public Object lookup(Type serviceType) {
        if (serviceType == QuestService.class) {
            return new QuestService(Repositories.get().getAnswer(), Repositories.get().getQuestion(),
                    Repositories.get().getQuest(), Repositories.get().getUser());
        }

        if (serviceType == UserService.class) {
            return new UserService(Repositories.get().getUser());
        }

        throw new RuntimeException(new Formatter().format(
                "Unregistered service with class type '%s'.", serviceType).toString());
    }
}
