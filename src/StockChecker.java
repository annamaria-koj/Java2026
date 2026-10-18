public class StockChecker {

    private StockCheck strategy;

    public StockChecker(StockCheck strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(StockCheck strategy) {
        this.strategy = strategy;
    }

    public boolean check(SportsEquipment item) {
        return strategy.check(item);
    }

    public String getDescription() {
        return strategy.getDescription();
    }
}