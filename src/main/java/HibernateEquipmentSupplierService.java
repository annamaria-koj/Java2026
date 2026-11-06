import java.util.Collection;

public class HibernateEquipmentSupplierService
        implements EquipmentSupplierService {

    @Override
    public EquipmentSupplier findById(long id)
            throws EntityNotFoundException {

        EquipmentSupplier supplier = JpaUtil.executeRead(
                em -> em.find(EquipmentSupplier.class, id)
        );

        if (supplier == null) {
            throw new EntityNotFoundException(id);
        }

        return supplier;
    }

    @Override
    public void save(EquipmentSupplier supplier) {
        JpaUtil.executeInTransaction(em -> {
            if (supplier.getId() == 0) {
                em.persist(supplier);
            } else {
                em.merge(supplier);
            }
        });
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {

        findById(id);

        JpaUtil.executeInTransaction(em -> {
            EquipmentSupplier supplier =
                    em.find(EquipmentSupplier.class, id);

            if (supplier != null) {
                em.remove(supplier);
            }
        });
    }

    @Override
    public Collection<EquipmentSupplier> findAllSortedByName() {
        return JpaUtil.executeRead(em ->
                em.createQuery(
                        "SELECT s FROM EquipmentSupplier s ORDER BY s.name ASC",
                        EquipmentSupplier.class
                ).getResultList()
        );
    }
}
