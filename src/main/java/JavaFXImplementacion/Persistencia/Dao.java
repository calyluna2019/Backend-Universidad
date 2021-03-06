package JavaFXImplementacion.Persistencia;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    T grabar(T entidad) throws SQLException;
    void actualizar(T entidad) throws SQLException;
    void borrar(Integer id) throws SQLException;
    List<T> obtenerTodo() throws SQLException;
    T obtenerPorId(Integer id) throws SQLException;

}
