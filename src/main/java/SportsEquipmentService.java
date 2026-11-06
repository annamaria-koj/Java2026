import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface SportsEquipmentService extends CRUDService<SportsEquipment> {

    Collection<SportsEquipment> findAllSortedByNameAndByPrice();

    Collection<SportsEquipment> findAllSorted(
            Comparator<? super SportsEquipment> comparator);

default Collection<SportsEquipment> findPage(
        int page, int pageSize) {

    if (page < 1 || pageSize < 1) {
        throw new IllegalArgumentException(
                "Номер сторінки та її розмір повинні бути більшими за 0.");
    }

    List<SportsEquipment> equipmentList =
            new ArrayList<>(findAllSortedByName());

    int fromIndex = (page - 1) * pageSize;

    if (fromIndex >= equipmentList.size()) {
        return List.of();
    }

    int toIndex = Math.min(
            fromIndex + pageSize,
            equipmentList.size());

    return equipmentList.subList(fromIndex, toIndex);
}

    int count();
}
