import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("STRENGTH")
public class StrengthEquipment extends SportsEquipment {

    @Column(name = "max_load", precision = 8, scale = 2)
    private BigDecimal maxLoad;

    protected StrengthEquipment() {
    }

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

        this.maxLoad = BigDecimal.valueOf(maxLoad);
    }

    public double getMaxLoad() {
        return maxLoad.doubleValue();
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = BigDecimal.valueOf(maxLoad);
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
