import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class JdbcSportsEquipmentService
        extends AbstractJdbcCrudService<SportsEquipment>
        implements SportsEquipmentService {

    @Override
    protected String getTableName() {
        return "sports_equipment";
    }

    @Override
    protected String getInsertSql() {
        return """
                insert into sports_equipment
                (id, name, equipment_category, equipment_type,
                 quantity, price, manufacturer, max_speed,
                 max_load, max_incline)
                values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
    }

    @Override
    protected String getUpdateSql() {
        return """
                update sports_equipment
                set name = ?, equipment_category = ?, equipment_type = ?,
                    quantity = ?, price = ?, manufacturer = ?,
                    max_speed = ?, max_load = ?, max_incline = ?
                where id = ?
                """;
    }

    @Override
    protected String getDeleteSql() {
        return "delete from sports_equipment where id = ?";
    }

    @Override
    protected String getFindByIdSql() {
        return "select * from sports_equipment where id = ?";
    }

    @Override
    protected String getFindAllSortedSql() {
        return """
                select * from sports_equipment
                order by lower(name)
                """;
    }

    @Override
    protected void setParameters(
            PreparedStatement statement,
            SportsEquipment equipment) throws SQLException {

        setParametersWithoutId(statement, equipment, 1);
        statement.setLong(10, equipment.getId());
    }

    @Override
    protected void setInsertParameters(
            PreparedStatement statement,
            SportsEquipment equipment) throws SQLException {

        statement.setLong(1, equipment.getId());
        setParametersWithoutId(statement, equipment, 2);
    }

    private void setParametersWithoutId(
            PreparedStatement statement,
            SportsEquipment equipment,
            int startIndex) throws SQLException {

        statement.setString(startIndex, equipment.getName());
        statement.setString(startIndex + 1, equipment.getEquipmentCategory());
        statement.setString(startIndex + 2, getTypeCode(equipment));
        statement.setInt(startIndex + 3, equipment.getQuantity());
        statement.setBigDecimal(startIndex + 4,
                BigDecimal.valueOf(equipment.getPrice()));
        statement.setString(startIndex + 5, equipment.getManufacturer());

        if (equipment instanceof CardioEquipment cardio) {
            statement.setBigDecimal(startIndex + 6,
                    BigDecimal.valueOf(cardio.getMaxSpeed()));
        } else {
            statement.setNull(startIndex + 6, java.sql.Types.DECIMAL);
        }

        if (equipment instanceof StrengthEquipment strength) {
            statement.setBigDecimal(startIndex + 7,
                    BigDecimal.valueOf(strength.getMaxLoad()));
        } else {
            statement.setNull(startIndex + 7, java.sql.Types.DECIMAL);
        }

        if (equipment instanceof Treadmill treadmill) {
            statement.setBigDecimal(startIndex + 8,
                    BigDecimal.valueOf(treadmill.getMaxIncline()));
        } else {
            statement.setNull(startIndex + 8, java.sql.Types.DECIMAL);
        }
    }

    private String getTypeCode(SportsEquipment equipment) {

        if (equipment instanceof Treadmill) {
            return "TREADMILL";
        }

        if (equipment instanceof StrengthEquipment) {
            return "STRENGTH";
        }

        if (equipment instanceof CardioEquipment) {
            return "CARDIO";
        }

        throw new IllegalArgumentException("Невідомий тип обладнання");
    }

    @Override
    protected SportsEquipment mapRow(ResultSet resultSet)
            throws SQLException {

        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String category = resultSet.getString("equipment_category");
        int quantity = resultSet.getInt("quantity");
        double price = resultSet.getBigDecimal("price").doubleValue();
        String manufacturer = resultSet.getString("manufacturer");
        String type = resultSet.getString("equipment_type");

        return switch (type) {
            case "TREADMILL" -> new Treadmill(
                    id, name, category, quantity, price, manufacturer,
                    resultSet.getBigDecimal("max_speed").doubleValue(),
                    resultSet.getBigDecimal("max_incline").doubleValue()
            );

            case "CARDIO" -> new CardioEquipment(
                    id, name, category, quantity, price, manufacturer,
                    resultSet.getBigDecimal("max_speed").doubleValue()
            );

            case "STRENGTH" -> new StrengthEquipment(
                    id, name, category, quantity, price, manufacturer,
                    resultSet.getBigDecimal("max_load").doubleValue()
            );

            default -> throw new SQLException(
                    "Невідомий тип обладнання: " + type);
        };
    }

    @Override
    public Collection<SportsEquipment> findAllSortedByNameAndByPrice() {

        String sql = """
                select * from sports_equipment
                order by lower(name), price
                """;

        List<SportsEquipment> equipmentList = new ArrayList<>();

        try (var connection = DBConnection.getInstance().getConnection();
             var statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                equipmentList.add(mapRow(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Помилка сортування обладнання", e);
        }

        return equipmentList;
    }

    @Override
    public Collection<SportsEquipment> findAllSorted(
            Comparator<? super SportsEquipment> comparator) {

        List<SportsEquipment> equipmentList =
                new ArrayList<>(findAllSortedByName());

        equipmentList.sort(comparator);

        return equipmentList;
    }


public void batchInsert(Collection<SportsEquipment> equipmentList) {

    String sql = getInsertSql();

    try (var connection = DBConnection.getInstance().getConnection();
         var statement = connection.prepareStatement(sql)) {

        for (SportsEquipment equipment : equipmentList) {

            if (equipment == null) {
                throw new IllegalArgumentException(
                        "Обладнання не може бути null.");
            }

            setInsertParameters(statement, equipment);
            statement.addBatch();
        }

        statement.executeBatch();

    } catch (SQLException e) {
        throw new RuntimeException(
                "Помилка пакетного додавання обладнання", e);
    }
}


@Override
public Collection<SportsEquipment> findPage(int page, int pageSize) {

    if (page < 1 || pageSize < 1) {
        throw new IllegalArgumentException(
                "Номер сторінки та її розмір повинні бути більшими за 0.");
    }

    int offset = (page - 1) * pageSize;

    String sql = """
            select * from sports_equipment
            order by lower(name)
            limit ? offset ?
            """;

    List<SportsEquipment> equipmentList = new ArrayList<>();

    try (var connection = DBConnection.getInstance().getConnection();
         var statement = connection.prepareStatement(sql)) {

        statement.setInt(1, pageSize);
        statement.setInt(2, offset);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                equipmentList.add(mapRow(resultSet));
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException(
                "Помилка отримання сторінки обладнання", e);
    }

    return equipmentList;
}


}
