package io.sponges.bot.dashboard.entity;

import io.sponges.bot.dashboard.network.Network;
import spark.Session;

public interface Account {

    String getPlatform();

    Session getSession();

    Network[] getNetworks();


}
