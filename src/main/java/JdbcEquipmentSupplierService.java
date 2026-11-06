import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEquipmentSupplierService
        extends AbstractJdbcCrudService<EquipmentSupplier>
        implements EquipmentSupplierService {

    @Override
    protected String getTableName() {
        return "equipment_suppliers";
    }

    @Override
    protected String getInsertSql() {
        return """
                insert into equipment_suppliers (id, name, phone, email)
                values (?, ?, ?, ?)
                """;
    }

    @Override
    protected String getUpdateSql() {
        return """
                update equipment_suppliers
                set name = ?, phone = ?, email = ?
                where id = ?
                """;
    }

    @Override
    protected String getDeleteSql() {
        return "delete from equipment_suppliers where id = ?";
    }

    @Override
    protected String getFindByIdSql() {
        return "select * from equipment_suppliers where id = ?";
    }

    @Override
    protected String getFindAllSortedSql() {
        return """
                select * from equipment_suppliers
                order by lower(name)
                """;
    }

    @Override
    protected void setParameters(
            PreparedStatement statement,
            EquipmentSupplier supplier) throws SQLException {

        statement.setString(1, supplier.getName());
        statement.setString(2, supplier.getPhone());
        statement.setString(3, supplier.getEmail());
        statement.setLong(4, supplier.getId());
    }

    @Override
    protected void setInsertParameters(
            PreparedStatement statement,
            EquipmentSupplier supplier) throws SQLException {

        statement.setLong(1, supplier.getId());
        statement.setString(2, supplier.getName());
        statement.setString(3, supplier.getPhone());
        statement.setString(4, supplier.getEmail());
    }

    @Override
    protected EquipmentSupplier mapRow(ResultSet resultSet)
            throws SQLException {

        return new EquipmentSupplier(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("phone"),
                resultSet.getString("email")
        );
    }
}
