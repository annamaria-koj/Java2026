import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipment_suppliers")
public class EquipmentSupplier extends BaseEntity {

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @OneToMany(
            mappedBy = "supplier",
            fetch = FetchType.LAZY
    )
    private List<SportsEquipment> equipment = new ArrayList<>();

    protected EquipmentSupplier() {
    }

    public EquipmentSupplier(
            long id,
            String name,
            String phone,
            String email) {

        super(id, name);
        this.phone = phone;
        this.email = email;
    }

    public EquipmentSupplier(
            String name,
            String phone,
            String email) {

        super(0, name);
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SportsEquipment> getEquipment() {
        return equipment;
    }

    public void addEquipment(SportsEquipment item) {
        if (item == null) {
            return;
        }

        if (!equipment.contains(item)) {
            equipment.add(item);
            item.setSupplier(this);
        }
    }

    public void removeEquipment(SportsEquipment item) {
        if (item == null) {
            return;
        }

        if (equipment.remove(item)) {
            item.setSupplier(null);
        }
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + ", Назва постачальника: " + getName()
                + ", Телефон: " + phone
                + ", Email: " + email;
    }
}
