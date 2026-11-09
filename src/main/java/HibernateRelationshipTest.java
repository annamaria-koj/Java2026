public class HibernateRelationshipTest {

    public static void main(String[] args) {

        try {
            EquipmentSupplier supplier = new EquipmentSupplier(
                    "Relationship Test Supplier",
                    "+380501111111",
                    "relationship2@test.com");

            SportsEquipment equipment = new StrengthEquipment(
                    0,
                    "Test Dumbbells",
                    "Силове обладнання",
                    10,
                    1500,
                    "Demo",
                    30.0);

            // Створюємо зв'язок між постачальником та обладнанням
            supplier.addEquipment(equipment);

            // Зберігаємо обидві сутності
            JpaUtil.executeInTransaction(em -> {
                em.persist(supplier);
                em.persist(equipment);
            });

            System.out.println("CREATE: зв'язок створено");
            System.out.println("Supplier ID: " + supplier.getId());
            System.out.println("Equipment ID: " + equipment.getId());

            // Перевіряємо зв'язок після повторного завантаження
            JpaUtil.executeRead(em -> {
                EquipmentSupplier foundSupplier = em.find(EquipmentSupplier.class, supplier.getId());

                System.out.println(
                        "READ: обладнання постачальника: "
                                + foundSupplier.getEquipment().size());

                SportsEquipment foundEquipment = em.find(SportsEquipment.class, equipment.getId());

                System.out.println(
                        "READ: постачальник обладнання: "
                                + foundEquipment.getSupplier().getName());

                return null;
            });

            // Перевіряємо видалення зв'язку
            JpaUtil.executeInTransaction(em -> {
                EquipmentSupplier foundSupplier = em.find(EquipmentSupplier.class, supplier.getId());

                SportsEquipment foundEquipment = em.find(SportsEquipment.class, equipment.getId());

                foundSupplier.removeEquipment(foundEquipment);

                System.out.println(
                        "REMOVE: кількість обладнання у постачальника: "
                                + foundSupplier.getEquipment().size());
            });

            System.out.println("Зв'язок між постачальником та обладнанням видалено!");

            System.out.println("Перевірку зв'язку завершено!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JpaUtil.close();
        }
    }
}
