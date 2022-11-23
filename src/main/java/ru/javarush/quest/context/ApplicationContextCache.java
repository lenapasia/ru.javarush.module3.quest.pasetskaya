package ru.javarush.quest.context;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

class ApplicationContextCache {
    private final Map<Type, Object> services = new HashMap<>();

    public Object getService(Type type) {
        return services.get(type);
    }

    public void addService(Object newService) {
        services.put(newService.getClass(), newService);
    }
}
