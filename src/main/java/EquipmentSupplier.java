public class EquipmentSupplier extends BaseEntity {

    private String phone;
    private String email;

    public EquipmentSupplier(
            long id,
            String name,
            String phone,
            String email) {

        super(id, name);
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