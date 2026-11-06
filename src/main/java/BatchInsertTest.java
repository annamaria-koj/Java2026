import java.util.ArrayList;
import java.util.List;

public class BatchInsertTest {

    public static void main(String[] args) {

        JdbcSportsEquipmentService service =
                new JdbcSportsEquipmentService();

        List<SportsEquipment> individualList =
                new ArrayList<>();

        List<SportsEquipment> batchList =
                new ArrayList<>();

        long baseId = System.currentTimeMillis() * 1000;

        for (int i = 0; i < 100; i++) {

            individualList.add(new StrengthEquipment(
                    baseId + i,
                    "Індивідуальне обладнання " + i,
                    "Силове",
                    10,
                    500.0,
                    "Test Manufacturer",
                    100.0
            ));

            batchList.add(new StrengthEquipment(
                    baseId + 100 + i,
                    "Пакетне обладнання " + i,
                    "Силове",
                    10,
                    500.0,
                    "Test Manufacturer",
                    100.0
            ));
        }

        try {
            long startIndividual = System.nanoTime();

            for (SportsEquipment equipment : individualList) {
                service.save(equipment);
            }

            long individualTime =
                    System.nanoTime() - startIndividual;

            long startBatch = System.nanoTime();

            service.batchInsert(batchList);

            long batchTime = System.nanoTime() - startBatch;

            System.out.println("Кількість записів: 100");
            System.out.println(
                    "Окреме додавання: "
                            + individualTime / 1_000_000.0 + " мс");

            System.out.println(
                    "Пакетне додавання: "
                            + batchTime / 1_000_000.0 + " мс");

            System.out.println("Порівняння завершено!");

        } finally {

            for (SportsEquipment equipment : individualList) {
                try {
                    service.delete(equipment.getId());
                } catch (Exception e) {
                    System.out.println(
                            "Не вдалося видалити запис "
                                    + equipment.getId());
                }
            }

            for (SportsEquipment equipment : batchList) {
                try {
                    service.delete(equipment.getId());
                } catch (Exception e) {
                    System.out.println(
                            "Не вдалося видалити запис "
                                    + equipment.getId());
                }
            }

            DBConnection.getInstance().close();
        }
    }
}
