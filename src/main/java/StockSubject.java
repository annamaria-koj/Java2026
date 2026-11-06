import java.util.ArrayList;
import java.util.List;

public class StockSubject {

    private final List<StockObserver> observers = new ArrayList<>();

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(SportsEquipment item) {
        for (StockObserver observer : observers) {
            observer.onStockChanged(item);
        }
    }

    public void changeQuantity(SportsEquipment item, int newQuantity) {
        int oldQuantity = item.getQuantity();

        if (oldQuantity != newQuantity) {
            item.setQuantity(newQuantity);
            notifyObservers(item);
        }
    }
}
