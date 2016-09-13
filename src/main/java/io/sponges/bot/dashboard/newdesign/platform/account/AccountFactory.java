package io.sponges.bot.dashboard.newdesign.platform.account;

import io.sponges.bot.dashboard.newdesign.user.User;

import java.util.HashMap;
import java.util.Map;

public abstract class AccountFactory {

    private static final Map<String, AccountFactory> factories = new HashMap<>();

    public AccountFactory(String platform) {
        factories.put(platform, this);
    }

    protected abstract Account create(User user, String value);

    public static Account createAccount(User user, String platform, String value) {
        if (!factories.containsKey(platform)) {
            System.out.println("no platform " + platform);
        }
        return factories.get(platform).create(user, value);
    }

}
