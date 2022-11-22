package ru.javarush.quest.services.util;

import ru.javarush.quest.entities.User;
import ru.javarush.quest.services.adapters.SessionAdapter;

public final class SessionAttributes {
    private static final String USER = "user";
    private static final String QUEST_ID = "questId";
    private static final String QUESTION_ID = "questionId";

    private final SessionAdapter session;

    public SessionAttributes(SessionAdapter session) {
        this.session = session;
    }

    public Long getQuestId() {
        return (Long) session.getAttribute(QUEST_ID);
    }

    public void setQuestId(Long questId) {
        if (questId == null)
            session.removeAttribute(QUEST_ID);
        else
            session.setAttribute(QUEST_ID, questId);
    }

    public Long getQuestionId() {
        return (Long) session.getAttribute(QUESTION_ID);
    }

    public void setQuestionId(Long questionId) {
        if (questionId == null)
            session.removeAttribute(QUESTION_ID);
        else
            session.setAttribute(QUESTION_ID, questionId);
    }

    public User getUser() {
        return (User) session.getAttribute(USER);
    }

    public void setUser(User user) {
        if (user == null)
            session.removeAttribute(USER);
        else
            session.setAttribute(USER, user);
    }
}