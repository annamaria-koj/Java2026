public class StockLogObserver implements StockObserver {

    @Override
    public void onStockChanged(SportsEquipment item) {
        System.out.println(
                "Журнал: кількість інвентарю змінено. " + item);
    }
}
