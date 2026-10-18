public abstract class AbstractStockCheck implements StockCheck {

    public void printResult(SportsEquipment item) {
        boolean result = check(item);

        System.out.println(
            getDescription() + ": " + (result ? "виявлено" : "не виявлено")
        );
    }
    
}
