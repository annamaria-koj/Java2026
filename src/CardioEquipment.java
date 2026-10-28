import java.util.Objects;

public sealed class CardioEquipment extends SportsEquipment permits Treadmill {

    private double maxSpeed;

    public CardioEquipment(
        String equipmentName,
        String equipmentCategory,
        int quantity,
        double price,
        String manufacturer) {

    this(equipmentName, equipmentCategory, quantity, price, manufacturer, 0.0);
}

    public CardioEquipment(
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer,
            double maxSpeed) {

        super(
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

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        CardioEquipment other = (CardioEquipment) obj;
        return Double.compare(maxSpeed, other.maxSpeed) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed);
    }
}
