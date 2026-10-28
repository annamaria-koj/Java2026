import java.util.Objects;

public abstract sealed class SportsEquipment
        permits CardioEquipment, StrengthEquipment {

    private String equipmentName;
    private String equipmentCategory;
    private int quantity;
    private double price;
    private String manufacturer;

    public SportsEquipment(
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer) {

        this.equipmentName = equipmentName;
        this.equipmentCategory = equipmentCategory;
        this.quantity = quantity;
        this.price = price;
        this.manufacturer = manufacturer;

        System.out.println("Конструктор SportsEquipment");
    }

    public abstract String getEquipmentType();

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Назва: " + equipmentName
                + ", Категорія: " + equipmentCategory
                + ", Кількість: " + quantity
                + ", Ціна: " + price
                + ", Виробник: " + manufacturer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SportsEquipment other = (SportsEquipment) obj;

        return Objects.equals(equipmentName, other.equipmentName)
                && Objects.equals(equipmentCategory, other.equipmentCategory)
                && quantity == other.quantity
                && Double.compare(price, other.price) == 0
                && Objects.equals(manufacturer, other.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                equipmentName,
                equipmentCategory,
                quantity,
                price,
                manufacturer);
    }
}
