package io.sponges.bot.dashboard.newdesign.user;

import spark.Session;

public interface UserManager {

    User getOrLoadUser(Session session);

    void removeUser(Session session);

}
