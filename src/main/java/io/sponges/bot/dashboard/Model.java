package io.sponges.bot.dashboard;

import java.util.HashMap;
import java.util.Map;

public class Model {

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private final Map<String, Object> mappings = new HashMap<>();

        public Builder with(String key, Object value) {
            mappings.put(key, value);
            return this;
        }

        public Map<String, Object> build() {
            return mappings;
        }

    }

}
