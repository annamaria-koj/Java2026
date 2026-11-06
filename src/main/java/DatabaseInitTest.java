public class DatabaseInitTest {

    public static void main(String[] args) {

        DBConnection dbConnection = DBConnection.getInstance();

        try {
            dbConnection.initializeDatabase();

            System.out.println("Ініціалізацію завершено!");

        } finally {
            dbConnection.close();
            System.out.println("Пул з'єднань закрито!");
        }
    }
}
