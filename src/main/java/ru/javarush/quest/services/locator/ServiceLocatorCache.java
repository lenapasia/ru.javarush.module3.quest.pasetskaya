package ru.javarush.quest.services.locator;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

final class ServiceLocatorCache {
    private final Map<Type, Object> services = new HashMap<>();

    public Object getService(Type type) {
        return services.get(type);
    }

    public void addService(Object newService) {
        services.put(newService.getClass(), newService);
    }
}
