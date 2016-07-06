package io.sponges.bot.dashboard.entity.skype;

import io.sponges.bot.dashboard.entity.Account;

public interface SkypeAccount extends Account {

    default String getPlatform() {
        return "skype";
    }

}
