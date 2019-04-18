package JDBC;

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
    protected Map<Integer, Object> getParameters(Carrera entidad) {
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, entidad.getNombre());
        return parameters;
    }
}
