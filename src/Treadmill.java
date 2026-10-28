import java.util.Objects;

public final class Treadmill extends CardioEquipment {

    private double maxIncline;

    public Treadmill(
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer,
            double maxSpeed,
            double maxIncline) {

        super(
                equipmentName,
                equipmentCategory,
                quantity,
                price,
                manufacturer,
                maxSpeed);

        this.maxIncline = maxIncline;
        System.out.println("Конструктор Treadmill");
    }

    public double getMaxIncline() {
        return maxIncline;
    }

    public void setMaxIncline(double maxIncline) {
        this.maxIncline = maxIncline;
    }

    @Override
    public String getEquipmentType() {
        return "Бігова доріжка";
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Максимальний кут нахилу: " + maxIncline;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        Treadmill other = (Treadmill) obj;
        return Double.compare(maxIncline, other.maxIncline) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxIncline);
    }

}
