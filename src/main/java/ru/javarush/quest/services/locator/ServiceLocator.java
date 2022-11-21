package ru.javarush.quest.services.locator;

import java.lang.reflect.Type;

public final class ServiceLocator {
    private static final ServiceLocatorCache cache = new ServiceLocatorCache();

    private ServiceLocator() {
    }

    public static <T> T getService(Class<T> clazz) {
        return (T) getService((Type) clazz);
    }

    private static Object getService(Type type) {
        Object service = cache.getService(type);

        if (service != null) {
            return service;
        }

        ServiceLocatorContext context = new ServiceLocatorContext();
        Object registeringService = context.lookup(type);
        cache.addService(registeringService);
        return registeringService;
    }
}