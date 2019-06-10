package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CarreraDao extends DaoSupport<Carrera> {

    public CarreraDao(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    protected String sqlInsert(Carrera entidad) {
        return "insert into Carrera (nombre) values (?)";
    }

    @Override
    protected String sqlUpdate(Carrera entidad) {
        return "update Carrera set nombre = ? where id = ?";
    }

    @Override
    protected Map<Integer, Object> getParameters(Carrera entidad) {
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, entidad.getNombre());
        return parameters;
    }

    @Override
    protected String nameTable() {
        return "Carrera";
    }

    @Override
    protected Carrera createEntity(ResultSet resultSet) throws SQLException {
        Carrera carrera = new Carrera();
        carrera.setId(resultSet.getInt("id"));
        carrera.setNombre(resultSet.getString("nombre"));
        return carrera;
    }
}
