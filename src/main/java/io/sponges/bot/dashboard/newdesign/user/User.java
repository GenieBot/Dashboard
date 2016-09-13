package io.sponges.bot.dashboard.newdesign.user;

import io.sponges.bot.dashboard.newdesign.platform.account.Account;
import spark.Session;

import java.util.Collection;

public interface User {

    Session getSession();

    Collection<Account> getOrLoadAccounts();

    Account getAccount(String name);

    void addAccount(Account account);

    void removeAccount(String name);

}
