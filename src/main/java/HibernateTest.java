public class HibernateTest {

    public static void main(String[] args) {
        HibernateEquipmentSupplierService service =
                new HibernateEquipmentSupplierService();

        try {
            EquipmentSupplier supplier =
                    new EquipmentSupplier(
                            "Hibernate Supplier",
                            "+380501234567",
                            "hibernate@test.com"
                    );

            service.save(supplier);
            System.out.println("CREATE: постачальника додано");

            System.out.println(
                    "READ: " + service.findById(supplier.getId())
            );

            supplier.setName("Updated Hibernate Supplier");
            service.save(supplier);
            System.out.println("UPDATE: постачальника оновлено");

            System.out.println("ALL: "
                    + service.findAllSortedByName());
            service.delete(supplier.getId());
            System.out.println("DELETE: постачальника видалено");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JpaUtil.close();
        }
    }
}
