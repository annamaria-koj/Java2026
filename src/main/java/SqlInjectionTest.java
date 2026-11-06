import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlInjectionTest {

    public static void main(String[] args) {

        DBConnection db = DBConnection.getInstance();

        try {
            db.initializeDatabase();

            String input = "' OR '1'='1";

            System.out.println("=== Небезпечний запит ===");

            String unsafeSql =
                    "select * from sports_equipment where name = '"
                            + input + "'";

            try (Connection connection = db.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet =
                         statement.executeQuery(unsafeSql)) {

                int count = 0;

                while (resultSet.next()) {
                    count++;
                }

                System.out.println(
                        "Кількість знайдених записів: " + count);
            }

            System.out.println("\n=== Безпечний запит ===");

            String safeSql =
                    "select * from sports_equipment where name = ?";

            try (Connection connection = db.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(safeSql)) {

                statement.setString(1, input);

                try (ResultSet resultSet = statement.executeQuery()) {

                    int count = 0;

                    while (resultSet.next()) {
                        count++;
                    }

                    System.out.println(
                            "Кількість знайдених записів: " + count);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
