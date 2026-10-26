public class LowStockCheck extends AbstractStockCheck implements Reportable {

    @Override
    public boolean check(SportsEquipment item) {
        return item.getQuantity() > 0 && item.getQuantity() <= Main.LOW_STOCK_LIMIT;
    }

    @Override 
    public String getDescription() {
        return "Перевірка малої кількості інвентарю";
    }

    @Override
    public String getReportName() {
        return "Мала кількість інвентарю";
    }
}
