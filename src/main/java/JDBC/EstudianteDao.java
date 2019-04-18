package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDao implements Dao<Estudiante> {

    private ConnectionManager connectionManager;

    public EstudianteDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Estudiante grabar(Estudiante estudiante) throws SQLException {

        String sql = "insert into Estudiante (nombre, apellido, padron) values (? , ? , ? )";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, estudiante.getNombre());
        statement.setString(2, estudiante.getApellido());
        statement.setString(3, estudiante.getPadron());
        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();
        if (keys.next()) {
            estudiante.setId(keys.getInt(1));
        }
        return estudiante;
    }

    public List<Estudiante> obtenerTodo() throws SQLException {

        String sql = "select * from Estudiante";

        Statement stmt = connectionManager.getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        List<Estudiante> estudiantes = new ArrayList<>();
        while (resultSet.next()) {
            estudiantes.add(construir(resultSet));
        }
        return estudiantes;
    }

    public Estudiante obtenerPorId(Integer id) throws SQLException {

        String sql = "select * from Estudiante where Id = ?";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Estudiante estudiante = null;
        if (resultSet.next()) {
            estudiante = construir(resultSet);
        }
        return estudiante;
    }

    public void borrar(Integer id) throws SQLException {

        String sql = "delete from Estudiante where Id = ?";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void actualizar(Estudiante estudiante) throws SQLException{

        String sql = "update Estudiante set nombre =?, apellido =?, padron =? where Id =?";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(sql);
        statement.setString(1, estudiante.getNombre());
        statement.setString(2, estudiante.getApellido());
        statement.setString(3, estudiante.getPadron());
        statement.setInt(4, estudiante.getId());
        statement.executeUpdate();
    }

    private Estudiante construir(ResultSet resultSet) throws SQLException {
        Estudiante estudiante = new Estudiante();
        estudiante = new Estudiante();
        estudiante.setId(resultSet.getInt("Id"));
        estudiante.setNombre(resultSet.getString("nombre"));
        estudiante.setApellido(resultSet.getString("apellido"));
        estudiante.setPadron(resultSet.getString("padron"));
        return estudiante;
    }
}
