public class OutOfStockCheck extends AbstractStockCheck implements Reportable{

    @Override
    public boolean check(SportsEquipment item) {
        return item.getQuantity() == 0;
    }

    @Override public String getDescription() {
        return "Перевірка відсутності інвентарю";
    }

    @Override 
    public String getReportName () {
        return "Немає в наявності";
    }
    
}
