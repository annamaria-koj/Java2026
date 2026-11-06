public class LegacyStockCheckAdapter implements StockCheck {

    private final LegacyStockChecker legacyChecker;

    public LegacyStockCheckAdapter(LegacyStockChecker legacyChecker) {
        this.legacyChecker = legacyChecker;
    }

    @Override
    public boolean check(SportsEquipment item) {
        return legacyChecker.isOutOfStock(item.getQuantity());
    }

    @Override
    public String getDescription() {
        return "Перевірка запасів через Legacy Adapter";
    }
}
