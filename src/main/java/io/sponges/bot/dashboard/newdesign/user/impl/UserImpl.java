package io.sponges.bot.dashboard.newdesign.user.impl;

import io.sponges.bot.dashboard.newdesign.platform.account.Account;
import io.sponges.bot.dashboard.newdesign.Database;
import io.sponges.bot.dashboard.newdesign.Main;
import io.sponges.bot.dashboard.newdesign.platform.account.AccountFactory;
import io.sponges.bot.dashboard.newdesign.user.User;
import spark.Session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserImpl implements User {

    private final Map<String, Account> accounts = new HashMap<>();

    private final Database database;
    private final Session session;

    public UserImpl(Database database, Session session) {
        this.database = database;
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Collection<Account> getOrLoadAccounts() {
        if (accounts.isEmpty()) {
            loadAccounts();
        }
        return accounts.values();
    }

    private void loadAccounts() {
        accounts.clear();
        int id = session.attribute("id");
        for (String platform : Main.AVAILABLE_PLATFORMS) {
            if (!database.hasUserPlatformColumnSync(id, platform)) {
                continue;
            }
            String value = database.getUserPlatformValueSync(id, platform);
            Account account = AccountFactory.createAccount(this, platform, value);
            accounts.put(platform, account);
        }
    }

    @Override
    public Account getAccount(String name) {
        return accounts.get(name);
    }

    @Override
    public void addAccount(Account account) {
        // TODO
    }

    @Override
    public void removeAccount(String name) {
        // TODO
    }
}
