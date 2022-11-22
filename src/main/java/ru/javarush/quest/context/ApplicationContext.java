package ru.javarush.quest.context;

import java.lang.reflect.Type;

public class ApplicationContext {

    private final ApplicationContextCache cache = new ApplicationContextCache();
    private ApplicationContextInitializer initializer;

    public <T> T getService(Class<T> clazz) {
        return (T) getService((Type) clazz);
    }

    private Object getService(Type type) {
        Object service = cache.getService(type);

        if (service != null) {
            return service;
        }

        service = lookup(type);
        cache.addService(service);
        return service;
    }

    public Object lookup(Type serviceType) {
        if (initializer == null) {
            initializer = new ApplicationContextInitializer();
            initializer.init();
        }
        return initializer.lookup(serviceType);
    }
}