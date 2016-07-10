package io.sponges.bot.dashboard.network;

import org.json.JSONObject;

public interface Network {

    String getName();

    String getId();

    JSONObject toJson();

}
