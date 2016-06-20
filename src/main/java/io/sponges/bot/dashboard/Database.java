package io.sponges.bot.dashboard;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Database {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String USERS_TABLE = "users";

    private final HikariDataSource dataSource;
    private final ExecutorService executorService;

    public Database() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/dashboard?autoReconnect=true&useSSL=false");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.dataSource = new HikariDataSource(config);
        this.executorService = Executors.newFixedThreadPool(10);

        Main.getLogger().info("Creating table " + USERS_TABLE + " if not exists!");
        createTable();
    }

    public String hash(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        md.update(input.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public byte[] getNewSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    private void createTable() {
        createTable(null);
    }

    private void createTable(Runnable runnable) {
        executorService.submit(() -> {
            try (Connection connection = dataSource.getConnection()) {
                String statementContent = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE + " (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, email VARCHAR(254) NOT NULL, password CHAR(64) NOT NULL, salt CHAR(16) NOT NULL);";
                PreparedStatement statement = connection.prepareStatement(statementContent);
                statement.execute();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (runnable != null) runnable.run();
        });
    }

    public void createUser(String email, String password, String salt) {
        createUser(email, password, salt, null);
    }

    public void createUser(String email, String password, String salt, Runnable runnable) {
        executorService.submit(() -> {
            try (Connection connection = dataSource.getConnection()) {
                String statementContent = "INSERT INTO " + USERS_TABLE + " (email, password, salt) VALUES (?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(statementContent);
                statement.setString(1, email);
                statement.setString(2, hash(salt + password)); // hash the combination of the salt and password
                statement.setString(3, salt); // store the salt used when hashing
                statement.execute();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (runnable != null) runnable.run();
        });
    }

    public boolean isUserSync(String email) {
        try (Connection connection = dataSource.getConnection()) {
            String statementContent = "SELECT * FROM " + USERS_TABLE + " WHERE email=?";
            PreparedStatement statement = connection.prepareStatement(statementContent);
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();
            boolean result = results.isBeforeFirst();
            statement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void isUser(String email, Consumer<Boolean> callback) {
        executorService.submit(() -> {
            try (Connection connection = dataSource.getConnection()) {
                String statementContent = "SELECT * FROM " + USERS_TABLE + " WHERE email=?";
                PreparedStatement statement = connection.prepareStatement(statementContent);
                statement.setString(1, email);
                ResultSet results = statement.executeQuery();
                callback.accept(results.isBeforeFirst());
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public int getUserIdSync(String email) {
        try (Connection connection = dataSource.getConnection()) {
            String statementContent = "SELECT id FROM " + USERS_TABLE + " WHERE email=?";
            PreparedStatement statement = connection.prepareStatement(statementContent);
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();
            results.next();
            int response = results.getInt("id");
            statement.close();
            return response;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void getUserId(String email, Consumer<Integer> callback) {
        executorService.submit(() -> {
            try (Connection connection = dataSource.getConnection()) {
                String statementContent = "SELECT id FROM " + USERS_TABLE + " WHERE email=?";
                PreparedStatement statement = connection.prepareStatement(statementContent);
                statement.setString(1, email);
                ResultSet results = statement.executeQuery();
                results.next();
                int response = results.getInt("id");
                callback.accept(response);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public String getUserValueSync(int id, String column) {
        try (Connection connection = dataSource.getConnection()) {
            String statementContent = "SELECT " + column + " FROM " + USERS_TABLE + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(statementContent);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            results.next();
            String response = results.getString(column);
            statement.close();
            return response;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getUserValue(int id, String column, Consumer<String> callback) {
        executorService.submit(() -> {
            try (Connection connection = dataSource.getConnection()) {
                String statementContent = "SELECT " + column + " FROM " + USERS_TABLE + " WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(statementContent);
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                results.next();
                String response = results.getString(column);
                callback.accept(response);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
