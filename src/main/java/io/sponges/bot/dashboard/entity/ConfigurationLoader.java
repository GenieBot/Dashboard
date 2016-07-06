package io.sponges.bot.dashboard.entity;

import org.json.JSONObject;

import java.io.*;

public final class ConfigurationLoader {

    private static final JSONObject DEFAULT_CONFIG = new JSONObject()
            .put("discord", new JSONObject()
                            .put("client_id", "")
                            .put("client_secret", "")
            ).put("recaptcha_secret", "");

    public static Configuration load(File file) {
        if (!file.exists()) {
            Configuration configuration = new Configuration(file, DEFAULT_CONFIG);
            save(configuration);
            return load(file); // because we dont want to use the default config instance
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String input;
            while ((input = reader.readLine()) != null) {
                builder.append(input).append("\n");
            }
            JSONObject json = new JSONObject(builder.toString());
            return new Configuration(file, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void save(Configuration configuration) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configuration.file))) {
            writer.write(configuration.jsonObject.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Configuration {

        private final File file;
        private final JSONObject jsonObject;

        protected Configuration(File file, JSONObject jsonObject) {
            this.file = file;
            this.jsonObject = jsonObject;
        }

        public void save() {
            ConfigurationLoader.save(this);
        }

        public JSONObject getJsonObject() {
            return jsonObject;
        }

    }

}
