import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment_suppliers")
public class EquipmentSupplier extends BaseEntity {

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "email", unique = true, length = 255)
    private String email;

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

    @Override
    public String toString() {
        return "ID: " + getId()
                + ", Назва постачальника: " + getName()
                + ", Телефон: " + phone
                + ", Email: " + email;
    }
}
