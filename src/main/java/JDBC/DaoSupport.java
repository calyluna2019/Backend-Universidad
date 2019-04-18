package JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public abstract class DaoSupport<T extends Entidad> implements Dao<T> {

    private ConnectionManager connectionManager;

    public DaoSupport(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected abstract String nameTable();
    protected abstract String sqlInsert(T entidad);
    protected abstract Map<Integer, Object> getParameters(T entidad);

    @Override
    public T grabar(T entidad) throws SQLException {
        String sql = sqlInsert(entidad);
        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        getParameters(entidad).forEach((Integer position, Object value) -> {
            try {
                statement.setObject(position, value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        statement.executeUpdate();

        ResultSet generatedKey = statement.getGeneratedKeys();
        if (generatedKey.next()) {
            entidad.setId(generatedKey.getInt(1));
        }
        return entidad;
    }

    @Override
    public void actualizar(T actualizar) throws SQLException {

    }

    @Override
    public void borrar(Integer id) throws SQLException {

    }

    @Override
    public List<T> obtenerTodo() throws SQLException {


        return null;
    }

    @Override
    public T obtenerPorId(Integer id) throws SQLException {
        return null;
    }
}
