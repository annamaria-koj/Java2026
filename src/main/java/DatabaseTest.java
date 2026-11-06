import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {
        try (Connection connection =
                DBConnection.getInstance().getConnection()) {

            System.out.println("Підключення до MySQL успішне!");
            System.out.println("База даних: "
                    + connection.getCatalog());

        } catch (Exception e) {
            System.out.println("Помилка підключення:");
            e.printStackTrace();
        }
    }
}
