import java.util.Objects;

public final class StrengthEquipment extends SportsEquipment {

    private double maxLoad;

    public StrengthEquipment(
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer,
            double maxLoad) {

        super(
                equipmentName,
                equipmentCategory,
                quantity,
                price,
                manufacturer);

        this.maxLoad = maxLoad;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }

    @Override
    public String getEquipmentType() {
        return "Силове обладнання";
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Максимальне навантаження: " + maxLoad;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        StrengthEquipment other = (StrengthEquipment) obj;
        return Double.compare(maxLoad, other.maxLoad) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxLoad);
    }
}
