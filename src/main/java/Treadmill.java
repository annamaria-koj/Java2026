public final class Treadmill extends CardioEquipment {

    private double maxIncline;

    public Treadmill(
            long id,
            String equipmentName,
            String equipmentCategory,
            int quantity,
            double price,
            String manufacturer,
            double maxSpeed,
            double maxIncline) {

        super(
                id,
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
}