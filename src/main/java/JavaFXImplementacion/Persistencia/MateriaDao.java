package JavaFXImplementacion.Persistencia;

import JDBC.ConnectionManager;
import JavaFXImplementacion.Dominio.Materia;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    protected String sqlUpdate(Materia entidad) {
        return "update Materia set nombre = ?, cargaHoraria = ? where id = ?";
    }

    @Override
    protected Map<Integer, Object> getParameters(Materia entidad) {
        Map<Integer, Object> parameters = new HashMap<>();
        parameters.put(1, entidad.getNombre());
        parameters.put(2, entidad.getCargaHoraria());
        return parameters;
    }

    @Override
    protected String nameTable() {
        return "Materia";
    }

    @Override
    protected Materia createEntity(ResultSet resultSet) throws SQLException {
        Materia materia = new Materia();
        materia.setId(resultSet.getInt("id"));
        materia.setNombre(resultSet.getString("nombre"));
        materia.setCargaHoraria(resultSet.getInt("cargaHoraria"));
        return materia;
    }

}
