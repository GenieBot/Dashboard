package io.sponges.bot.dashboard.newdesign.user.impl;

import io.sponges.bot.dashboard.newdesign.Database;
import io.sponges.bot.dashboard.newdesign.user.User;
import io.sponges.bot.dashboard.newdesign.user.UserManager;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

public class UserManagerImpl implements UserManager {

    private final Map<Session, User> users = new HashMap<>();

    private final Database database;

    public UserManagerImpl(Database database) {
        this.database = database;
    }

    @Override
    public User getOrLoadUser(Session session) {
        if (users.containsKey(session)) {
            return users.get(session);
        }
        User user = new UserImpl(database, session);
        users.put(session, user);
        return user;
    }

    @Override
    public void removeUser(Session session) {
        users.remove(session);
    }

}
