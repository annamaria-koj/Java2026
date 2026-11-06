public class JdbcServiceTest {

    public static void main(String[] args) {

        JdbcSportsEquipmentService service =
                new JdbcSportsEquipmentService();

        try {
            // 1. Додавання обладнання
            service.save(new StrengthEquipment(
                    1001, "Гантелі", "Силове обладнання",
                    10, 1500, "Demo", 30.0
            ));

            service.save(new CardioEquipment(
                    1002, "Велотренажер", "Кардіо",
                    5, 8000, "SportTech", 25.0
            ));

            service.save(new Treadmill(
                    1003, "Бігова доріжка", "Кардіо",
                    3, 25000, "FitPro", 20.0, 15.0
            ));

            System.out.println("Обладнання додано!");

            // 2. Пошук за ID
            System.out.println("\nПошук за ID 1001:");
            System.out.println(service.findById(1001));

            // 3. Оновлення
            service.save(new StrengthEquipment(
                    1001, "Гантелі", "Силове обладнання",
                    20, 1800, "Demo", 35.0
            ));

            System.out.println("\nПісля оновлення:");
            System.out.println(service.findById(1001));

            // 4. Сортування за назвою та ціною
            System.out.println("\nСортування:");
            EntityPrinter.printAll(
                    service.findAllSortedByNameAndByPrice()
            );

            // 5. Видалення
            service.delete(1002);

            System.out.println("\nПісля видалення:");
            EntityPrinter.printAll(service.findAllSortedByName());

        } catch (Exception e) {
            System.out.println("Помилка:");
            e.printStackTrace();
        }
    }
}
