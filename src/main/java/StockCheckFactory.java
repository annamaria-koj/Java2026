public class StockCheckFactory {
    
    public static StockCheck createCheck(String type) {

        switch (type.toLowerCase()) {
            case  "low":
                return new LowStockCheck();

            case "out":
                return new OutOfStockCheck();
                
            default:
                throw new IllegalArgumentException("Невідомий тип перевірки: " + type);
        }
    }
}
