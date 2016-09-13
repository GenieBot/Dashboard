package io.sponges.bot.dashboard.newdesign;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public final class Model {

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private final Map<String, Object> mappings = new HashMap<>();
        private final JSONObject data = new JSONObject();

        private Builder() {
        }

        public Builder with(String key, Object value) {
            mappings.put(key, value);
            return this;
        }

        public Builder data(String key, Object value) {
            data.put(key, value);
            return this;
        }

        public Map<String, Object> build() {
            mappings.put("internal_data", data);
            return mappings;
        }

    }

}
