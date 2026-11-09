import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TREADMILL")
public class Treadmill extends CardioEquipment {

    @Column(name = "max_incline", precision = 5, scale = 2)
    private BigDecimal maxIncline;

    protected Treadmill() {
    }

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

        this.maxIncline = BigDecimal.valueOf(maxIncline);
        System.out.println("Конструктор Treadmill");
    }

    public double getMaxIncline() {
        return maxIncline.doubleValue();
    }

    public void setMaxIncline(double maxIncline) {
        this.maxIncline = BigDecimal.valueOf(maxIncline);
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
