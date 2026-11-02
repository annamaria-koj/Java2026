import java.util.Collection;
import java.util.Comparator;

public class SportsEquipmentServiceImpl extends InMemoryCrudService<SportsEquipment>
        implements SportsEquipmentService {

    @Override
    public Collection<SportsEquipment> findAllSortedByNameAndByPrice() {

        Comparator<SportsEquipment> comparator =
                Comparator.comparing(
                        SportsEquipment::getName,
                        String.CASE_INSENSITIVE_ORDER
                ).thenComparingDouble(SportsEquipment::getPrice);

        return findAllSorted(comparator);
    }
}