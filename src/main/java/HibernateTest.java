public class HibernateTest {

    public static void main(String[] args) {
        HibernateEquipmentSupplierService service =
                new HibernateEquipmentSupplierService();

        try {
            // CREATE
            EquipmentSupplier supplier =
                    new EquipmentSupplier(
                            "Hibernate Supplier",
                            "+380501234567",
                            "hibernate@test.com"
                    );

            service.save(supplier);
            System.out.println("CREATE: постачальника додано");

            // READ
            System.out.println(
                    "READ: " + service.findById(supplier.getId())
            );

            // UPDATE
            supplier.setName("Updated Hibernate Supplier");
            service.save(supplier);
            System.out.println("UPDATE: постачальника оновлено");

            // READ ALL
            System.out.println("ALL: "
                    + service.findAllSortedByName());

            // DELETE
            service.delete(supplier.getId());
            System.out.println("DELETE: постачальника видалено");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JpaUtil.close();
        }
    }
}
