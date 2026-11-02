import java.util.Collection;

public interface CRUDService<T extends BaseEntity> {

    T findById(long id) throws EntityNotFoundException;

    void save(T entity);

    void delete(long id) throws EntityNotFoundException;

    Collection<T> findAllSortedByName();

    default boolean existsById(long id) {
        try {
            findById(id);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }
}