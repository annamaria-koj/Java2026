import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class InMemoryCrudService<T extends BaseEntity>
        implements CRUDService<T> {

    private final List<T> entities = new ArrayList<>();

    @Override
    public T findById(long id) throws EntityNotFoundException {
        for (T entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }

        throw new EntityNotFoundException(id);
    }

    @Override
    public void save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Об'єкт не може бути null.");
        }

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == entity.getId()) {
                entities.set(i, entity);
                return;
            }
        }

        entities.add(entity);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == id) {
                entities.remove(i);
                return;
            }
        }

        throw new EntityNotFoundException(id);
    }

    @Override
    public Collection<T> findAllSortedByName() {
        List<T> sorted = new ArrayList<>(entities);

        sorted.sort(
                Comparator.comparing(
                        entity -> entity.getName(),
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return sorted;
    }

    public Collection<T> findAllSorted(Comparator<? super T> comparator){
        List<T> sorted = new ArrayList<>(entities);
        sorted.sort(comparator);
        return sorted;
    }

    public int count() {
        return entities.size();
    }
}