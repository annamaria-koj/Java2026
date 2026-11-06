import java.sql.Connection;
import java.sql.PreparedStatement;

public class JdbcSupplierTest {

    public static void main(String[] args) {

        JdbcEquipmentSupplierService service =
                new JdbcEquipmentSupplierService();

        try {
            EquipmentSupplier supplier = new EquipmentSupplier(
                    501,
                    "Sport Ukraine",
                    "+380501112233",
                    "sport@example.com"
            );

            service.save(supplier);
            System.out.println("Постачальника додано!");

       
            System.out.println("\nПошук постачальника:");
            System.out.println(service.findById(501));

        
            service.save(new EquipmentSupplier(
                    501,
                    "Sport Ukraine Updated",
                    "+380509998877",
                    "updated@example.com"
            ));

            System.out.println("\nПісля оновлення:");
            System.out.println(service.findById(501));

            System.out.println("\nСписок постачальників:");
            for (EquipmentSupplier item : service.findAllSortedByName()) {
                System.out.println(item);
            }

  
            String sql = """
                    update sports_equipment
                    set supplier_id = ?
                    where id = ?
                    """;

            try (Connection connection =
                         DBConnection.getInstance().getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setLong(1, 501);
                statement.setLong(2, 1001);
                statement.executeUpdate();
            }

            System.out.println("\nПостачальника прив'язано до обладнання!");

        
            try {
                service.delete(501);
                System.out.println("Помилка: постачальника видалено!");
            } catch (RuntimeException e) {
                System.out.println(
                        "Видалення заборонено: постачальник має обладнання.");
            }

         
            try (Connection connection =
                         DBConnection.getInstance().getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(
                                 "update sports_equipment " +
                                 "set supplier_id = null where id = ?")) {

                statement.setLong(1, 1001);
                statement.executeUpdate();
            }

     
            service.delete(501);
            System.out.println(
                    "\nПостачальника успішно видалено після відв'язування!");

        } catch (Exception e) {
            System.out.println("Помилка тестування:");
            e.printStackTrace();
        }
    }
}
