import java.util.Collection;

public class EntityPrinter {

    public static void printAll(Collection<? extends BaseEntity> entities) {

        for (BaseEntity entity : entities) {
            System.out.println(entity);
        }
    }
    
}
