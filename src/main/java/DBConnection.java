import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

    private final HikariDataSource dataSource;

    private DBConnection() {

        Properties properties = new Properties();

        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Файл application.properties не знайдено");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Помилка завантаження налаштувань БД", e);
        }

        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        if (url == null || username == null || password == null) {
            throw new RuntimeException(
                    "Не всі налаштування бази даних вказані");
        }

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setPoolName("SportInventoryPool");

        dataSource = new HikariDataSource(config);
    }

    private static class Holder {
        private static final DBConnection INSTANCE =
                new DBConnection();
    }

    public static DBConnection getInstance() {
        return Holder.INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void initializeDatabase() {

        try (Connection connection = getConnection();
             InputStream input = getClass()
                     .getClassLoader()
                     .getResourceAsStream("init.sql")) {

            if (input == null) {
                throw new RuntimeException("Файл init.sql не знайдено");
            }

            String sql = new String(
                    input.readAllBytes(),
                    java.nio.charset.StandardCharsets.UTF_8
            );

            try (Statement statement = connection.createStatement()) {

                for (String command : sql.split(";")) {
                    if (!command.isBlank()) {
                        statement.execute(command);
                    }
                }
            }

            System.out.println(
                    "Структуру бази даних перевірено!");

        } catch (Exception e) {
            throw new RuntimeException(
                    "Помилка ініціалізації бази даних", e);
        }
    }

    public void close() {
        dataSource.close();
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
