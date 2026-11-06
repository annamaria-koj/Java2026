import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractJdbcCrudService<T extends BaseEntity>
        implements CRUDService<T> {

    protected abstract String getTableName();

    protected abstract String getInsertSql();

    protected abstract String getUpdateSql();

    protected abstract String getDeleteSql();

    protected abstract String getFindByIdSql();

    protected abstract String getFindAllSortedSql();

    protected abstract T mapRow(ResultSet resultSet) throws SQLException;

    protected abstract void setParameters(
            PreparedStatement statement, T entity) throws SQLException;

    protected abstract void setInsertParameters(
            PreparedStatement statement, T entity) throws SQLException;

    @Override
    public T findById(long id) throws EntityNotFoundException {

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(getFindByIdSql())) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Помилка пошуку запису", e);
        }

        throw new EntityNotFoundException(id);
    }

    @Override
    public void save(T entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Об'єкт не може бути null.");
        }

        try (Connection connection = DBConnection.getInstance().getConnection()) {

            if (exists(connection, entity.getId())) {

                try (PreparedStatement statement =
                             connection.prepareStatement(getUpdateSql())) {

                    setParameters(statement, entity);
                    statement.executeUpdate();
                }

            } else {

                try (PreparedStatement statement =
                             connection.prepareStatement(getInsertSql())) {

                    setInsertParameters(statement, entity);
                    statement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Помилка збереження запису", e);
        }
    }

    private boolean exists(Connection connection, long id)
            throws SQLException {

        String sql = "select id from " + getTableName() + " where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(getDeleteSql())) {

            statement.setLong(1, id);

            if (statement.executeUpdate() == 0) {
                throw new EntityNotFoundException(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Помилка видалення запису", e);
        }
    }

    @Override
    public Collection<T> findAllSortedByName() {

        List<T> entities = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(getFindAllSortedSql());
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                entities.add(mapRow(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Помилка отримання записів", e);
        }

        return entities;
    }

    public int count() {

        String sql = "select count(*) from " + getTableName();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Помилка підрахунку записів", e);
        }

        return 0;
    }
}