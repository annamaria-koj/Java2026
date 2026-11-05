public abstract sealed class SportsEquipment extends BaseEntity
        permits CardioEquipment, StrengthEquipment {

    private String equipmentCategory;
    private int quantity;
    private double price;
    private String manufacturer;

    public SportsEquipment(
            long id,
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer) {

        super(id, equipmentName);

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
        return getName();
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + ", Назва: " + getName()
                + ", Категорія: " + equipmentCategory
                + ", Кількість: " + quantity
                + ", Ціна: " + price
                + ", Виробник: " + manufacturer;
    }
}
