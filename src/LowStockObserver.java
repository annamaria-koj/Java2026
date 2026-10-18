public class LowStockObserver implements StockObserver {

    @Override
    public void onStockChanged(SportsEquipment item) {
        if (item.getQuantity() > 0
                && item.getQuantity() <= Main.LOW_STOCK_LIMIT) {

            System.out.println(
                    "УВАГА! Залишилося мало інвентарю: " + item);
        }
    }
}
