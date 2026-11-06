public sealed class CardioEquipment extends SportsEquipment permits Treadmill {

    private double maxSpeed;

    public CardioEquipment(
            long id,
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer) {

        this(id, equipmentName, equipmentCategory, quantity, price,
                manufacturer, 0.0);
    }

    public CardioEquipment(
            long id,
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer,
            double maxSpeed) {

        super(
                id,
                equipmentName,
                equipmentCategory,
                quantity,
                price,
                manufacturer);

        this.maxSpeed = maxSpeed;
        System.out.println("Конструктор CardioEquipment");
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String getEquipmentType() {
        return "Кардіотренажер";
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Максимальна швидкість: " + maxSpeed;
    }
}
