package ru.javarush.quest.services.adapters;

public interface SessionAdapter {
    Object getAttribute(String name);
    void setAttribute(String name, Object value);
    void removeAttribute(String name);
}