import java.util.Collection;

public interface SportsEquipmentService extends CRUDService<SportsEquipment> {

    Collection<SportsEquipment> findAllSortedByNameAndByPrice();
    
}
