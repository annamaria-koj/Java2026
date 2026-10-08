public class SportsEquipment {
    
    private String equipmentName;
    private String equipmentCategory;
    private int quantity;
    private double price;
    private String manufacturer; 

    public SportsEquipment(String equipmentName, String equipmentCategory, int quantity, double price, String manufacturer)
    {
        this.equipmentName = equipmentName;
        this.equipmentCategory = equipmentCategory;
        this.quantity = quantity;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Назва: " + equipmentName+
               ", Категорія: " + equipmentCategory +
               ", Кількість: " + quantity +
               ", Ціна: " + price +
               ", Виробник: " + manufacturer;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        SportsEquipment other = (SportsEquipment) obj;

        return equipmentName.equals(other.equipmentName)
                && equipmentCategory.equals(other.equipmentCategory)
                && quantity == other.quantity
                && Double.compare(price, other.price) == 0
                && manufacturer.equals(other.manufacturer);
    }
}