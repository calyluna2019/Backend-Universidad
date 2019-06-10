package JavaFXImplementacion.Persistencia;

import JDBC.ConnectionManager;
import JDBC.Entidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class DaoSupport<T extends Entidad> implements Dao<T> {

    private ConnectionManager connectionManager;

    public DaoSupport(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    protected abstract String nameTable();
    protected abstract T createEntity(ResultSet resultSet) throws SQLException;
    protected abstract String sqlInsert(T entidad);
    protected abstract String sqlUpdate(T entidad);
    protected abstract Map<Integer, Object> getParameters(T entidad);

    @Override
    public T grabar(T entidad) throws SQLException {
        String sql = sqlInsert(entidad);
        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        setParameters(entidad, statement);

        statement.executeUpdate();

        ResultSet generatedKey = statement.getGeneratedKeys();
        if (generatedKey.next()) {
            entidad.setId(generatedKey.getInt(1));
        }
        return entidad;
    }

    @Override
    public void actualizar(T entidad) throws SQLException {

        String sql = sqlUpdate(entidad);
        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql);

        setParameters(entidad, statement);

        statement.setInt(getParameters(entidad).size()+1, entidad.getId());

        statement.executeUpdate();

    }

    private void setParameters(T entidad, PreparedStatement statement) {
        getParameters(entidad).forEach((Integer position, Object value) -> {
            try {
                statement.setObject(position, value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void borrar(Integer id) throws SQLException {

        String sql = "delete from "+nameTable()+" where id = ?";
        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();

    }

    @Override
    public List<T> obtenerTodo() throws SQLException {

        String sql = "select * from "+nameTable();
        Statement statement = connectionManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<T> entidades = new ArrayList<>();
        while (resultSet.next()) {
           entidades.add(createEntity(resultSet));
        }

        return entidades;
    }

    @Override
    public T obtenerPorId(Integer id) throws SQLException {
        String sql = "select * from "+nameTable()+" where id = ?";
        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        T entidad = null;

        if (resultSet.next()) {
            entidad = createEntity(resultSet);
        }
        return entidad;
    }
}
