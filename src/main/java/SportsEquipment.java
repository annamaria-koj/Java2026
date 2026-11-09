import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "sports_equipment")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "equipment_type", discriminatorType = DiscriminatorType.STRING, length = 50)
public abstract class SportsEquipment extends BaseEntity {

    @Column(name = "equipment_category", nullable = false, length = 100)
    private String equipmentCategory;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "manufacturer", nullable = false, length = 100)
    private String manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private EquipmentSupplier supplier;

    protected SportsEquipment() {
    }

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
        this.price = BigDecimal.valueOf(price);
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
        return price.doubleValue();
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

    public EquipmentSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(EquipmentSupplier supplier) {
        this.supplier = supplier;
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
