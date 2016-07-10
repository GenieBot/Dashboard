package io.sponges.bot.dashboard.entity;

import io.sponges.bot.dashboard.network.Network;
import spark.Session;

import java.util.Map;

public interface Account {

    String getPlatform();

    Session getSession();

    Network[] getNetworks();

    Map<String, Network> getNetworkCache();

    void updateNetworkCache(Network[] networks);


}
