import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CARDIO")
public class CardioEquipment extends SportsEquipment {

    @Column(name = "max_speed", precision = 6, scale = 2)
    private BigDecimal maxSpeed;

    protected CardioEquipment() {
    }

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

        super(id, equipmentName, equipmentCategory, quantity, price,
                manufacturer);

        this.maxSpeed = BigDecimal.valueOf(maxSpeed);
    }

    public double getMaxSpeed() {
        return maxSpeed.doubleValue();
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = BigDecimal.valueOf(maxSpeed);
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
