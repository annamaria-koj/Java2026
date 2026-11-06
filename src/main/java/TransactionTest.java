import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionTest {

    public static void main(String[] args) {

        String insertSupplier = """
                insert into equipment_suppliers
                (id, name, phone, email)
                values (?, ?, ?, ?)
                """;

        String updateEquipment = """
                update sports_equipment
                set supplier_id = ?
                where id = ?
                """;

        try (Connection connection =
                     DBConnection.getInstance().getConnection()) {

           
            connection.setAutoCommit(false);

            try {
               
                try (PreparedStatement statement =
                             connection.prepareStatement(insertSupplier)) {

                    statement.setLong(1, 502);
                    statement.setString(2, "Test Supplier");
                    statement.setString(3, "+380501234567");
                    statement.setString(4, "test502@example.com");

                    statement.executeUpdate();
                }

            
                try (PreparedStatement statement =
                             connection.prepareStatement(updateEquipment)) {

                    statement.setLong(1, 502);
                    statement.setLong(2, 1001);

                    int rows = statement.executeUpdate();

                    if (rows == 0) {
                        throw new RuntimeException(
                                "Обладнання не знайдено!");
                    }
                }

             
                throw new RuntimeException(
                        "Тестова помилка для перевірки rollback");

            } catch (Exception e) {

             
                connection.rollback();

                System.out.println("Транзакцію скасовано!");
                System.out.println("Причина: " + e.getMessage());

            } finally {

               
                connection.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
