package JDBC;

import java.util.HashMap;
import java.util.Map;

public class MateriaDao extends DaoSupport<Materia> {

    public MateriaDao(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    @Override
    protected String sqlInsert(Materia entidad) {
        return "insert into Materia (nombre, carga_horaria) values (?, ?)";
    }

    @Override
    protected Map<Integer, Object> getParameters(Materia entidad) {
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, entidad.getNombre());
        parameters.put(2, entidad.getCargaHoraria());
        return parameters;
    }


}
