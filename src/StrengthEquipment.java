public final class StrengthEquipment extends SportsEquipment {

    private double maxLoad;

    public StrengthEquipment(
            long id,
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer,
            double maxLoad) {

        super(
                id,
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
}
